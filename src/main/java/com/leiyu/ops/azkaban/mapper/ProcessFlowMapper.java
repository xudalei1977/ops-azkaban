package com.leiyu.ops.azkaban.mapper;

import com.leiyu.ops.azkaban.entity.ProcessFlow;
import org.springframework.stereotype.Component;

/**
 * @auther Pitt
 * @date 2020-03-15
 */
@Component(value = "ProcessFlowMapper")
public interface ProcessFlowMapper {

    /**
     * 保存流程表
     * @param processFlow
     * @return
     */
    Integer insert(ProcessFlow processFlow);

    /**
     * 修改流程表状态以及群数量
     * @param processFlow
     * @return
     */
    Integer updateStatusByBatchNo(ProcessFlow processFlow);

    /**
     * 根据群id修改流程图状态
     * @param processFlow
     */
    Integer updateStatusByCrowId(ProcessFlow processFlow);

    /**
     * 根据群id查询有效的批次号
     * @param crowdId
     * @return
     */
    String queryBatchNoByCrowdId(Integer crowdId);

    /**
     * 根据主键删除
     * @param crowdId
     * @return
     */
    void deleteProcessFlowByCrowdId(Integer crowdId);

}