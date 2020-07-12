package com.leiyu.ops.azkaban.entity;

/**
 * An entity on Azkaban response
 *
 * @auther Pitt
 * @date 2020-03-14
 */
public abstract class AzkabanResponse {

    private String message;
    private String status;
    private String error;

    public abstract boolean failure();

    public abstract boolean error();

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}