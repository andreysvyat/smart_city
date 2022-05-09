package ru.krd.smc.model.resp;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CityCaseShortResp {
	private String id;
	private String address;
	private String author;
	private String status;
}
