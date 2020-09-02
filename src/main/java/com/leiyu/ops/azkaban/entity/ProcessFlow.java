package com.leiyu.ops.azkaban.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * An entity on Azkaban process flow
 *
 * @auther Pitt
 * @date 2020-03-14
 */
@Setter
@Getter
public class ProcessFlow {
    private Integer id;
    private Integer crowdId;
    private String batchNo;
    private Integer status;
    private Integer crowdCount;
    private Integer userProfileStatus;
    private String fileName;
    private String masterAccountCode;
    private String tenantName;
    private Date createTime;
    private Date updateTime;

    @Override
    public String toString() {
        return "ProcessFlow{" +
                "id=" + id +
                ", crowdId=" + crowdId +
                ", batchNo='" + batchNo + '\'' +
                ", masterAccountCode='" + masterAccountCode + '\'' +
                ", userProfileStatus=" + userProfileStatus +
                ", status=" + status +
                ", crowdCount=" + crowdCount +
                ", fileName='" + fileName + '\'' +
                ", tenantName='" + tenantName + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}