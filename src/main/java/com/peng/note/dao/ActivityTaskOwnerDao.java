package com.peng.note.dao;

import com.peng.note.entity.ActivityTaskOwner;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * (ActivityTaskOwner)表数据库访问层
 *
 * @author makejava
 * @since 2022-02-20 13:33:01
 */
public interface ActivityTaskOwnerDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    ActivityTaskOwner queryById(String id);

    /**
     * 查询指定行数据
     *
     * @param activityTaskOwner 查询条件
     * @param pageable         分页对象
     * @return 对象列表
     */
    List<ActivityTaskOwner> queryAllByLimit(ActivityTaskOwner activityTaskOwner, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param activityTaskOwner 查询条件
     * @return 总行数
     */
    long count(ActivityTaskOwner activityTaskOwner);

    /**
     * 新增数据
     *
     * @param activityTaskOwner 实例对象
     * @return 影响行数
     */
    int insert(ActivityTaskOwner activityTaskOwner);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<ActivityTaskOwner> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<ActivityTaskOwner> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<ActivityTaskOwner> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<ActivityTaskOwner> entities);

    /**
     * 修改数据
     *
     * @param activityTaskOwner 实例对象
     * @return 影响行数
     */
    int update(ActivityTaskOwner activityTaskOwner);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(String id);

    Boolean deleteByActivityId(String activityId);

    List<String> queryByActivityId(String activityId);

}

