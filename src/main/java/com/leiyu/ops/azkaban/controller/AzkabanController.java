package com.leiyu.ops.azkaban.controller;

import com.leiyu.ops.azkaban.service.AzkabanService;
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
     * Call Azkaban to start flow
     *
     * @param project
     * @param flow
     * @param paramMap
     * @return an instance of GeneralResult
     */
	@PostMapping("/start-azkaban")
	public GeneralResult startAzkabanFlow(@RequestParam String project, @RequestParam String flow,
										@RequestParam Map<String, Object> paramMap) {
        log.info("AzkabanController:: startAzkabanFlow(): started, project={}, flow={}, paramMap={}",
                project, flow, paramMap);

		GeneralResult result = new GeneralResult();

		try {
			result = azkabanService.startAzkabanFlow(project, flow, paramMap);
			result.success();
		} catch (_400CommonException e1) {
			log.error("AzkabanController:: startAzkabanFlow(): failed, project={}, flow={}, paramMap={}, exception={}",
                    project, flow, paramMap, e1.getMessage());
			result.error(new _400CommonException(e1.getMessage()));
		} catch (Exception e) {
			log.error("AzkabanController:: startAzkabanFlow(): failed, project={}, flow={}, paramMap={}, exception={}",
                    project, flow, paramMap, e.getMessage());
			result.error(new _500CommonException(e.getMessage()));
		}

        log.info("AzkabanController:: startAzkabanFlow(): ended, project={}, flow={}, paramMap={}, result={}",
                project, flow, paramMap, result);
		return result;
	}

}