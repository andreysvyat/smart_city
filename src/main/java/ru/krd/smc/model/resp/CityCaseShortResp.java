package ru.krd.smc.model.resp;

import lombok.Data;

@Data
public class CityCaseShortResp {
    private String id;
    private String address;
    private String author;
    private String status;
    private String resLink;
}
