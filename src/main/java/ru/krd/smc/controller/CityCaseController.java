package ru.krd.smc.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import ru.krd.smc.model.resp.CityCaseShortResp;

@Log4j2
@RestController
@RequestMapping("/cases")
public class CityCaseController {

    @GetMapping
    public Page<CityCaseShortResp> getAll(String filter, Pageable pageable){
        log.trace(filter);
        log.trace(pageable);
        return Page.empty();
    }

}
