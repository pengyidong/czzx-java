package com.peng.note.service.impl;

import com.peng.note.entity.User;
import com.peng.note.entity.UserPw;
import com.peng.note.dao.UserPwDao;
import com.peng.note.entity.UserRole;
import com.peng.note.service.RoleService;
import com.peng.note.service.UserPwService;
import com.peng.note.service.UserRoleService;
import com.peng.note.service.UserService;
import com.peng.note.utils.SnowFlakeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * (UserPw)表服务实现类
 *
 * @author makejava
 * @since 2022-02-26 14:23:58
 */
@Service("userPwService")
public class UserPwServiceImpl implements UserPwService {
    @Resource
    private UserPwDao userPwDao;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRoleService userRoleService;

    /**
     * 通过ID查询单条数据
     *
     * @param userId 主键
     * @return 实例对象
     */
    @Override
    public UserPw queryById(String userId) {
        return this.userPwDao.queryById(userId);
    }

    /**
     * 分页查询
     *
     * @param userPw 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<UserPw> queryByPage(UserPw userPw, PageRequest pageRequest) {
        long total = this.userPwDao.count(userPw);
        return new PageImpl<>(this.userPwDao.queryAllByLimit(userPw, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param userPw 实例对象
     * @return 实例对象
     */
    @Override
    public UserPw insert(UserPw userPw) {
        //先创建user,赋予游客权限
        String userId = new SnowFlakeUtils.IdWorker().nextId();
        Date createTime = new Date();
        User user = new User();
        user.setUserId(userId);
        user.setNickName(userId);
        user.setCreateBy(userPw.getCreateBy());
        user.setCreateTime(createTime);
        userService.insert(user);
        //绑定user和 role关系
        UserRole userRole = new UserRole();
        userRole.setUserId(userId);
        userRole.setRoleId(2);
        userRole.setCreateBy(userPw.getCreateBy());
        userRole.setCreateTime(createTime);
        userRoleService.insert(userRole);

        userPw.setUserId(userId);
        userPw.setCreateTime(createTime);
        this.userPwDao.insert(userPw);
        return userPw;
    }

    /**
     * 修改数据
     *
     * @param userPw 实例对象
     * @return 实例对象
     */
    @Override
    public UserPw update(UserPw userPw) {
        this.userPwDao.update(userPw);
        return this.queryById(userPw.getUserId());
    }

    /**
     * 通过主键删除数据
     *
     * @param userId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer userId) {
        return this.userPwDao.deleteById(userId) > 0;
    }

    @Override
    public UserPw queryByUserName(String userName) {
        return userPwDao.queryByUserName(userName);
    }

    @Override
    public String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}
