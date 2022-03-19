package com.peng.note.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (ActivityDockingStaffs)实体类
 *
 * @author makejava
 * @since 2022-02-20 13:32:56
 */
public class ActivityDockingStaffs implements Serializable {
    private static final long serialVersionUID = -13052082806129338L;
    
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
     * 对接人员
     */
    private String dockingStaffs;
    /**
     * 对接人联系电话
     */
    private String dockingPhoneNumber;
    /**
     * 对接人id
     */
    private String dockingStaffsId;


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

    public String getDockingStaffs() {
        return dockingStaffs;
    }

    public void setDockingStaffs(String dockingStaffs) {
        this.dockingStaffs = dockingStaffs;
    }

    public String getDockingPhoneNumber() {
        return dockingPhoneNumber;
    }

    public void setDockingPhoneNumber(String dockingPhoneNumber) {
        this.dockingPhoneNumber = dockingPhoneNumber;
    }

    public String getDockingStaffsId() {
        return dockingStaffsId;
    }

    public void setDockingStaffsId(String dockingStaffsId) {
        this.dockingStaffsId = dockingStaffsId;
    }

}

