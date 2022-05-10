package ru.krd.smc.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.krd.smc.model.enums.CityCaseStatus;
import ru.krd.smc.model.enums.CityCaseType;
import ru.krd.smc.model.resp.CityCaseShortResp;
import ru.krd.smc.model.resp.CreatedCityCase;
import ru.krd.smc.model.rq.NewCityCase;

import java.util.UUID;

public interface CityCaseProcessor {
	CreatedCityCase createCityCase(NewCityCase cityCase);
	Page<CityCaseShortResp> getCases(Pageable pageable);
	Page<CityCaseShortResp> getCases(Pageable pageable, UUID userId);
	Page<CityCaseShortResp> getCases(Pageable pageable, CityCaseStatus status, CityCaseType type);
}
