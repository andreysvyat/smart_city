package ru.krd.smc.service;

import ru.krd.smc.model.resp.CreatedCityCase;
import ru.krd.smc.model.rq.NewCityCase;

public interface CityCaseProcessor {
	CreatedCityCase createCityCase(NewCityCase cityCase);
}
