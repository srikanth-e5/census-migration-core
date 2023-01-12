package com.org.census.migration.controller;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class BatchController implements BatchApi {

    @Override
    public String get() {
        return "Welcome";
    }
}
