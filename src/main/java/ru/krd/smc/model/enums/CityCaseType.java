package ru.krd.smc.model.enums;

public enum CityCaseType implements Displayable {
	GARBAGE,
	INFRASTRUCTURE;

	@Override
	public String display() {
		return name();
	}
}
