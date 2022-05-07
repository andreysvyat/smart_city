package ru.krd.smc.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.krd.smc.model.resp.CityCaseShortResp;

@Log4j2
@RequiredArgsConstructor
@RestController
@RequestMapping("/cases")
public class CityCaseController {

    @GetMapping
    public Page<CityCaseShortResp> getAll(String filter, Pageable pageable){
        return Page.empty();
    }

}
