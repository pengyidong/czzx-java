package com.peng.note.entity;

import lombok.Data;

import java.util.Date;

/**
 * @Author : code
 * @Date 2022/2/11 20:55
 * @Version 1.0
 */
@Data
public class BaseEntity {
    private Integer id;

    private Date createTime;

    private Date createBy;

    private Date updateTime;

    private Date updateBy;
}
