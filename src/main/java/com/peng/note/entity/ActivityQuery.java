package com.peng.note.entity;

import lombok.Data;

/**
 * @Author : code
 * @Date 2022/3/14 22:40
 * @Version 1.0
 */
@Data
public class ActivityQuery  {

    private Activity activity;

    private int size =10 ;

    private int number = 0;
}
