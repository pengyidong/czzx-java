package com.peng.note.utils;

import com.peng.note.entity.UserPw;
import jdk.nashorn.internal.ir.CallNode;

/**
 * @Author : code
 * @Date 2022/2/26 12:50
 * @Version 1.0
 */
public class UserHandle {


    private UserHandle(){ }

    private static final ThreadLocal<UserPw> USER_INFO = new ThreadLocal<UserPw>();

    public static void set(UserPw userInfo) {
        USER_INFO.set(userInfo);
    }


    public static UserPw get() {
        return USER_INFO.get();
    }

    public static void remove() {
        USER_INFO.remove();
    }


}
