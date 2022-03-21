package com.peng.note.dao;

import com.peng.note.entity.ActivityFile;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * (ActivityFile)表数据库访问层
 *
 * @author makejava
 * @since 2022-03-06 13:23:56
 */
public interface ActivityFileDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    ActivityFile queryById(String id);

    /**
     * 查询指定行数据
     *
     * @param activityFile 查询条件
     * @param pageable         分页对象
     * @return 对象列表
     */
    List<ActivityFile> queryAllByLimit(ActivityFile activityFile, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param activityFile 查询条件
     * @return 总行数
     */
    long count(ActivityFile activityFile);

    /**
     * 新增数据
     *
     * @param activityFile 实例对象
     * @return 影响行数
     */
    int insert(@Param("activityFile") ActivityFile activityFile);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<ActivityFile> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<ActivityFile> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<ActivityFile> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<ActivityFile> entities);

    /**
     * 修改数据
     *
     * @param activityFile 实例对象
     * @return 影响行数
     */
    int update(ActivityFile activityFile);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(String id);

    /**
     * 通过主键列表批量删除数据
     *
     * @param ids 主键
     * @return 影响行数
     */
    int batchDelete(List<String> ids);

    /**
     * 通过newName字段批量删除文件
     * @param fileNames
     * @return
     */
    int deleteByNewFile(List<String> fileNames);

    /**
     * 根据活动id删除文件记录
     * @param activityId
     * @return
     */
    int deleteByActivity(String activityId);

    /**
     * 根据新文件名称查询文件记录
     * @param newNames
     * @return
     */
    List<ActivityFile> queryByNewName(List<String> newNames);

    /**
     * 根据活动id查询文件记录
     * @param activityId
     * @return
     */
    List<ActivityFile> queryByActivity(String activityId);

}

