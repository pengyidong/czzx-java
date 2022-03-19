package com.peng.note.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import java.util.Date;
import java.io.Serializable;

/**
 * (Activity)实体类
 *
 * @author makejava
 * @since 2022-02-26 14:23:50
 */
public class Activity implements Serializable {
    private static final long serialVersionUID = 818374894700520373L;

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
     * 来访单位
     */
    private String visitingunit;
    /**
     * 来访人员
     */
    private String visitingcontact;
    /**
     * 联系电话
     */
    private String contactnumber;
    /**
     * 活动类型
     */
    private String typeofactivity;
    /**
     * 活动地址
     */
    private String eventaddress;
    /**
     * 活动开始时间
     */
    private Date starttime;
    /**
     * 活动结束时间
     */
    private Date endtime;
    /**
     * 来访人数
     */
    private Integer numberofpeople;
    /**
     * 来访事由
     */
    private String reasonforvisit;
    /**
     * 备注
     */
    private String note;

    private String status;
    
    private Long price;




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

    public String getVisitingunit() {
        return visitingunit;
    }

    public void setVisitingunit(String visitingunit) {
        this.visitingunit = visitingunit;
    }

    public String getVisitingcontact() {
        return visitingcontact;
    }

    public void setVisitingcontact(String visitingcontact) {
        this.visitingcontact = visitingcontact;
    }

    public String getContactnumber() {
        return contactnumber;
    }

    public void setContactnumber(String contactnumber) {
        this.contactnumber = contactnumber;
    }

    public String getTypeofactivity() {
        return typeofactivity;
    }

    public void setTypeofactivity(String typeofactivity) {
        this.typeofactivity = typeofactivity;
    }

    public String getEventaddress() {
        return eventaddress;
    }

    public void setEventaddress(String eventaddress) {
        this.eventaddress = eventaddress;
    }

    public Date getStarttime() {
        return starttime;
    }

    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

    public Integer getNumberofpeople() {
        return numberofpeople;
    }

    public void setNumberofpeople(Integer numberofpeople) {
        this.numberofpeople = numberofpeople;
    }

    public String getReasonforvisit() {
        return reasonforvisit;
    }

    public void setReasonforvisit(String reasonforvisit) {
        this.reasonforvisit = reasonforvisit;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

