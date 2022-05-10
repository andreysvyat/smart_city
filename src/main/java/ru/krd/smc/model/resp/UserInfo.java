package ru.krd.smc.model.resp;

import lombok.*;
import ru.krd.smc.model.enums.UserType;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {
	private String id;
	private String fio;
	private UserType type;
	private String orgName;
}
