package ru.krd.smc.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.krd.smc.model.resp.CityCaseShortResp;
import ru.krd.smc.model.resp.CreatedCityCase;
import ru.krd.smc.model.rq.NewCityCase;

public interface CityCaseProcessor {
	CreatedCityCase createCityCase(NewCityCase cityCase);
	Page<CityCaseShortResp> getCases(Pageable pageable);
}
