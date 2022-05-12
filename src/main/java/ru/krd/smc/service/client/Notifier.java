package ru.krd.smc.service.client;

import ru.krd.smc.model.entity.CityCase;
import ru.krd.smc.model.entity.ContractorInfo;

public interface Notifier {
	void notifyOperators(CityCase cityCaseId);
	void notifyContractor(ContractorInfo info);
}
