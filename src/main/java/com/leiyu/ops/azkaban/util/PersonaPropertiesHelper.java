package com.leiyu.ops.azkaban.util;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 获取配置文件中的文件路径帮助类
 * application.yml
 *
 * @auther Pitt
 * @date 2020-03-14
 */
@Component
@ConfigurationProperties(prefix = "persona")
public class PersonaPropertiesHelper {

    /**
     * 初始化状态（未生效，未计算）
     */
    public static Integer initStatus = 0;
    /**
     * 计算中
     */
    public static Integer calcStatus = 1;
    /**
     * 计算失败，异常的状态
     */
    public static Integer failStatus = -1;

    /**
     * 需要删除ES已同步的人群数据状态
     */
    public static Integer delStatus = -2;

    /**
     * 人群匹配中
     */
    public static Integer mapping=4;
    /**
     * 人群匹配失败
     */
    public static Integer mappingFail=-1;

    /**
     * 基础标签来源
     */
    public static Integer basicTagSource = 1;

    /**
     * 人群放大标签来源
     */
    public static Integer enLargeCrowdSource = 2;

    /**
     * 人群上传
     */
    public static Integer matchCrowdSource = 3;

    /**
     * 系统标签
     */
    public static Integer systemTagSource = 4;

    /**
     * azkaban调度项目名称
     */
    private String projectName;

    /**
     * azkaban任务名称
     */
    private String flowName;

    /**
     * 文件下载路径
     */
    private String crowdUpLoad;

    /**
     * 下载上传文件
     */
    private String urlFileDownload;

    /**
     * 用户档案展示字段
     */
    private String showColumns;

    /**
     * 生成用户明细数据时，固定字段
     */
    private String fixedField;

    /**
     * 人群上传根据SeqName查询批次号
     */
    private String crowdUpLoadSeqName;

    /**
     * 人群放大根据SeqName查询批次号
     */
    private String crowdEnLargeSeqName;

    /**
     * 基础标签根据SeqName查询批次号
     */
    private String basicTagSeqName;

    /**
     * 系统标签根据SeqName查询批次号
     */
    private String systemTagSeqName;

    /**
     * 人群下载文件导出路径
     */
    private String dmpDataFileOutPutPath;

    /**
     * 种子人群文件路径
     */
    private String dmpSeedCrowdPath;

    /**
     * 解析种子人群文件匹配的lava_id
     */
    private String dmpSeedCrowdLavaIdPath;

    /**
     * 人群上传字段匹配字段
     */
    private String upLoadColumns;

    private String krbAuthenUser;

    private String krbAuthenKeytab;

    private String krbConfPath;

    private String hdfsUri;

    private String hdfsUploadPath;

    public String getCrowdUpLoad() {
        return crowdUpLoad;
    }

    public void setCrowdUpLoad(String crowdUpLoad) {
        this.crowdUpLoad = crowdUpLoad;
    }

    public String getShowColumns() {
        return showColumns;
    }

    public void setShowColumns(String showColumns) {
        this.showColumns = showColumns;
    }

    public String getFixedField() {
        return fixedField;
    }

    public void setFixedField(String fixedField) {
        this.fixedField = fixedField;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getFlowName() {
        return flowName;
    }

    public void setFlowName(String flowName) {
        this.flowName = flowName;
    }

    public String getCrowdUpLoadSeqName() {
        return crowdUpLoadSeqName;
    }

    public void setCrowdUpLoadSeqName(String crowdUpLoadSeqName) {
        this.crowdUpLoadSeqName = crowdUpLoadSeqName;
    }

    public String getCrowdEnLargeSeqName() {
        return crowdEnLargeSeqName;
    }

    public void setCrowdEnLargeSeqName(String crowdEnLargeSeqName) {
        this.crowdEnLargeSeqName = crowdEnLargeSeqName;
    }

    public String getBasicTagSeqName() {
        return basicTagSeqName;
    }

    public void setBasicTagSeqName(String basicTagSeqName) {
        this.basicTagSeqName = basicTagSeqName;
    }

    public String getDmpDataFileOutPutPath() {
        return dmpDataFileOutPutPath;
    }

    public void setDmpDataFileOutPutPath(String dmpDataFileOutPutPath) {
        this.dmpDataFileOutPutPath = dmpDataFileOutPutPath;
    }

    public String getDmpSeedCrowdPath() {
        return dmpSeedCrowdPath;
    }

    public void setDmpSeedCrowdPath(String dmpSeedCrowdPath) {
        this.dmpSeedCrowdPath = dmpSeedCrowdPath;
    }

    public String getDmpSeedCrowdLavaIdPath() {
        return dmpSeedCrowdLavaIdPath;
    }

    public void setDmpSeedCrowdLavaIdPath(String dmpSeedCrowdLavaIdPath) {
        this.dmpSeedCrowdLavaIdPath = dmpSeedCrowdLavaIdPath;
    }

    public String getUpLoadColumns() {
        return upLoadColumns;
    }

    public void setUpLoadColumns(String upLoadColumns) {
        this.upLoadColumns = upLoadColumns;
    }

    public String getSystemTagSeqName() {
        return systemTagSeqName;
    }

    public void setSystemTagSeqName(String systemTagSeqName) {
        this.systemTagSeqName = systemTagSeqName;
    }

    public String getKrbAuthenUser() {
        return krbAuthenUser;
    }

    public void setKrbAuthenUser(String krbAuthenUser) {
        this.krbAuthenUser = krbAuthenUser;
    }

    public String getKrbAuthenKeytab() {
        return krbAuthenKeytab;
    }

    public void setKrbAuthenKeytab(String krbAuthenKeytab) {
        this.krbAuthenKeytab = krbAuthenKeytab;
    }

    public String getKrbConfPath() {
        return krbConfPath;
    }

    public void setKrbConfPath(String krbConfPath) {
        this.krbConfPath = krbConfPath;
    }

    public String getHdfsUri() {
        return hdfsUri;
    }

    public void setHdfsUri(String hdfsUri) {
        this.hdfsUri = hdfsUri;
    }

    public String getHdfsUploadPath() {
        return hdfsUploadPath;
    }

    public void setHdfsUploadPath(String hdfsUploadPath) {
        this.hdfsUploadPath = hdfsUploadPath;
    }

    public String getUrlFileDownload() {
        return urlFileDownload;
    }

    public void setUrlFileDownload(String urlFileDownload) {
        this.urlFileDownload = urlFileDownload;
    }
}
