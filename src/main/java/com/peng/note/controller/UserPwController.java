package com.peng.note.controller;

import com.peng.note.aop.UserLogin;
import com.peng.note.entity.Role;
import com.peng.note.entity.UserPw;
import com.peng.note.service.RoleService;
import com.peng.note.service.UserPwService;
import com.peng.note.service.UserService;
import com.peng.note.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * (UserPw)表控制层
 *
 * @author makejava
 * @since 2022-02-26 14:23:52
 */
@RestController
@RequestMapping("userPw")
public class UserPwController {
    /**
     * 服务对象
     */
    @Resource
    private UserPwService userPwService;



    /**
     * 分页查询
     *
     * @param userPw 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @GetMapping
    public ResponseEntity<Page<UserPw>> queryByPage(UserPw userPw, PageRequest pageRequest) {
        return ResponseEntity.ok(this.userPwService.queryByPage(userPw, pageRequest));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<UserPw> queryById(@PathVariable("id") String id) {
        return ResponseEntity.ok(this.userPwService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param userPw 实体
     * @return 新增结果
     */
    @PostMapping
    public ResultUtils add(HttpServletRequest request, @RequestBody UserPw userPw) {
        //查询用户名是否存在
        UserPw queryByUserName = userPwService.queryByUserName(userPw.getUserName());
        if (queryByUserName != null){
            return ResultUtils.build(500,"该用户已被注册");
        }
        String ipAddr = userPwService.getIpAddr(request);
        userPw.setCreateBy(ipAddr);
        userPwService.insert(userPw);
        return ResultUtils.build(200,"注册成功");
    }

    /**
     * 编辑数据
     *
     * @param userPw 实体
     * @return 编辑结果
     */
    @PutMapping
    @UserLogin
    public ResponseEntity<UserPw> edit(UserPw userPw) {
        return ResponseEntity.ok(this.userPwService.update(userPw));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Integer id) {
        return ResponseEntity.ok(this.userPwService.deleteById(id));
    }

}

