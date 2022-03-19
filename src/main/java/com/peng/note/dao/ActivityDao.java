package com.peng.note.dao;

import com.peng.note.entity.Activity;
import com.peng.note.entity.Note;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * (Activity)表数据库访问层
 *
 * @author makejava
 * @since 2022-02-26 14:23:49
 */
public interface ActivityDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Activity queryById(String id);

    /**
     * 查询指定行数据
     *
     * @param activity 查询条件
     * @param pageable         分页对象
     * @return 对象列表
     */
    List<Activity> queryAllByLimit(@Param("activity") Activity activity, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param activity 查询条件
     * @return 总行数
     */
    long count(@Param("activity")Activity activity);

    /**
     * 新增数据
     *
     * @param activity 实例对象
     * @return 影响行数
     */
    int insert(Activity activity);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Activity> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Activity> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Activity> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<Activity> entities);

    /**
     * 修改数据
     *
     * @param activity 实例对象
     * @return 影响行数
     */
    int update(Activity activity);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(String id);

    List<Note> queryByPage(Note note, Page page);

}

