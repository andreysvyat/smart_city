package ru.krd.smc.service.processors;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.krd.smc.dba.CityCaseRepo;
import ru.krd.smc.dba.CityCaseTypeRepo;
import ru.krd.smc.dba.ContractorRepo;
import ru.krd.smc.model.entity.CityCase;
import ru.krd.smc.model.entity.CityCaseType;
import ru.krd.smc.model.entity.ContractorInfo;
import ru.krd.smc.model.entity.OKVED;
import ru.krd.smc.model.enums.CityCaseStatus;
import ru.krd.smc.model.resp.CityCaseInfo;
import ru.krd.smc.model.resp.CityCaseShortResp;
import ru.krd.smc.model.rq.ContractorNotification;
import ru.krd.smc.model.rq.NewCityCase;
import ru.krd.smc.service.CityCaseProcessor;
import ru.krd.smc.service.UserProcessor;
import ru.krd.smc.service.client.AddressRequestor;
import ru.krd.smc.service.client.Notifier;

import java.util.Optional;
import java.util.UUID;

import static ru.krd.smc.model.enums.CityCaseStatus.*;

@Log4j2
@Service
@RequiredArgsConstructor
public class BaseCityCaseProcessor implements CityCaseProcessor {

	private final CityCaseRepo cityCaseRepo;
	private final ContractorRepo contractorRepo;
	private final CityCaseTypeRepo caseTypeRepo;

	private final UserProcessor userProcessor;
	private final AddressRequestor addressRequestor;
	private final Notifier notifier;

	@Override
	public Page<CityCaseShortResp> getCases(Pageable pageable, UUID userId) {
		return cityCaseRepo.findAllByAuthor(userProcessor.findUser(userId), pageable)
				.map(this::cityCase2ShortResp);
	}

	@Override
	public Page<CityCaseShortResp> getCases(Pageable pageable, CityCaseStatus status, String type) {
		return cityCaseRepo.findAllByTypeAndStatus(getCaseType(type), status, pageable)
				.map(this::cityCase2ShortResp);
	}

	@Override
	public Page<CityCaseShortResp> getCases(Pageable pageable) {
		return cityCaseRepo.findAll(pageable)
				.map(this::cityCase2ShortResp);
	}

	@Override
	public CityCaseInfo createCityCase(NewCityCase newCityCase) {
		log.trace("Start case creation with incoming data {}", newCityCase);
		CityCase eCase = CityCase.builder()
				.author(userProcessor.findUser(newCityCase.getUserId()))
				.initedOn(newCityCase.getInitedOn())
				.description(newCityCase.getDescription())
				.location(newCityCase.getLocation())
				.files(newCityCase.getImages())
				.status(NEW)
				.type(getCaseType(newCityCase.getType()))
				.build();
		log.trace("Case created {}", eCase.toString());

		notifier.notifyOperators(lifecycleProcess(eCase));
		return toCityCaseInfo(eCase);
	}

	@Override
	public CityCaseInfo handleContractorNotification(ContractorNotification notification) {
		CityCase cityCase = cityCaseRepo.findById(UUID.fromString(notification.getCityCaseId()))
				.orElseThrow(() -> new RuntimeException("Case is not presented. ID = " + notification.getCityCaseId()));

		cityCase.setStatus(notification.getStatus());
		return toCityCaseInfo(cityCase);
	}

	private CityCaseType getCaseType(String code) {
		return caseTypeRepo.findOneByCode(code)
				.orElseThrow(() -> new RuntimeException("Unsupported type exception"));
	}

	private CityCase lifecycleProcess(CityCase cityCase) {
		switch (cityCase.getStatus()) {
			case NEW: {
				log.info("Case {} in status {}", cityCase.getId(), cityCase.getStatus());
				cityCase.setAddress(addressRequestor.getAddress(cityCase.getLocation()));
				cityCase.setStatus(IN_PROGRESS);
			}
			case IN_PROGRESS: {
				log.info("Case {} in status {}", cityCase.getId(), cityCase.getStatus());
				Optional<CityCase> same = cityCaseRepo.findDuplicates(
						cityCase.getId(),
						cityCase.getLocation(),
						cityCase.getInitedOn(),
						LINKED);
				if (same.isPresent()) {
					log.info("Case {} is duplicated", cityCase.getId());
					cityCase.setStatus(LINKED);
					cityCase.setLinked(same.get());
					cityCaseRepo.save(cityCase);
					return cityCase;
				}
				cityCase.setStatus(ACCEPTED);
				cityCaseRepo.save(cityCase);
			}
			case ACCEPTED: {
				log.info("Case {} in status {}", cityCase.getId(), cityCase.getStatus());
				processAccepted(cityCase);
			}
		}
		return cityCaseRepo.save(cityCase);
	}

	private void processAccepted(CityCase cityCase) {
		OKVED okved = cityCase.getType()
				.getOkved();
		Optional<ContractorInfo> contractorInfo = contractorRepo.findByOkved(okved);
		if (contractorInfo.isPresent()) {
			cityCase.setStatus(AT_CONTRACTOR);
			notifier.notifyContractor(contractorInfo.get());
		}
		else {
			cityCase.setStatus(ON_HOLD);
		}
	}

	private CityCaseShortResp cityCase2ShortResp(CityCase entity) {
		return CityCaseShortResp.builder()
				.address(entity.getLocation())
				.id(entity.getId()
						    .toString())
				.author(entity.getAuthor()
						        .getLogin())
				.status(entity.getStatus()
						        .display())
				.build();
	}

	private CityCaseInfo toCityCaseInfo(CityCase eCase) {
		return CityCaseInfo.builder()
				.id(eCase.getId()
						    .toString())
				.address(eCase.getAddress())
				.author(eCase.getAuthor()
						        .getLogin())
				.type(eCase.getType()
						      .getName())
				.resLinks(eCase.getFiles())
				.status(eCase.getStatus()
						        .display())
				.build();
	}
}
