package com.peng.note.utils;

import java.util.Collection;
import java.util.Map;

/**
 * @Author : code
 * @Date 2022/2/28 21:21
 * @Version 1.0
 */
public class Utils {
    public  static boolean isEmpty(Object obj){
        if (null == obj){
            return true;
        }else if (obj instanceof  String){
            return obj== null || ("".equals(obj));
        }else if (obj instanceof Collection){
            return obj==null ||((Collection<?>) obj).isEmpty();
        }else if (obj instanceof Object[]){
            return obj==null || ((Object[])obj).length == 0;
        }else if (obj instanceof Map){
            return obj==null ||((Map<?,?> )obj).isEmpty();
        }else {
            return obj == null;
        }
    }
}
