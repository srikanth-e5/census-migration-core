package com.org.census.migration.controller;

import org.springframework.web.bind.annotation.GetMapping;

public interface BatchApi {

    @GetMapping("/api/welcome")
    public String get();
}
