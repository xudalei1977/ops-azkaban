package com.leiyu.ops.azkaban.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

/**
 * An value object on Azkaban controller parameters
 *
 * @auther Pitt
 * @date 2020-09-02
 */
@Setter
@Getter
public class AzkabanVO {
    private String projectName;

    private String flowName;

    private Map<String, Object> paramMap;
}
