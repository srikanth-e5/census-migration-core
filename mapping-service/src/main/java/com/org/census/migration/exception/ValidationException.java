package com.org.census.migration.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class ValidationException extends RuntimeException {

    private final List<String> errors = new ArrayList<>();
    private final List<String> warnings = new ArrayList<>();

    public ValidationException() {
        super();
    }

    public ValidationException(String message) {
        errors.add(message);
    }

    public ValidationException(List<String> errors) {
        this.errors.addAll(errors);
    }

    public ValidationException(List<String> errors, List<String> warnings) {
        this.errors.addAll(errors);
        this.warnings.addAll(warnings);
    }
}
