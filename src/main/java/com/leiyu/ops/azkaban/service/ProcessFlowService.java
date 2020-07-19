package com.leiyu.ops.azkaban.service;

import com.leiyu.ops.azkaban.entity.ProcessFlow;
import com.leiyu.ops.azkaban.mapper.ProcessFlowMapper;
import com.leiyu.ops.common.constant.Constant;
import com.leiyu.ops.common.entity.GeneralResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * A service which provides operations on Azkaban process flow
 *
 * @auther Pitt
 * @date 2020-03-15
 */
@Service
public class ProcessFlowService {

    @Autowired
    private ProcessFlowMapper processFlowMapper;

    /**
     * 保存流程表
     * @param processFlow
     * @return主键id
     */
    public GeneralResult saveProcessFlow(ProcessFlow processFlow){

        GeneralResult generalResult = new GeneralResult();

        processFlowMapper.insert(processFlow);

        generalResult.success(processFlow.getId());

        return generalResult;
    }

    /**
     * 在修改标签的时候，根据群id把之前已经生效的群数据删除，避免冗余
     * @param processFlow
     * @return
     */
    public GeneralResult updateStatusByCrowId(ProcessFlow processFlow){

        GeneralResult generalResult = new GeneralResult();

        processFlowMapper.updateStatusByCrowId(processFlow);

        generalResult.success();

        return generalResult;
    }

    /**
     * 操作失败，需要更新流程表的状态
     * @param processFlow
     * @return
     */
    public GeneralResult updateStatusByBatchNo(ProcessFlow processFlow){

        GeneralResult generalResult = new GeneralResult();

        processFlowMapper.updateStatusByBatchNo(processFlow);

        generalResult.success();

        return generalResult;
    }

    /**
     * 根据主键删除
     * @param crowdId
     */
    public GeneralResult deleteByCrowdId(Integer crowdId){

        GeneralResult generalResult = new GeneralResult();

        processFlowMapper.deleteProcessFlowByCrowdId(crowdId);

        generalResult.success();

        return generalResult;
    }

    /**
     * 根据群id批次号
     * @param crowdId
     * @return
     */
    public GeneralResult queryBatchNoByCrowdId(Integer crowdId){

        GeneralResult generalResult = new GeneralResult();

        String batchNo = processFlowMapper.queryBatchNoByCrowdId(crowdId);

        generalResult.success(Constant.OPERATE_SUCCESS, batchNo);

        return generalResult;
    }

}