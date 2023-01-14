package com.org.census.migration.constant;

import org.springframework.http.HttpStatus;

public enum ErrorCode {

    E1030000("Bad request. Please refer to the API documentation", HttpStatus.BAD_REQUEST),
    E1030001("Resource not found", HttpStatus.NOT_FOUND),
    E1030003("This entity already exists", HttpStatus.CONFLICT),
    E1030004("Not authorized to perform this operation", HttpStatus.FORBIDDEN),
    E1030005("System failure. Try after some time", HttpStatus.INTERNAL_SERVER_ERROR),
    E1030006("Not authenticated to perform this operation", HttpStatus.UNAUTHORIZED),
    E1030007("Too many requests", HttpStatus.TOO_MANY_REQUESTS),
    E1030008("Unsupported content-type requested", HttpStatus.BAD_REQUEST), //406 (NOT_ACCEPTABLE) -> 400
    E1030009("Method not allowed", HttpStatus.METHOD_NOT_ALLOWED); //405

    private final String description;
    private final HttpStatus httpStatus;

    ErrorCode(String desc, HttpStatus httpStatus) {
        this.description = desc;
        this.httpStatus = httpStatus;
    }

    public String getDescription() {
        return description;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
