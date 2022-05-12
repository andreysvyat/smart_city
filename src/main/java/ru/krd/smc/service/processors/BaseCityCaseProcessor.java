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
import ru.krd.smc.model.resp.CityCaseShortResp;
import ru.krd.smc.model.resp.CreatedCityCase;
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
	public CreatedCityCase createCityCase(NewCityCase newCityCase) {
		log.trace("Start case creation with incoming data {}", newCityCase);
		CityCase eCase = cityCaseRepo.save(
				CityCase.builder()
						.author(userProcessor.findUser(newCityCase.getUserId()))
						.initedOn(newCityCase.getInitedOn())
						.description(newCityCase.getDescription())
						.location(newCityCase.getLocation())
						.files(newCityCase.getImages())
						.status(NEW)
						.type(getCaseType(newCityCase.getType()))
						.build()
		);
		log.trace("Case created {}", eCase.toString());

		notifier.notifyOperators(lifecycleProcess(eCase));
		return CreatedCityCase.builder()
				.id(eCase.getId().toString())
				.address(eCase.getAddress())
				.author(eCase.getAuthor().getLogin())
				.type(eCase.getType().getName())
				.resLinks(eCase.getFiles())
				.status(eCase.getStatus().display())
				.build();
	}

	private CityCaseType getCaseType(String code){
		return caseTypeRepo.findOneByCode(code)
				.orElseThrow(() -> new RuntimeException("Unsupported type exception"));
	}

	private CityCase lifecycleProcess(CityCase cityCase){
		while (cityCase.getStatus() != CLOSED){
			switch (cityCase.getStatus()) {
				case NEW: {
					log.info("Case {} in status {}", cityCase.getId(), cityCase.getStatus());
					cityCase.setAddress(addressRequestor.getAddress(cityCase.getLocation()));
					cityCase.setStatus(IN_PROGRESS);
				}
				case IN_PROGRESS: {
					log.info("Case {} in status {}", cityCase.getId(), cityCase.getStatus());
					Long sameCount = cityCaseRepo.countByLocationAndInitedOn(cityCase.getLocation(), cityCase.getInitedOn());
					if(sameCount > 0){
						cityCase.setStatus(CLOSED);
						cityCaseRepo.save(cityCase);
						log.info("Case {} is duplicated", cityCase.getId());
						return cityCase;
					}
					cityCase.setStatus(ACCEPTED);
				}
				case ACCEPTED: {
					log.info("Case {} in status {}", cityCase.getId(), cityCase.getStatus());
					processAccepted(cityCase);
				}
				case AT_CONTRACTOR: {
					log.info("Case {} in status {}", cityCase.getId(), cityCase.getStatus());
					processATContractor(cityCase);
				}
			}
		}
		throw new RuntimeException("Unknown status processing");
	}

	private void processATContractor(CityCase cityCase) {

	}

	private void processAccepted(CityCase cityCase) {
		OKVED okved = cityCase.getType().getOkved();
		Optional<ContractorInfo> contractorInfo =  contractorRepo.findByOkved(okved);
		if(contractorInfo.isPresent()){
			cityCase.setStatus(AT_CONTRACTOR);
			notifier.notifyContractor(contractorInfo.get());
		} else {
			cityCase.setStatus(ON_HOLD);
		}
	}

	private CityCaseShortResp cityCase2ShortResp(CityCase entity){
		return CityCaseShortResp.builder()
				.address(entity.getLocation())
				.id(entity.getId().toString())
				.author(entity.getAuthor().getLogin())
				.status(entity.getStatus().display())
				.build();
	}
}
