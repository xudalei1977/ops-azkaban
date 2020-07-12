package com.leiyu.ops.azkaban.entity;

/**
 * An entity on Azkaban login response
 *
 * @auther Pitt
 * @date 2020-03-14
 */
public class AzkabanLoginResponse extends AzkabanResponse {

    private String sessionId;

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    @Override
    public boolean failure() {
        return this.sessionId == null || "".equals(this.sessionId);
    }

    @Override
    public boolean error() {
        return this.failure();
    }
}