package ru.krd.smc.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import ru.krd.smc.model.enums.CityCaseProcessorType;
import ru.krd.smc.model.resp.CityCaseShortResp;
import ru.krd.smc.model.resp.CreatedCityCase;
import ru.krd.smc.model.rq.NewCityCase;
import ru.krd.smc.service.CityCaseProcessorSelector;

@Log4j2
@RequiredArgsConstructor
@RestController
@RequestMapping("/case")
public class CityCaseController {

	private final CityCaseProcessorSelector cityCaseProcessorSelector;

	@GetMapping
	public Page<CityCaseShortResp> getAll(String filter, Pageable pageable) {
		return Page.empty();
	}

	@PostMapping
	public CreatedCityCase newCityCase(
			@RequestParam CityCaseProcessorType type,
			@RequestBody NewCityCase cityCase
	){
		return cityCaseProcessorSelector.select(type.name()).createCityCase(cityCase);
	}

}
