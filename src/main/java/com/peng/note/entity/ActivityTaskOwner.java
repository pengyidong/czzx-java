package com.peng.note.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (ActivityTaskOwner)实体类
 *
 * @author makejava
 * @since 2022-02-20 13:33:01
 */
public class ActivityTaskOwner implements Serializable {
    private static final long serialVersionUID = 574844657736566813L;
    
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
     * 活动负责人
     */
    private String taskOwner;
    /**
     * 活动负责人联系电话
     */
    private String taskPhoneNumber;
    /**
     * 活动负责人id
     */
    private String taskOwnerId;


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

    public String getTaskOwner() {
        return taskOwner;
    }

    public void setTaskOwner(String taskOwner) {
        this.taskOwner = taskOwner;
    }

    public String getTaskPhoneNumber() {
        return taskPhoneNumber;
    }

    public void setTaskPhoneNumber(String taskPhoneNumber) {
        this.taskPhoneNumber = taskPhoneNumber;
    }

    public String getTaskOwnerId() {
        return taskOwnerId;
    }

    public void setTaskOwnerId(String taskOwnerId) {
        this.taskOwnerId = taskOwnerId;
    }

}

