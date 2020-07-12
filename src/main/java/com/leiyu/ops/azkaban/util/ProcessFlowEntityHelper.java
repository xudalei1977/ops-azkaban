package com.leiyu.ops.azkaban.util;

import com.leiyu.ops.azkaban.entity.ProcessFlow;

/**
 * Utility for building Azkaban process flow entity
 *
 * @auther Pitt
 * @date 2020-03-14
 */
public class ProcessFlowEntityHelper {

    /**
     * 封装ProcessFlow更新实体类方法
     */
    public static ProcessFlow buildProcessFlow(String batchNo, Integer status, Integer userProfileStatus) {
        ProcessFlow processFlow = new ProcessFlow();

        processFlow.setBatchNo(batchNo);
        processFlow.setStatus(status);
        processFlow.setUserProfileStatus(userProfileStatus);

        return processFlow;
    }

    /**
     * 封装保存流程控制表方法
     *
     * @param crowdId
     * @param batchNo
     * @return
     */
    public static ProcessFlow buildSaveProcessFlow(String batchNo, Integer crowdId,
                                                   String masterAccountCode, String tenantName,
                                                   Integer userProfileStatus, Integer status){
        ProcessFlow processFlow = new ProcessFlow();

        processFlow.setCrowdId(crowdId);
        processFlow.setBatchNo(batchNo);
        processFlow.setUserProfileStatus(userProfileStatus);
        processFlow.setStatus(status);
        processFlow.setFileName(batchNo+ ".txt");
        processFlow.setMasterAccountCode(masterAccountCode);
        processFlow.setTenantName(tenantName);

        return processFlow;
    }
}