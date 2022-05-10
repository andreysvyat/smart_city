package ru.krd.smc.model.entity.embeded;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OKVED {
	private char division;
	private byte clas;
	private byte subclas;
	private byte group;
	private byte subGroup;
	private byte type;
}
