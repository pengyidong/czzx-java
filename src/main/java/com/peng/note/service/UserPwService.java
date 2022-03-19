package com.peng.note.service;

import com.peng.note.entity.UserPw;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import javax.servlet.http.HttpServletRequest;

/**
 * (UserPw)表服务接口
 *
 * @author makejava
 * @since 2022-02-26 14:23:58
 */
public interface UserPwService {

    /**
     * 通过ID查询单条数据
     *
     * @param userId 主键
     * @return 实例对象
     */
    UserPw queryById(String userId);

    /**
     * 分页查询
     *
     * @param userPw 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    Page<UserPw> queryByPage(UserPw userPw, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param userPw 实例对象
     * @return 实例对象
     */
    UserPw insert(UserPw userPw);

    /**
     * 修改数据
     *
     * @param userPw 实例对象
     * @return 实例对象
     */
    UserPw update(UserPw userPw);

    /**
     * 通过主键删除数据
     *
     * @param userId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer userId);

    UserPw queryByUserName(String userName);

    String getIpAddr(HttpServletRequest request);
}
