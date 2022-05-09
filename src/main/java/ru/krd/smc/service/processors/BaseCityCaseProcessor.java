package ru.krd.smc.service.processors;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.krd.smc.dba.CityCaseRepo;
import ru.krd.smc.model.entity.CityCase;
import ru.krd.smc.model.resp.CityCaseShortResp;
import ru.krd.smc.model.resp.CreatedCityCase;
import ru.krd.smc.model.rq.NewCityCase;
import ru.krd.smc.service.CityCaseProcessor;
import ru.krd.smc.service.UserProcessor;
import ru.krd.smc.service.client.AddressRequestor;

import static ru.krd.smc.model.enums.CityCaseStatus.NEW;

@Log4j2
@Service
@RequiredArgsConstructor
public class BaseCityCaseProcessor implements CityCaseProcessor {

	private final CityCaseRepo cityCaseRepo;
	private final UserProcessor userProcessor;
	private final AddressRequestor addressRequestor;

	@Override
	public Page<CityCaseShortResp> getCases(Pageable pageable) {
		return cityCaseRepo.findAll(pageable)
				.map(entity -> CityCaseShortResp.builder()
						.address(entity.getLocation())
						.id(entity.getId().toString())
						.author(entity.getAuthor().getLogin())
						.status(entity.getStatus().display())
						.build());
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
						.cityCaseType(newCityCase.getCityCaseType())
						.build()
		);

		log.trace("Case created {}", eCase.toString());
		return CreatedCityCase.builder()
				.id(eCase.getId().toString())
				.address(addressRequestor.getAddress(newCityCase.getLocation()))
				.author(eCase.getAuthor().getLogin())
				.cityCaseType(eCase.getCityCaseType().display())
				.resLinks(eCase.getFiles())
				.status(eCase.getStatus().display())
				.build();
	}
}
