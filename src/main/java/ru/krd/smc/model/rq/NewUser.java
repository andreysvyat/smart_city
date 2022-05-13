package ru.krd.smc.model.rq;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.krd.smc.model.enums.UserType;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NewUser {
	private String login;
	private UserType type;
	private String firstName;
	private String middleName;
	private String lastName;
	private String email;
}
