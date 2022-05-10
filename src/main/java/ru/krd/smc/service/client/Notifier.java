package ru.krd.smc.service.client;

import ru.krd.smc.model.entity.CityCase;

public interface Notifier {
	void notifyOperators(CityCase cityCaseId);
}
