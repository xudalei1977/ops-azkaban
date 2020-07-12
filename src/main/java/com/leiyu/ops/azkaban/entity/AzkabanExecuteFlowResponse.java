package com.leiyu.ops.azkaban.entity;

/**
 * An entity on Azkaban execution flow response
 *
 * @auther Pitt
 * @date 2020-03-14
 */
public class AzkabanExecuteFlowResponse extends AzkabanResponse {

    private String flow;
    private String execId;
    private String project;

    @Override
    public boolean failure() {
        return this.execId == null || "".equals(this.execId);
    }

    @Override
    public boolean error() {
        return this.failure();
    }

    public String getFlow() {
        return flow;
    }

    public void setFlow(String flow) {
        this.flow = flow;
    }

    public String getExecId() {
        return execId;
    }

    public void setExecId(String execId) {
        this.execId = execId;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }
}
