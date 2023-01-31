package com.org.census.migration.exception;

import lombok.Data;

@Data
public class ServiceException extends RuntimeException {
	
	static final long serialVersionUID = -7014897190745766939L;

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceException(String message) {
        super(message);
    }

}