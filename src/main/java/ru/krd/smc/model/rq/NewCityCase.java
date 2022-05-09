package ru.krd.smc.model.rq;

import lombok.Data;
import ru.krd.smc.model.enums.CityCaseType;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

@Data
public class NewCityCase {
	private UUID userId;
	private String description;
	private String location;
	private List<String> images;
	private ZonedDateTime initedOn;
	private CityCaseType cityCaseType;
}
