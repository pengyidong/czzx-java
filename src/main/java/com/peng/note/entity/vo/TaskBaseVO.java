package com.peng.note.entity.vo;

import com.peng.note.entity.BaseEntity;
import lombok.Data;

import java.util.Date;

/**
 * @Author : code
 * @Date 2022/2/12 16:00
 * @Version 1.0
 */
@Data
public class TaskBaseVO extends BaseEntity {

    /**
     * 来访单位
     */
    private String visitingUnit;

    /**
     * 来访人员
     */
    private String visitingContact;


    /**
     * 联系电话
     */
    private String contactNumber;

    /**
     * 活动类型
     */
    private String typeOfActivity;

    /**
     * 活动地址
     */
    private String eventAddress;

    /**
     * 活动开始时间
     */
    private Date startTime;

    /**
     * 活动结束时间
     */
    private Date endTime;

    /**
     * 来访人数
     */
    private Integer numberOfPeople;

    /**
     * 来访事由
     */
    private String reasonForVisit;

    /**
     * 备注
     */
    private String note;
}
