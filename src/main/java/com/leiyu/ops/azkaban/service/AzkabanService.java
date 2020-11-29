package com.leiyu.ops.azkaban.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.leiyu.ops.azkaban.entity.AzkabanScheduleFlowRequest;
import com.leiyu.ops.azkaban.entity.AzkabanScheduleFlowResponse;
import com.leiyu.ops.common.entity.GeneralResult;
import com.leiyu.ops.common.exception._400CommonException;
import com.leiyu.ops.common.exception._500CommonException;
import com.leiyu.ops.common.util.HttpUtils;
import com.leiyu.ops.azkaban.config.AzkabanConfig;
import com.leiyu.ops.azkaban.entity.AzkabanExecuteFlowRequest;
import com.leiyu.ops.azkaban.entity.AzkabanExecuteFlowResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * A service which provides operations on Azkaban
 * 
 * @auther Pitt
 * @date 2020-03-15
 */
@Service
@Slf4j
public class AzkabanService {

    private static final String CONTENT_TYPE = "application/x-www-form-urlencoded; charset=utf-8";

    private static final String X_REQUESTED_WITH = "XMLHttpRequest";

    private static String AZK_SUCCESS = "success";

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private AzkabanConfig azkabanConfig;

    /**
     * Login Azkaban
     *
     * @return
     * @throws Exception
     */
    public String login() {

        HttpHeaders hs = new HttpHeaders();
        hs.add("Content-Type", CONTENT_TYPE);
        hs.add("X-Requested-With", X_REQUESTED_WITH);

        LinkedMultiValueMap<String, String> linkedMultiValueMap = new LinkedMultiValueMap<String, String>();

        linkedMultiValueMap.add("action", "login");
        linkedMultiValueMap.add("username", azkabanConfig.getAzkUsername());
        linkedMultiValueMap.add("password", azkabanConfig.getAzkPassword());

        HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(linkedMultiValueMap, hs);
        String result = restTemplate.postForObject(azkabanConfig.getAzkUrl(), httpEntity, String.class);
        JSONObject jsonObject = JSONObject.parseObject(result);

        if (!AZK_SUCCESS.equals(jsonObject.get("status").toString())) {
            log.error("AzkabanService:: login(): failed to login Azkaban, username={}, password={}, url={}",
                    azkabanConfig.getAzkUsername(), azkabanConfig.getAzkPassword(), azkabanConfig.getAzkUrl());
            throw new _400CommonException("Failed to login Azkaban");
        }

        return jsonObject.get("session.id").toString();
    }

    /**
     * Execute Azkaban flow request at once
     *
     * @param request
     * @return
     */
    private AzkabanExecuteFlowResponse executeAtOnce(AzkabanExecuteFlowRequest request) {

        log.info("AzkabanService:: executeAtOnce(): AzkabanExecuteFlowRequest url={}",
                azkabanConfig.getAzkUrl());

        String jsonString = HttpUtils.doGet(azkabanConfig.getAzkUrl(), request.parse());

        AzkabanExecuteFlowResponse azkabanExecuteFlowResponse;
        try {
            azkabanExecuteFlowResponse = JSON.parseObject(jsonString, AzkabanExecuteFlowResponse.class);
        } catch (Exception e) {
            azkabanExecuteFlowResponse = new AzkabanExecuteFlowResponse();
            azkabanExecuteFlowResponse.setProject(request.getProject());
            azkabanExecuteFlowResponse.setFlow(request.getFlow());
            azkabanExecuteFlowResponse.setError(jsonString);
        }

        return azkabanExecuteFlowResponse;
    }

    /**
     * Execute Azkaban flowName with given params
     *
     * @param projectName
     * @param flowName
     */
    public GeneralResult executeAzkabanFlow(String projectName, String flowName, Map<String, Object> paramMap) {

        GeneralResult generalResult = new GeneralResult();

        AzkabanExecuteFlowRequest request = new AzkabanExecuteFlowRequest();
        request.setFlowOverride(paramMap);
        request.setProject(projectName);
        request.setFlow(flowName);
        request.setSessionId(login());
        request.setConcurrentOption("ignore");

        AzkabanExecuteFlowResponse response = executeAtOnce(request);

        // Execution failed
        if (response.getError() != null){
            generalResult.error(new _400CommonException("AzkabanExecuteFlowResponse returned error=" +
                    response.getError()));
        }else{
            generalResult.success();
        }

        return generalResult;
    }

    /**
     * Execute Azkaban flow request using Cron expression
     *
     * @param request
     * @return
     */
    private AzkabanScheduleFlowResponse scheduleWithCron(AzkabanScheduleFlowRequest request) {

        log.info("AzkabanService:: scheduleWithCron(): AzkabanScheduleFlowRequest url={}",
                azkabanConfig.getAzkUrl());

        String jsonString = HttpUtils.doGet(azkabanConfig.getAzkUrl(), request.parse());

        AzkabanScheduleFlowResponse azkabanScheduleFlowResponse;
        try {
            azkabanScheduleFlowResponse = JSON.parseObject(jsonString, AzkabanScheduleFlowResponse.class);
        } catch (Exception e) {
            azkabanScheduleFlowResponse = new AzkabanScheduleFlowResponse();
            azkabanScheduleFlowResponse.setError(jsonString);
        }

        return azkabanScheduleFlowResponse;
    }

    /**
     * Schedule Azkaban flow with given params
     *
     * @param projectName
     * @param flowName
     * @param cron The instance of quartz-schedule cron format, for example, "0 0 12 ? * WED" means "every Wednesday at 12:00:00 pm"
     *             These sub-expression are separated with white-space, and represent:
     *                         Seconds
     *                         Minutes
     *                         Hours
     *                         Day-of-Month
     *                         Month
     *                         Day-of-Week
     *                         Year (optional field)
     */
    public GeneralResult scheduleAzkabanCronFlow(String projectName, String flowName, String cron) {

        GeneralResult generalResult = new GeneralResult();

        AzkabanScheduleFlowRequest request = new AzkabanScheduleFlowRequest();
        request.setProjectName(projectName);
        request.setFlow(flowName);
        request.setSessionId(login());
        request.setCronExpression(cron);

        AzkabanScheduleFlowResponse response = scheduleWithCron(request);

        // Execution failed
        if (response.getError() != null){
            generalResult.error(new _400CommonException("AzkabanScheduleFlowResponse returned error=" +
                    response.getError()));
        }else{
            generalResult.success();
        }

        return generalResult;
    }

    // - - - Following methods are for test - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -- - - - - -

    private static RestTemplate getRestTemplateForMain() {
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setConnectTimeout(60000);
        requestFactory.setReadTimeout(120000);
        RestTemplate restTemplate = new RestTemplate(requestFactory);
        return restTemplate;
    }

    private String loginForMain() {
        String loginUrl = "http://common-1:8087";
        String userName = "azkaban";
        String password = "azkaban";

        HttpHeaders hs = new HttpHeaders();
        hs.add("Content-Type", CONTENT_TYPE);
        hs.add("X-Requested-With", X_REQUESTED_WITH);

        LinkedMultiValueMap<String, String> linkedMultiValueMap = new LinkedMultiValueMap<String, String>();

        linkedMultiValueMap.add("action", "login");
        linkedMultiValueMap.add("username", userName);
        linkedMultiValueMap.add("password", password);

        HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(linkedMultiValueMap, hs);
        String result = getRestTemplateForMain().postForObject(loginUrl, httpEntity, String.class);
        JSONObject jsonObject = JSONObject.parseObject(result);

        if (!AZK_SUCCESS.equals(jsonObject.get("status").toString())) {
            log.error("AzkabanService:: loginForMain(): failed to login Azkaban, username={}, password={}, url={}",
                    userName, password, loginUrl);
            throw new _400CommonException("Failed to login Azkaban");
        }

        return jsonObject.get("session.id").toString();
    }

    private AzkabanExecuteFlowResponse executeCalculationForMain(AzkabanExecuteFlowRequest request) {
        String azkUrl = "http://common-1:8087";

        log.info("AzkabanService:: executeCalculationForMain(): AzkabanExecuteFlowRequest url={}",
                azkUrl);

        String jsonString = HttpUtils.doGet(azkUrl, request.parse());
        //getRestTemplateForMain().getForObject()

        AzkabanExecuteFlowResponse azkabanExecuteFlowResponse;
        try {
            azkabanExecuteFlowResponse = JSON.parseObject(jsonString, AzkabanExecuteFlowResponse.class);
        } catch (Exception e) {
            azkabanExecuteFlowResponse = new AzkabanExecuteFlowResponse();
            azkabanExecuteFlowResponse.setProject(request.getProject());
            azkabanExecuteFlowResponse.setFlow(request.getFlow());
            azkabanExecuteFlowResponse.setError(jsonString);
        }

        return azkabanExecuteFlowResponse;
    }

    public GeneralResult executeAzkabanFlowForMain(String project, String flow, Map<String, Object> paramMap) {

        GeneralResult generalResult = new GeneralResult();

        AzkabanExecuteFlowRequest request = new AzkabanExecuteFlowRequest();
        request.setFlowOverride(paramMap);
        request.setProject(project);
        request.setFlow(flow);
        request.setSessionId(loginForMain());
        request.setConcurrentOption("ignore");
        // Added
        request.setAjax("executeFlow");

        AzkabanExecuteFlowResponse response = executeCalculationForMain(request);

        // Execution failed
        if (response.getError() != null){
            generalResult.error(new _400CommonException("AzkabanExecuteFlowResponse returned error=" +
                    response.getError()));
        }else{
            generalResult.success();
        }

        return generalResult;
    }

    private AzkabanScheduleFlowResponse scheduleWithCronForMain(AzkabanScheduleFlowRequest request) {
        String azkUrl = "http://common-1:8087";

        log.info("AzkabanService:: scheduleWithCronForMain(): AzkabanScheduleFlowRequest url={}",
                azkUrl);

        String jsonString = HttpUtils.doGet(azkUrl, request.parse());

        AzkabanScheduleFlowResponse azkabanScheduleFlowResponse;
        try {
            azkabanScheduleFlowResponse = JSON.parseObject(jsonString, AzkabanScheduleFlowResponse.class);
        } catch (Exception e) {
            azkabanScheduleFlowResponse = new AzkabanScheduleFlowResponse();
            azkabanScheduleFlowResponse.setError(jsonString);
        }

        return azkabanScheduleFlowResponse;
    }

    public GeneralResult scheduleAzkabanCronFlowForMain(String projectName, String flowName, String cron) {

        GeneralResult generalResult = new GeneralResult();

        AzkabanScheduleFlowRequest request = new AzkabanScheduleFlowRequest();
        request.setProjectName(projectName);
        request.setFlow(flowName);
        request.setSessionId(loginForMain());
        request.setCronExpression(cron);

        AzkabanScheduleFlowResponse response = scheduleWithCronForMain(request);

        // Execution failed
        if (response.getError() != null){
            generalResult.error(new _400CommonException("AzkabanScheduleFlowResponse returned error=" +
                    response.getError()));
        }else{
            generalResult.success();
        }

        return generalResult;
    }

    public static void main(String[] args) {
        //String projectName = "Pitt_Test";
        //String flowName = "two";
        String projectName = "Persona";
        String flowName = "update";
        Map<String, Object> paramMap = Maps.newHashMap();
        // An expression to create a trigger that simply fires every 1 minute
        //String cron = "0 0/1 * * * ?";
        String batchNo = "BASIC_TAG202011290001";
        paramMap.put("batchNo", batchNo);

        log.info("AzkabanService:: main(): started, " +
                        "projectName={}, flowName={}, paramMap={}",
                projectName, flowName, paramMap);

        GeneralResult result = new GeneralResult();

        try {
            result = new AzkabanService().executeAzkabanFlowForMain(projectName, flowName, paramMap);
            //result = new AzkabanService().scheduleAzkabanCronFlowForMain(projectName, flowName, cron);
            result.success();
        } catch (_400CommonException e1) {
            log.error("AzkabanService:: main(): failed, " +
                            "projectName={}, flowName={}, paramMap={}, exception={}",
                    projectName, flowName, paramMap, e1.getMessage());
            result.error(new _400CommonException(e1.getMessage()));
        } catch (Exception e) {
            log.error("AzkabanService:: main(): failed, " +
                            "projectName={}, flowName={}, paramMap={}, exception={}",
                    projectName, flowName, paramMap, e.getMessage());
            result.error(new _500CommonException(e.getMessage()));
        }

        log.info("AzkabanService:: main(): ended, " +
                        "projectName={}, flowName={}, paramMap={}, result={}",
                projectName, flowName, paramMap, result);
    }
}