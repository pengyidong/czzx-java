package com.peng.note.dao;

import com.peng.note.entity.ActivityDockingStaffs;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * (ActivityDockingStaffs)表数据库访问层
 *
 * @author makejava
 * @since 2022-02-20 13:32:55
 */
public interface ActivityDockingStaffsDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    ActivityDockingStaffs queryById(String id);

    /**
     * 查询指定行数据
     *
     * @param activityDockingStaffs 查询条件
     * @param pageable         分页对象
     * @return 对象列表
     */
    List<ActivityDockingStaffs> queryAllByLimit(ActivityDockingStaffs activityDockingStaffs, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param activityDockingStaffs 查询条件
     * @return 总行数
     */
    long count(ActivityDockingStaffs activityDockingStaffs);

    /**
     * 新增数据
     *
     * @param activityDockingStaffs 实例对象
     * @return 影响行数
     */
    int insert(ActivityDockingStaffs activityDockingStaffs);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<ActivityDockingStaffs> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<ActivityDockingStaffs> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<ActivityDockingStaffs> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<ActivityDockingStaffs> entities);

    /**
     * 修改数据
     *
     * @param activityDockingStaffs 实例对象
     * @return 影响行数
     */
    int update(ActivityDockingStaffs activityDockingStaffs);

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

