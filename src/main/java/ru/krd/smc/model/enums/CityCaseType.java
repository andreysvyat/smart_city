package ru.krd.smc.model.enums;

import lombok.RequiredArgsConstructor;
import ru.krd.smc.model.entity.embeded.OKVED;

@RequiredArgsConstructor
public enum CityCaseType implements Displayable {
	UNKNOWN("0.0.0", ' '),
	GARBAGE("81.29.9", 'N'),
	INFRASTRUCTURE("71.11.1", 'M');

	private final String okvedCode;
	private final char div;

	public OKVED getOKVED(){
		String[] arr = okvedCode.split("\\.");
		return OKVED.builder()
				.division(div)
				.clas(Byte.parseByte(arr[0]))
				.subclas(Byte.parseByte(arr[1].substring(0, 1)))
				.group(Byte.parseByte(arr[1].substring(1)))
				.subGroup(Byte.parseByte(arr[2].substring(0,1)))
				.build();
	}

	@Override
	public String display() {
		return name();
	}
}
