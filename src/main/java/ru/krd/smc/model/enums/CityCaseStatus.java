package ru.krd.smc.model.enums;

public enum CityCaseStatus implements Displayable {
	NEW,
	IN_PROGRESS,
	AT_OPERATOR,
	AT_CONTRACTOR,
	ACCEPTED,
	ON_HOLD,
	CLOSED;

	@Override
	public String display() {
		return name();
	}
}
