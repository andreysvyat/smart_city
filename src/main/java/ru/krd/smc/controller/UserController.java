package ru.krd.smc.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.krd.smc.dba.UserRepo;
import ru.krd.smc.model.entity.User;
import ru.krd.smc.model.rq.NewUser;

@Log4j2
@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserRepo repo;

    @PostMapping
    public String addUser(@RequestBody NewUser user) {
        return repo.save(User.builder()
                                 .firstName(user.getFirstName())
                                 .lastName(user.getLastName())
                                 .login(user.getLogin())
                                 .middleName(user.getMiddleName())
                                 .type(user.getType())
                                 .build())
                .getUuid();
    }
}
