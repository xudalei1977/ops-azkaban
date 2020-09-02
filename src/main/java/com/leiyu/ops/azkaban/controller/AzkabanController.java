package com.leiyu.ops.azkaban.controller;

import com.leiyu.ops.azkaban.service.AzkabanService;
import com.leiyu.ops.azkaban.vo.AzkabanVO;
import com.leiyu.ops.common.entity.GeneralResult;
import com.leiyu.ops.common.exception.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * REST request controller for Azkaban general service
 *
 * @author Pitt
 * @CreateDate 2020-07-12
 */
@RestController
@RequestMapping("/azkaban")
@Slf4j
public class AzkabanController {
    
	@Autowired
    private AzkabanService azkabanService;

    /**
     * Call Azkaban to start flowName
     *
     * @param azkabanVO
     * @return an instance of GeneralResult
     */
	@PostMapping("/start-azkaban")
	public GeneralResult startAzkabanFlow(@RequestBody AzkabanVO azkabanVO) {
		String projectName = azkabanVO.getProjectName();
		String flowName = azkabanVO.getFlowName();
		Map<String, Object> paramMap = azkabanVO.getParamMap();

        log.info("AzkabanController:: startAzkabanFlow(): started, " +
                        "projectName={}, flowName={}, paramMap={}",
                projectName, flowName, paramMap);

		GeneralResult result = new GeneralResult();

		try {
			result = azkabanService.startAzkabanFlow(projectName, flowName, paramMap);
			result.success();
		} catch (_400CommonException e1) {
			log.error("AzkabanController:: startAzkabanFlow(): failed, " +
							"projectName={}, flowName={}, paramMap={}, exception={}",
                    projectName, flowName, paramMap, e1.getMessage());
			result.error(new _400CommonException(e1.getMessage()));
		} catch (Exception e) {
			log.error("AzkabanController:: startAzkabanFlow(): failed, " +
                            "projectName={}, flowName={}, paramMap={}, exception={}",
                    projectName, flowName, paramMap, e.getMessage());
			result.error(new _500CommonException(e.getMessage()));
		}

        log.info("AzkabanController:: startAzkabanFlow(): ended, " +
                        "projectName={}, flowName={}, paramMap={}, result={}",
                projectName, flowName, paramMap, result);
		return result;
	}

}