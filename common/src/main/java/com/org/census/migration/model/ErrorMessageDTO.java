package com.org.census.migration.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
public class ErrorMessageDTO {

    @NonNull
    @NotBlank
    private final String message;
}
