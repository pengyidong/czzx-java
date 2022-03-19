package com.peng.note.dao;

import com.peng.note.entity.UserPw;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * (UserPw)表数据库访问层
 *
 * @author makejava
 * @since 2022-02-26 14:23:52
 */
public interface UserPwDao {

    /**
     * 通过ID查询单条数据
     *
     * @param userId 主键
     * @return 实例对象
     */
    UserPw queryById(String userId);

    /**
     * 查询指定行数据
     *
     * @param userPw 查询条件
     * @param pageable         分页对象
     * @return 对象列表
     */
    List<UserPw> queryAllByLimit(UserPw userPw, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param userPw 查询条件
     * @return 总行数
     */
    long count(UserPw userPw);

    /**
     * 新增数据
     *
     * @param userPw 实例对象
     * @return 影响行数
     */
    int insert(UserPw userPw);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<UserPw> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<UserPw> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<UserPw> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<UserPw> entities);

    /**
     * 修改数据
     *
     * @param userPw 实例对象
     * @return 影响行数
     */
    int update(UserPw userPw);

    /**
     * 通过主键删除数据
     *
     * @param userId 主键
     * @return 影响行数
     */
    int deleteById(Integer userId);

    UserPw queryByUserName(@Param("userName") String userName);

}

