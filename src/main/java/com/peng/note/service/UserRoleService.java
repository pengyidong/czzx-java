package com.peng.note.service;

import com.peng.note.entity.UserRole;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * (UserRole)表服务接口
 *
 * @author makejava
 * @since 2022-02-20 13:33:03
 */
public interface UserRoleService {

    /**
     * 通过ID查询单条数据
     *
     * @param userId 主键
     * @return 实例对象
     */
    List<UserRole> queryById(String userId);

    /**
     * 分页查询
     *
     * @param userRole 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    Page<UserRole> queryByPage(UserRole userRole, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param userRole 实例对象
     * @return 实例对象
     */
    UserRole insert(UserRole userRole);

    /**
     * 修改数据
     *
     * @param userRole 实例对象
     * @return 实例对象
     */
    UserRole update(UserRole userRole);

    /**
     * 通过主键删除数据
     *
     * @param userId 主键
     * @return 是否成功
     */
    boolean deleteById(String userId);

    List<Integer> queryRoleList(String userId);

    Boolean deleteByUserId(String userId,Integer roleId);

}
