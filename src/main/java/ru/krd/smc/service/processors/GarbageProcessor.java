package ru.krd.smc.service.processors;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import ru.krd.smc.dba.CityCaseRepo;
import ru.krd.smc.model.entity.CityCase;
import ru.krd.smc.model.resp.CreatedCityCase;
import ru.krd.smc.model.rq.NewCityCase;
import ru.krd.smc.service.CityCaseProcessor;
import ru.krd.smc.service.UserProcessor;
import ru.krd.smc.service.client.AddressRequestor;

import static ru.krd.smc.model.enums.CityCaseProcessorType.GARBAGE;
import static ru.krd.smc.model.enums.CityCaseProcessorType.GARBAGE_VALUE;
import static ru.krd.smc.model.enums.CityCaseStatus.NEW;

@Log4j2
@Service(GARBAGE_VALUE)
@RequiredArgsConstructor
public class GarbageProcessor implements CityCaseProcessor {

	private final CityCaseRepo cityCaseRepo;
	private final UserProcessor userProcessor;
	private final AddressRequestor addressRequestor;

	@Override
	public CreatedCityCase createCityCase(NewCityCase newCityCase) {
		log.trace("Start garbage case creation with incoming data {}", newCityCase);

		CityCase eCase = cityCaseRepo.save(
				CityCase.builder()
						.author(userProcessor.findUser(newCityCase.getUserId()))
						.description(newCityCase.getDescription())
						.location(newCityCase.getLocation())
						.files(newCityCase.getImages())
						.build()
		);

		log.trace("Garbage case created {}", eCase.toString());
		return CreatedCityCase.builder()
				.id(eCase.getId().toString())
				.address(addressRequestor.getAddress(newCityCase.getLocation()))
				.author(eCase.getAuthor().getLogin())
				.cityCaseType(GARBAGE.name())
				.resLinks(eCase.getFiles())
				.status(NEW.display())
				.build();
	}
}
