package com.peng.note.entity.eum;

import com.peng.note.utils.Utils;

import java.util.Arrays;

/**
 * @Author : code
 * @Date 2022/2/26 15:32
 * @Version 1.0
 */
public enum RoleEnum {
    ADMIN(1,"admin"),

    TOURISTS(2,"tourists")
    ;
    private Integer code;

    private String desc;

    public Integer getCode(){
        return code;
    }

    public String getDesc(){
        return desc;
    }

    RoleEnum(Integer code,String desc){
        this.code = code;
        this.desc = desc;
    }

    public static RoleEnum getByCode(Integer code){
        if (Utils.isEmpty(code)){
            return null;
        }
        return Arrays.stream(RoleEnum.values())
                .filter(RoleEnum -> code.equals(RoleEnum.code)).findFirst().orElse(null);

    }
}
