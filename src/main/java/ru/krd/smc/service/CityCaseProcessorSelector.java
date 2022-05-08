package ru.krd.smc.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class CityCaseProcessorSelector {
	private final Map<String, CityCaseProcessor> cityCaseProcessors;

	public CityCaseProcessor select(String type){
		return cityCaseProcessors.get(type);
	}
}
