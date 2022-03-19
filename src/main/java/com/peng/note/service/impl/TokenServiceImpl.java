package com.peng.note.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.peng.note.conf.SystemCOnf;
import com.peng.note.entity.UserPw;
import com.peng.note.service.TokenService;
import com.peng.note.utils.UserHandle;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author : code
 * @Date 2022/2/26 12:00
 * @Version 1.0
 */
@Service("token")
public class TokenServiceImpl implements TokenService {
    @Resource
    private SystemCOnf systemCOnf;


    @Override
    public String createToken(String userId,String userName, String password, long effectiveTime) {
        String token = "";
        try {
            //过期时间
            Date date = new Date(System.currentTimeMillis()+effectiveTime);
            //秘钥及加密算法
            Algorithm algorithm = Algorithm.HMAC256(systemCOnf.getTOKEN_SECRET());
            //设置头部信息
            Map<String,Object> header = new HashMap<>();
            header.put("typ","JWT");
            header.put("alg","HS256");
            //携带username，password信息，生成签名
            token = JWT.create()
                    .withHeader(header)
                    .withClaim("userId",userId)
                    .withClaim("username",userName)
                    .withClaim("password",password)
                    .withExpiresAt(date)
                    .sign(algorithm);
        }catch (Exception e){
            e.printStackTrace();
            return  null;
        }
        return token;
    }

    @Override
    public Boolean checkToken(String token) {
        /**
         * @desc   验证token，通过返回true
         * @create 2019/1/18/018 9:39
         * @params [token]需要校验的串
         **/
        try {
            Algorithm algorithm = Algorithm.HMAC256(systemCOnf.getTOKEN_SECRET());
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT jwt = verifier.verify(token);
            String username = jwt.getClaims().get("username").asString();
            String password = jwt.getClaims().get("password").asString();
            String userId = jwt.getClaims().get("userId").asString();
            //为true将解析的用户信息存入本地线程
            UserPw userPw = new UserPw();
            userPw.setUserId(userId);
            userPw.setUserName(username);
            userPw.setPassword(password);
            UserHandle.set(userPw);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return  false;
        }
    }
}
