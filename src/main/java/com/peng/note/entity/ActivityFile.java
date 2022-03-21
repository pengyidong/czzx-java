package com.peng.note.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.util.Date;
import java.io.Serializable;

/**
 * (ActivityFile)实体类
 *
 * @author makejava
 * @since 2022-03-06 13:23:56
 */
public class ActivityFile implements Serializable {
    private static final long serialVersionUID = 120210666886568140L;

    @TableId(value = "id",type = IdType.INPUT)
    private String id;
    /**
     * 创建时间
     */
    private Date createtime;
    /**
     * 创建人
     */
    private String createby;
    /**
     * 修改时间
     */
    private Date updatetime;
    /**
     * 修改人
     */
    private String updateby;
    /**
     * 活动id
     */
    private String activityId;
    /**
     * 文件名
     */
    private String fileName;
    /**
     * 新文件名
     */
    private String newName;
    /**
     * 文件类型
     */
    private String fileType;
    /**
     * 文件后缀
     */
    private String fileExtension;
    /**
     * 文件路径
     */
    private String filePath;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getCreateby() {
        return createby;
    }

    public void setCreateby(String createby) {
        this.createby = createby;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public String getUpdateby() {
        return updateby;
    }

    public void setUpdateby(String updateby) {
        this.updateby = updateby;
    }

    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getNewName() {
        return newName;
    }

    public void setNewName(String newName) {
        this.newName = newName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getFileExtension() {
        return fileExtension;
    }

    public void setFileExtension(String fileExtension) {
        this.fileExtension = fileExtension;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

}

