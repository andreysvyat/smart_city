package ru.krd.smc.model.rq;

import lombok.Data;

import java.util.List;

@Data
public class NewCityCase {
    private String userId;
    private String description;
    private String location;
    private List<String> images;
}
