package ru.krd.smc.model.rq;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NewCityCase {
	private UUID userId;
	private String description;
	private String location;
	private List<String> images;
	private ZonedDateTime initedOn;
	private String type;
}
