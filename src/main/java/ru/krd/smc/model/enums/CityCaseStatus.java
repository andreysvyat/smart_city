package ru.krd.smc.model.enums;

public enum CityCaseStatus implements Displayable {
	NEW,
	IN_PROGRESS,
	DECLINED,
	ACCEPTED,
	CLOSED,
	AT_CONTRACTOR,
	HOLD;

	@Override
	public String display() {
		return name();
	}
}
