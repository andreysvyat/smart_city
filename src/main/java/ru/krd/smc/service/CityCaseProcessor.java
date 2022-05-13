package ru.krd.smc.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.krd.smc.model.enums.CityCaseStatus;
import ru.krd.smc.model.resp.CityCaseShortResp;
import ru.krd.smc.model.resp.CityCaseInfo;
import ru.krd.smc.model.rq.ContractorNotification;
import ru.krd.smc.model.rq.NewCityCase;

import java.util.UUID;

public interface CityCaseProcessor {
	CityCaseInfo createCityCase(NewCityCase cityCase);
	CityCaseInfo handleContractorNotification(ContractorNotification notification);
	Page<CityCaseShortResp> getCases(Pageable pageable);
	Page<CityCaseShortResp> getCases(Pageable pageable, UUID userId);
	Page<CityCaseShortResp> getCases(Pageable pageable, CityCaseStatus status, String type);
}
