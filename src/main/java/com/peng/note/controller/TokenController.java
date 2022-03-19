package com.peng.note.controller;

import com.peng.note.entity.UserPw;
import com.peng.note.service.TokenService;
import com.peng.note.service.UserPwService;
import com.peng.note.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author : code
 * @Date 2022/2/25 22:57
 * @Version 1.0
 */

@RestController
@RequestMapping("/token")
public class TokenController {

    @Autowired
    private UserPwService userPwService;

    @Autowired
    private TokenService tokenService;

    @RequestMapping(value = "/create",method = RequestMethod.POST)
    public ResultUtils addActivity(@RequestBody UserPw userPw){
        String userName = userPw.getUserName();
        String password = userPw.getPassword();
        //根据username 查询用户是否存在，存在校验密码
        UserPw byUserName = userPwService.queryByUserName(userName);
        if (byUserName != null ){
            if (!byUserName.getPassword().equals(password)){
                return new ResultUtils(500,"请检查账号密码是否正确",null);
            }
            //生成token Bearer
            String token = tokenService.createToken(byUserName.getUserId(),userName, password, 60 * 1000 * 60 *24 );
            return new ResultUtils(token);
        }else {
            return new ResultUtils(500,"无效用户",null);
        }

    }

    @RequestMapping(value = "/check",method = RequestMethod.GET)
    public ResultUtils addActivity(@RequestParam String token){
        Boolean aBoolean = tokenService.checkToken(token);
        return ResultUtils.build(200, String.valueOf(aBoolean));
    }


}
