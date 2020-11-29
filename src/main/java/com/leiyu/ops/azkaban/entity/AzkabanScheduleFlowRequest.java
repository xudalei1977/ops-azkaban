package com.leiyu.ops.azkaban.entity;

import lombok.Getter;
import lombok.Setter;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * An entity on Azkaban scheduler flow request
 * Reference - https://azkaban.github.io/azkaban/docs/latest/#api-flexible-schedule
 *
 * @auther Pitt
 * @date 2020-11-27
 */
@Getter
@Setter
public class AzkabanScheduleFlowRequest {

    private String ajax = "scheduleCronFlow";
    private String sessionId;
    private String projectName;
    private String flow;
    private String cronExpression;

    public String parse() {
        List<BasicNameValuePair> pairs = new ArrayList();
        pairs.add(new BasicNameValuePair("ajax", this.ajax));
        pairs.add(new BasicNameValuePair("session.id", this.sessionId));
        pairs.add(new BasicNameValuePair("projectName", this.projectName));
        pairs.add(new BasicNameValuePair("flow", this.flow));
        pairs.add(new BasicNameValuePair("cronExpression", this.cronExpression));

        try {
            String url = "/schedule?" + EntityUtils.toString(new UrlEncodedFormEntity(pairs, "UTF-8"));
            return url;
        } catch (Throwable e) {
            if (e instanceof Error) {
                throw (Error) e;
            } else if (e instanceof RuntimeException) {
                throw (RuntimeException) e;
            } else {
                throw new RuntimeException((Exception) e);
            }
        }
    }

}