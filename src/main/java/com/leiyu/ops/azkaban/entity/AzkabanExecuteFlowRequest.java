package com.leiyu.ops.azkaban.entity;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.codec.net.URLCodec;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * An entity on Azkaban execution flow request
 *
 * @auther Pitt
 * @date 2020-03-14
 */
public class AzkabanExecuteFlowRequest {

    private String ajax = "executeFlow";
    private String sessionId;
    private String project;
    private String flow;
    private String[] disabled;
    private String successEmails;
    private String failureEmails;
    private String successEmailsOverride;
    private String failureEmailsOverride;
    private String notifyFailureFirst;
    private String notifyFailureLast;
    private String failureAction;
    private String concurrentOption;
    private Map<String, Object> flowOverride;

    public String parse() {
        List<BasicNameValuePair> pairs = new ArrayList();
        pairs.add(new BasicNameValuePair("ajax", this.ajax));
        pairs.add(new BasicNameValuePair("session.id", this.sessionId));
        pairs.add(new BasicNameValuePair("project", this.project));
        pairs.add(new BasicNameValuePair("flow", this.flow));

        if (this.disabled != null && this.disabled.length > 0) {
            try {
                pairs.add(new BasicNameValuePair("disabled", (new ObjectMapper()).writeValueAsString(this.disabled)));
            } catch (Exception e) {
                new Exception("disabled参数转json出错");
            }
        }

        if (this.successEmails != null && !"".equals(this.successEmails)) {
            pairs.add(new BasicNameValuePair("successEmails", this.successEmails));
        }
        if (this.failureEmails != null && !"".equals(this.failureEmails)) {
            pairs.add(new BasicNameValuePair("failureEmails", this.successEmails));
        }
        if (this.successEmailsOverride != null && !"".equals(this.successEmailsOverride)) {
            pairs.add(new BasicNameValuePair("successEmailsOverride", this.successEmailsOverride));
        }
        if (this.failureEmailsOverride != null && !"".equals(this.failureEmailsOverride)) {
            pairs.add(new BasicNameValuePair("failureEmailsOverride", this.failureEmailsOverride));
        }
        if (this.notifyFailureFirst != null && !"".equals(this.notifyFailureFirst)) {
            pairs.add(new BasicNameValuePair("notifyFailureFirst", this.notifyFailureFirst));
        }
        if (this.notifyFailureLast != null && !"".equals(this.notifyFailureLast)) {
            pairs.add(new BasicNameValuePair("notifyFailureLast", this.notifyFailureLast));
        }
        if (this.failureAction != null && !"".equals(this.failureAction)) {
            pairs.add(new BasicNameValuePair("failureAction", this.failureAction));
        }
        if (this.concurrentOption != null && !"".equals(this.concurrentOption)) {
            pairs.add(new BasicNameValuePair("concurrentOption", this.concurrentOption));
        }
        try {
            String url = "/executor?" + EntityUtils.toString(new UrlEncodedFormEntity(pairs, "UTF-8"));
            String key;
            String value;
            URLCodec urlCodec;
            if (this.flowOverride != null && this.flowOverride.size() > 0) {
                for (Iterator i7s = this.flowOverride.entrySet().iterator(); i7s.hasNext();
                     url = url + "&flowOverride[" + key + "]=" + urlCodec.encode(value)) {
                    Map.Entry<String, Object> flowOverrideEntry = (Map.Entry) i7s.next();
                    key = (String) flowOverrideEntry.getKey();
                    value = String.valueOf(flowOverrideEntry.getValue());
                    urlCodec = new URLCodec();
                }
            }

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

    public String getAjax() {
        return ajax;
    }

    public void setAjax(String ajax) {
        this.ajax = ajax;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getFlow() {
        return flow;
    }

    public void setFlow(String flow) {
        this.flow = flow;
    }

    public String[] getDisabled() {
        return disabled;
    }

    public void setDisabled(String[] disabled) {
        this.disabled = disabled;
    }

    public String getSuccessEmails() {
        return successEmails;
    }

    public void setSuccessEmails(String successEmails) {
        this.successEmails = successEmails;
    }

    public String getFailureEmails() {
        return failureEmails;
    }

    public void setFailureEmails(String failureEmails) {
        this.failureEmails = failureEmails;
    }

    public String getSuccessEmailsOverride() {
        return successEmailsOverride;
    }

    public void setSuccessEmailsOverride(String successEmailsOverride) {
        this.successEmailsOverride = successEmailsOverride;
    }

    public String getFailureEmailsOverride() {
        return failureEmailsOverride;
    }

    public void setFailureEmailsOverride(String failureEmailsOverride) {
        this.failureEmailsOverride = failureEmailsOverride;
    }

    public String getNotifyFailureFirst() {
        return notifyFailureFirst;
    }

    public void setNotifyFailureFirst(String notifyFailureFirst) {
        this.notifyFailureFirst = notifyFailureFirst;
    }

    public String getNotifyFailureLast() {
        return notifyFailureLast;
    }

    public void setNotifyFailureLast(String notifyFailureLast) {
        this.notifyFailureLast = notifyFailureLast;
    }

    public String getFailureAction() {
        return failureAction;
    }

    public void setFailureAction(String failureAction) {
        this.failureAction = failureAction;
    }

    public String getConcurrentOption() {
        return concurrentOption;
    }

    public void setConcurrentOption(String concurrentOption) {
        this.concurrentOption = concurrentOption;
    }

    public Map<String, Object> getFlowOverride() {
        return flowOverride;
    }

    public void setFlowOverride(Map<String, Object> flowOverride) {
        this.flowOverride = flowOverride;
    }
}