package com.example.codeseek_testtask.model.dto;

import java.time.ZonedDateTime;

public class ErrorResponseDTO {
    private final ZonedDateTime timestamp;
    private final String message;
    private final String path;
    private final String method;

    public ErrorResponseDTO(String message, String path, String method) {
        this.timestamp = ZonedDateTime.now();
        this.message = message;
        this.path = path;
        this.method = method;
    }

    public ZonedDateTime getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    public String getPath() {
        return path;
    }

    public String getMethod() {
        return method;
    }
}
