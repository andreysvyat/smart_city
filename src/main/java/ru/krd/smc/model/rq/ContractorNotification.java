package ru.krd.smc.model.rq;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.krd.smc.model.enums.CityCaseStatus;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ContractorNotification {
	private String contractorId;
	private String cityCaseId;
	private CityCaseStatus status;
}
