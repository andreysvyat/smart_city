package ru.krd.smc.service;

import ru.krd.smc.model.entity.User;
import ru.krd.smc.model.resp.UserInfo;
import ru.krd.smc.model.rq.NewUser;

import java.util.List;
import java.util.UUID;

public interface UserProcessor {
	User findUser(UUID userId);
	List<User> getOperators();
	UserInfo addNewUser(NewUser user);
	UserInfo getUserInfo(UUID userId);
}
