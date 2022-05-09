package ru.krd.smc.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import ru.krd.smc.model.resp.CityCaseShortResp;
import ru.krd.smc.model.resp.CreatedCityCase;
import ru.krd.smc.model.rq.NewCityCase;
import ru.krd.smc.service.CityCaseProcessor;

@Log4j2
@RequiredArgsConstructor
@RestController
@RequestMapping("/case")
public class CityCaseController {

	private final CityCaseProcessor caseProcessor;

	@GetMapping
	public Page<CityCaseShortResp> getAll(Pageable pageable) {
		return caseProcessor.getCases(pageable);
	}

	@PostMapping
	public CreatedCityCase newCityCase(@RequestBody NewCityCase cityCase){
		return caseProcessor.createCityCase(cityCase);
	}

}
