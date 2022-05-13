package ru.krd.smc.model.resp;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CityCaseInfo {
	private String id;
	private String address;
	private String author;
	private String status;
	private List<String> resLinks;
	private String type;
}
