package ru.krd.smc.service.processors;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import ru.krd.smc.dba.UserRepo;
import ru.krd.smc.model.entity.User;
import ru.krd.smc.service.UserProcessor;

import java.util.UUID;

@Service
@Log4j2
@RequiredArgsConstructor
public class BaseUserProcessor implements UserProcessor {

	private final UserRepo repo;

	@Override
	public User findUser(UUID userId) {
		return repo.findById(userId).orElseThrow(() -> new RuntimeException("User not found " + userId));
	}
}
