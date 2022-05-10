package ru.krd.smc.service.client.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import ru.krd.smc.model.entity.CityCase;
import ru.krd.smc.model.entity.User;
import ru.krd.smc.service.UserProcessor;
import ru.krd.smc.service.client.Notifier;

import java.util.stream.Collectors;

@Log4j2
@Service
@RequiredArgsConstructor
public class MockNotifier implements Notifier {

	private final UserProcessor userProcessor;

	@Override
	public void notifyOperators(CityCase cityCaseId) {
		log.warn("Notify users [{}] that city case {} is changed or created",
		         userProcessor.getOperators().stream().map(User::getLogin).collect(Collectors.joining(", ")),
		         cityCaseId.toString());
	}
}
