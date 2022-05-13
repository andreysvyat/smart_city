package ru.krd.smc.model.resp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.krd.smc.model.enums.UserType;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {
	private String id;
	private String fio;
	private UserType type;
	private String orgName;
	private String email;
	private String login;
	private List<String> cases;
}
