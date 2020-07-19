package com.leiyu.ops.azkaban.controller;

import com.leiyu.ops.azkaban.entity.ProcessFlow;
import com.leiyu.ops.azkaban.service.ProcessFlowService;
import com.leiyu.ops.azkaban.util.PersonaPropertiesHelper;
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
     * @param batchNo
	 * @param groupId
	 * @param masterAccountCode
	 * @param tenantName
     * @return an instance of GeneralResult
     */
	@PostMapping("/add")
	public GeneralResult saveProcessFlow(@RequestParam String batchNo,
										 @RequestParam Integer groupId,
										 @RequestParam String masterAccountCode,
										 @RequestParam String tenantName) {
        log.info("ProcessFlowController:: saveProcessFlow(): started, " +
						"batchNo={}, groupId={}, masterAccountCode={}, tenantName={}",
				batchNo, groupId, masterAccountCode, tenantName);

		GeneralResult result = new GeneralResult();

		try {
			ProcessFlow processFlow = new ProcessFlow();

			processFlow.setCrowdId(groupId);
			processFlow.setBatchNo(batchNo);
            processFlow.setStatus(PersonaPropertiesHelper.calcStatus);
			processFlow.setUserProfileStatus(PersonaPropertiesHelper.calcStatus);
			processFlow.setFileName(batchNo+ ".txt");
			processFlow.setMasterAccountCode(masterAccountCode);
			processFlow.setTenantName(tenantName);

			result = processFlowService.saveProcessFlow(processFlow);
			result.success();
		} catch (_400CommonException e1) {
			log.error("ProcessFlowController:: saveProcessFlow(): failed, " +
					"batchNo={}, groupId={}, masterAccountCode={}, tenantName={}, exception={}",
					batchNo, groupId, masterAccountCode, tenantName, e1.getMessage());
			result.error(new _400CommonException(e1.getMessage()));
		} catch (Exception e) {
			log.error("ProcessFlowController:: saveProcessFlow(): failed, " +
					"batchNo={}, groupId={}, masterAccountCode={}, tenantName={}, exception={}",
					batchNo, groupId, masterAccountCode, tenantName, e.getMessage());
			result.error(new _500CommonException(e.getMessage()));
		}

        log.info("ProcessFlowController:: saveProcessFlow(): ended, " +
				"batchNo={}, groupId={}, masterAccountCode={}, tenantName={}, result={}",
				batchNo, groupId, masterAccountCode, tenantName, result);
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
		log.info("ProcessFlowController:: deleteByGroupId(): started, " +
                "groupId={}", groupId);

		GeneralResult result = new GeneralResult();

		try {
			result = processFlowService.deleteByCrowdId(groupId);
			result.success();
		} catch (_400CommonException e1) {
			log.error("ProcessFlowController:: deleteByGroupId(): failed, " +
                            "groupId={}, exception={}",
					groupId, e1.getMessage());
			result.error(new _400CommonException(e1.getMessage()));
		} catch (Exception e) {
			log.error("ProcessFlowController:: deleteByGroupId(): failed, " +
                            "groupId={}, exception={}",
					groupId, e.getMessage());
			result.error(new _500CommonException(e.getMessage()));
		}

		log.info("ProcessFlowController:: deleteByGroupId(): ended, " +
                        "groupId={}, result={}",
				groupId, result);
		return result;
	}

	/**
	 * Update status of Azkaban process flow by given batch no
	 *
	 * @param batchNo
     * @param status
     * @param userProfileStatus
	 * @return an instance of GeneralResult
	 */
	@PostMapping("/update-status-by-batch-no")
	public GeneralResult updateStatusByBatchNo(@RequestParam String batchNo,
                                               @RequestParam Integer status,
                                               @RequestParam Integer userProfileStatus) {
		log.info("ProcessFlowController:: updateStatusByBatchNo(): started, " +
                        "batchNo={}, status={}, userProfileStatus={}",
                batchNo, status, userProfileStatus);

		GeneralResult result = new GeneralResult();

		try {
            ProcessFlow processFlow = new ProcessFlow();

            processFlow.setBatchNo(batchNo);
            processFlow.setStatus(status);
            processFlow.setUserProfileStatus(userProfileStatus);

			result = processFlowService.updateStatusByBatchNo(processFlow);
			result.success();
		} catch (_400CommonException e1) {
			log.error("ProcessFlowController:: updateStatusByBatchNo(): failed, " +
                            "batchNo={}, status={}, userProfileStatus={}, exception={}",
                    batchNo, status, userProfileStatus, e1.getMessage());
			result.error(new _400CommonException(e1.getMessage()));
		} catch (Exception e) {
			log.error("ProcessFlowController:: updateStatusByBatchNo(): failed, " +
                            "batchNo={}, status={}, userProfileStatus={}, exception={}",
                    batchNo, status, userProfileStatus, e.getMessage());
			result.error(new _500CommonException(e.getMessage()));
		}

		log.info("ProcessFlowController:: updateStatusByBatchNo(): ended, " +
                        "batchNo={}, status={}, userProfileStatus={}, result={}",
                batchNo, status, userProfileStatus, result);
		return result;
	}

	/**
	 * Update status of Azkaban process flow by given group id
	 *
	 * @param groupId
     * @param status
     * @param userProfileStatus
	 * @return an instance of GeneralResult
	 */
	@PostMapping("/update-status-by-group-id")
	public GeneralResult updateStatusByGoupId(@RequestParam Integer groupId,
                                              @RequestParam Integer status,
                                              @RequestParam Integer userProfileStatus) {
		log.info("ProcessFlowController:: updateStatusByGoupId(): started, " +
                        "groupId={}, status={}, userProfileStatus={}",
                groupId, status, userProfileStatus);

		GeneralResult result = new GeneralResult();

		try {
            ProcessFlow processFlow = new ProcessFlow();

            processFlow.setCrowdId(groupId);
            processFlow.setStatus(PersonaPropertiesHelper.delStatus);
            processFlow.setUserProfileStatus(PersonaPropertiesHelper.delStatus);

			result = processFlowService.updateStatusByCrowId(processFlow);
			result.success();
		} catch (_400CommonException e1) {
			log.error("ProcessFlowController:: updateStatusByGoupId(): failed, " +
                            "groupId={}, status={}, userProfileStatus={}, exception={}",
                    groupId, status, userProfileStatus, e1.getMessage());
			result.error(new _400CommonException(e1.getMessage()));
		} catch (Exception e) {
			log.error("ProcessFlowController:: updateStatusByGoupId(): failed, " +
                            "groupId={}, status={}, userProfileStatus={}, exception={}",
                    groupId, status, userProfileStatus, e.getMessage());
			result.error(new _500CommonException(e.getMessage()));
		}

		log.info("ProcessFlowController:: updateStatusByGoupId(): ended, " +
                        "groupId={}, status={}, userProfileStatus={}, result={}",
                groupId, status, userProfileStatus, result);
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
        log.info("ProcessFlowController:: queryBatchNoByCrowdId(): started, " +
                "groupId={}", groupId);

        GeneralResult result = new GeneralResult();

        try {
            result = processFlowService.queryBatchNoByCrowdId(groupId);
            result.success();
        } catch (_400CommonException e1) {
            log.error("ProcessFlowController:: queryBatchNoByCrowdId(): failed, " +
                            "groupId={}, exception={}",
                    groupId, e1.getMessage());
            result.error(new _400CommonException(e1.getMessage()));
        } catch (Exception e) {
            log.error("ProcessFlowController:: queryBatchNoByCrowdId(): failed, " +
                            "groupId={}, exception={}",
                    groupId, e.getMessage());
            result.error(new _500CommonException(e.getMessage()));
        }

        log.info("ProcessFlowController:: queryBatchNoByCrowdId(): ended, " +
                        "groupId={}, result={}",
                groupId, result);
        return result;
    }

}