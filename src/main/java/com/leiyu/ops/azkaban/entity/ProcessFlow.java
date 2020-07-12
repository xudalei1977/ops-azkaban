package com.leiyu.ops.azkaban.entity;

import java.util.Date;

/**
 * An entity on Azkaban process flow
 *
 * @auther Pitt
 * @date 2020-03-14
 */
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCrowdId() {
        return crowdId;
    }

    public void setCrowdId(Integer crowdId) {
        this.crowdId = crowdId;
    }

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public String getMasterAccountCode() {
        return masterAccountCode;
    }

    public void setMasterAccountCode(String masterAccountCode) {
        this.masterAccountCode = masterAccountCode;
    }

    public Integer getUserProfileStatus() {
        return userProfileStatus;
    }

    public void setUserProfileStatus(Integer userProfileStatus) {
        this.userProfileStatus = userProfileStatus;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getCrowdCount() {
        return crowdCount;
    }

    public void setCrowdCount(Integer crowdCount) {
        this.crowdCount = crowdCount;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getTenantName() {
        return tenantName;
    }

    public void setTenantName(String tenantName) {
        this.tenantName = tenantName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

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