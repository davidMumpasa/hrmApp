package com.xgileit.hrm.exception.response;

import java.io.Serializable;
import java.sql.Timestamp;

public class ErrorResponse implements Serializable {

    private static final long serialVersionUID = 1L;
    private Timestamp timestamp;
    private int status;
    private String error;
    private String message;

    public ErrorResponse() {
        super();
    }

    public ErrorResponse(final Timestamp timestamp, final int status, final String error,
            final String message) {
        super();
        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
        this.message = message;
    }

    public String getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

    public int getStatus() {
        return status;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setError(final String error) {
        this.error = error;
    }

    public void setMessage(final String message) {
        this.message = message;
    }

    public void setStatus(final int status) {
        this.status = status;
    }

    public void setTimestamp(final Timestamp timestamp) {
        this.timestamp = timestamp;
    }

}
