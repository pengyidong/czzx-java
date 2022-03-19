package com.peng.note.service;

/**
 * @Author : code
 * @Date 2022/2/26 11:58
 * @Version 1.0
 */
public interface TokenService {

    String createToken(String userId,String userName,String password,long effectiveTime);

    Boolean checkToken(String token);
}
