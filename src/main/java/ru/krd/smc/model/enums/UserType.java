package ru.krd.smc.model.enums;

public enum UserType implements Displayable{
	CITIZEN,
	OPERATOR,
	CONTRACTOR;

	@Override
	public String display() {
		return name();
	}
}
