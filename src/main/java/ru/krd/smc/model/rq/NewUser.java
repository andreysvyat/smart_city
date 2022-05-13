package ru.krd.smc.model.rq;

import lombok.Data;
import ru.krd.smc.model.enums.UserType;

@Data
public class NewUser {
	private String login;
	private UserType type;
	private String firstName;
	private String middleName;
	private String lastName;
	private String email;
}
