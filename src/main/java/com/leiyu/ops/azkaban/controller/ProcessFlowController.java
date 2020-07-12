package com.leiyu.ops.azkaban.controller;

import com.leiyu.ops.azkaban.entity.ProcessFlow;
import com.leiyu.ops.azkaban.service.ProcessFlowService;
import com.leiyu.ops.common.entity.GeneralResult;
import com.leiyu.ops.common.exception._400CommonException;
import com.leiyu.ops.common.exception._500CommonException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * REST request controller for Azkaban process flow service
 *
 * @author Pitt
 * @CreateDate 2020-07-12
 */
@RestController
@RequestMapping("/process-flow")
@Slf4j
public class ProcessFlowController {
    
	@Autowired
    private ProcessFlowService processFlowService;

    /**
     * Save a new Azkaban process flow
     *
     * @param flow
     * @return an instance of GeneralResult
     */
	@PostMapping("/add")
	public GeneralResult saveProcessFlow(@RequestParam ProcessFlow flow) {
        log.info("ProcessFlowController:: saveProcessFlow(): started, flow={}", flow);

		GeneralResult result = new GeneralResult();

		try {
			result = processFlowService.saveProcessFlow(flow);
			result.success();
		} catch (_400CommonException e1) {
			log.error("ProcessFlowController:: saveProcessFlow(): failed, flow={}, exception={}", flow, e1.getMessage());
			result.error(new _400CommonException(e1.getMessage()));
		} catch (Exception e) {
			log.error("ProcessFlowController:: saveProcessFlow(): failed, flow={}, exception={}", flow, e.getMessage());
			result.error(new _500CommonException(e.getMessage()));
		}

        log.info("ProcessFlowController:: saveProcessFlow(): ended, flow={}, result={}", flow, result);
		return result;
	}

	/**
	 * Delete Azkaban process flow by given group id
	 *
	 * @param groupId
	 * @return an instance of GeneralResult
	 */
	@PostMapping("/delete")
	public GeneralResult deleteByGroupId(@RequestParam Integer groupId) {
		log.info("ProcessFlowController:: deleteByGroupId(): started, groupId={}", groupId);

		GeneralResult result = new GeneralResult();

		try {
			result = processFlowService.deleteByCrowdId(groupId);
			result.success();
		} catch (_400CommonException e1) {
			log.error("ProcessFlowController:: deleteByGroupId(): failed, groupId={}, exception={}",
					groupId, e1.getMessage());
			result.error(new _400CommonException(e1.getMessage()));
		} catch (Exception e) {
			log.error("ProcessFlowController:: deleteByGroupId(): failed, groupId={}, exception={}",
					groupId, e.getMessage());
			result.error(new _500CommonException(e.getMessage()));
		}

		log.info("ProcessFlowController:: deleteByGroupId(): ended, groupId={}, result={}",
				groupId, result);
		return result;
	}

	/**
	 * Update status of Azkaban process flow by given batch no
	 *
	 * @param flow
	 * @return an instance of GeneralResult
	 */
	@PostMapping("/update-status-by-batch-no")
	public GeneralResult updateStatusByBatchNo(@RequestParam ProcessFlow flow) {
		log.info("ProcessFlowController:: updateStatusByBatchNo(): started, flow={}", flow);

		GeneralResult result = new GeneralResult();

		try {
			result = processFlowService.updateStatusByBatchNo(flow);
			result.success();
		} catch (_400CommonException e1) {
			log.error("ProcessFlowController:: updateStatusByBatchNo(): failed, flow={}, exception={}",
                    flow, e1.getMessage());
			result.error(new _400CommonException(e1.getMessage()));
		} catch (Exception e) {
			log.error("ProcessFlowController:: updateStatusByBatchNo(): failed, flow={}, exception={}",
                    flow, e.getMessage());
			result.error(new _500CommonException(e.getMessage()));
		}

		log.info("ProcessFlowController:: updateStatusByBatchNo(): ended, flow={}, result={}",
                flow, result);
		return result;
	}

	/**
	 * Update status of Azkaban process flow by given group id
	 *
	 * @param flow
	 * @return an instance of GeneralResult
	 */
	@PostMapping("/update-status-by-group-id")
	public GeneralResult updateStatusByGoupId(@RequestParam ProcessFlow flow) {
		log.info("ProcessFlowController:: updateStatusByGoupId(): started, flow={}", flow);

		GeneralResult result = new GeneralResult();

		try {
			result = processFlowService.updateStatusByCrowId(flow);
			result.success();
		} catch (_400CommonException e1) {
			log.error("ProcessFlowController:: updateStatusByGoupId(): failed, flow={}, exception={}",
                    flow, e1.getMessage());
			result.error(new _400CommonException(e1.getMessage()));
		} catch (Exception e) {
			log.error("ProcessFlowController:: updateStatusByGoupId(): failed, flow={}, exception={}",
                    flow, e.getMessage());
			result.error(new _500CommonException(e.getMessage()));
		}

		log.info("ProcessFlowController:: updateStatusByGoupId(): ended, flow={}, result={}",
                flow, result);
		return result;
	}

    /**
     * Query batch no of Azkaban process flow by given group id
     *
     * @param groupId
     * @return an instance of GeneralResult
     */
    @PostMapping("/query-batch-no-by-group-id")
    public GeneralResult queryBatchNoByCrowdId(@RequestParam Integer groupId) {
        log.info("ProcessFlowController:: queryBatchNoByCrowdId(): started, groupId={}", groupId);

        GeneralResult result = new GeneralResult();

        try {
            result = processFlowService.queryBatchNoByCrowdId(groupId);
            result.success();
        } catch (_400CommonException e1) {
            log.error("ProcessFlowController:: queryBatchNoByCrowdId(): failed, groupId={}, exception={}",
                    groupId, e1.getMessage());
            result.error(new _400CommonException(e1.getMessage()));
        } catch (Exception e) {
            log.error("ProcessFlowController:: queryBatchNoByCrowdId(): failed, groupId={}, exception={}",
                    groupId, e.getMessage());
            result.error(new _500CommonException(e.getMessage()));
        }

        log.info("ProcessFlowController:: queryBatchNoByCrowdId(): ended, groupId={}, result={}",
                groupId, result);
        return result;
    }

}