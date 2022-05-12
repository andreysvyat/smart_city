package ru.krd.smc.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;
import ru.krd.smc.model.resp.UserInfo;
import ru.krd.smc.model.rq.NewUser;
import ru.krd.smc.service.UserProcessor;

import java.util.List;
import java.util.UUID;

@Log4j2
@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

	private final UserProcessor userProcessor;

	@PostMapping
	public UserInfo addUser(@RequestBody NewUser user) {
		return userProcessor.addNewUser(user);
	}

	@GetMapping("/{id}")
	public UserInfo getUser(@PathVariable String id){
		return userProcessor.getUserInfo(UUID.fromString(id));
	}

	@GetMapping
	public List<UserInfo> getAll(){
		return userProcessor.getAll();
	}
}
