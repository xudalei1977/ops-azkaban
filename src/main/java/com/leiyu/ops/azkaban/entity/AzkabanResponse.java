package com.leiyu.ops.azkaban.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * An entity on Azkaban response
 *
 * @auther Pitt
 * @date 2020-03-14
 */
@Getter
@Setter
public abstract class AzkabanResponse {

    private String message;
    private String status;
    private String error;

    public abstract boolean failure();

    public abstract boolean error();
}