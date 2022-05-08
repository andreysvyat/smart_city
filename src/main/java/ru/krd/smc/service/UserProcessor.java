package ru.krd.smc.service;

import ru.krd.smc.model.entity.User;

import java.util.UUID;

public interface UserProcessor {
	User findUser(UUID userId);
}
