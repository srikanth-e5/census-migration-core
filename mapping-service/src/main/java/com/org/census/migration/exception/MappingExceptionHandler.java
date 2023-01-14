package com.org.census.migration.exception;

import com.org.census.migration.constant.ErrorCode;
import com.org.census.migration.model.ErrorDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class MappingExceptionHandler {

    @ExceptionHandler({ResourceNotFoundException.class})
    public ResponseEntity<ErrorDTO> resourceNotFoundExceptionHandler(Exception ex) {
        logException(ex);
        return errorResponseEntity(ErrorCode.E1030001, ex.getMessage());
    }

    private void logException(Exception ex) {
        log.error("Mapping Service Exception - ", ex);
    }

    private ResponseEntity<ErrorDTO> errorResponseEntity(ErrorCode errorCode) {
        return errorResponseEntity(errorCode, "");
    }

    private ResponseEntity<ErrorDTO> errorResponseEntity(ErrorCode errorCode, String detail) {
        return ResponseEntity.status(errorCode.getHttpStatus()).body(errorDto(errorCode, detail));
    }

    private ErrorDTO errorDto(ErrorCode errorCode, String detail) {
        return new ErrorDTO(errorCode, detail, null);
    }
}
