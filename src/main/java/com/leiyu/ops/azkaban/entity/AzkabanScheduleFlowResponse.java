package com.leiyu.ops.azkaban.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * An entity on Azkaban scheduler flow response
 *
 * @auther Pitt
 * @date 2020-11-27
 */
@Getter
@Setter
public class AzkabanScheduleFlowResponse extends AzkabanResponse {

    private String scheduleId;

    @Override
    public boolean failure() {
        return this.scheduleId == null || "".equals(this.scheduleId);
    }

    @Override
    public boolean error() {
        return this.failure();
    }
}
