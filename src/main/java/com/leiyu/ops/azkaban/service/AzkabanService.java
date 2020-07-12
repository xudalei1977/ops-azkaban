package com.leiyu.ops.azkaban.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
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
     * Execute Azkaban flow request
     *
     * @param request
     * @return
     */
    public AzkabanExecuteFlowResponse executeCalculation(AzkabanExecuteFlowRequest request) {

        log.info("AzkabanService:: executeCalculation(): AzkabanExecuteFlowRequest url={}",
                azkabanConfig.getAzkUrl());

        String jsonString = HttpUtils.doGet(azkabanConfig.getAzkUrl(), request.parse());

        AzkabanExecuteFlowResponse azkabanExecuteFlowResponse =
                JSON.parseObject(jsonString,AzkabanExecuteFlowResponse.class);

        return azkabanExecuteFlowResponse;
    }

    /**
     * Start Azkaban flow with given params
     *
     * @param project
     * @param flow
     */
    public GeneralResult startAzkabanFlow(String project, String flow, Map<String, Object> paramMap) {

        GeneralResult generalResult = new GeneralResult();

        AzkabanExecuteFlowRequest request = new AzkabanExecuteFlowRequest();
        request.setFlowOverride(paramMap);
        request.setProject(project);
        request.setFlow(flow);
        request.setSessionId(login());
        request.setConcurrentOption("ignore");

        AzkabanExecuteFlowResponse response = executeCalculation(request);

        // Execution failed
        if (response.getError() != null){
            generalResult.error(new _400CommonException("AzkabanExecuteFlowResponse returned error=" +
                    response.getError()));
        }else{
            generalResult.success();
        }

        return generalResult;
    }

}