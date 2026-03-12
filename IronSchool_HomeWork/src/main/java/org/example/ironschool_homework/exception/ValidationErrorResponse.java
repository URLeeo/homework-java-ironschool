package org.example.ironschool_homework.exception;

import java.time.LocalDateTime;
import java.util.List;

public class ValidationErrorResponse {

    private int status;
    private String error;
    private List<String> messages;
    private LocalDateTime timestamp;

    public ValidationErrorResponse(int status, String error, List<String> messages, LocalDateTime timestamp) {
        this.status = status;
        this.error = error;
        this.messages = messages;
        this.timestamp = LocalDateTime.now();
    }

    public int getStatus() {
        return status;
    }

    public String getError() {
        return error;
    }

    public List<String> getMessages() {
        return messages;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
