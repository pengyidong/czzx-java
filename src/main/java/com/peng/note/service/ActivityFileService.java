package com.peng.note.service;

import com.peng.note.entity.ActivityFile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * (ActivityFile)表服务接口
 *
 * @author makejava
 * @since 2022-03-06 13:23:57
 */
public interface ActivityFileService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    ActivityFile queryById(String id);

    /**
     * 分页查询
     *
     * @param activityFile 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    Page<ActivityFile> queryByPage(ActivityFile activityFile, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param activityFile 实例对象
     * @return 实例对象
     */
    ActivityFile insert(ActivityFile activityFile);

    /**
     * 修改数据
     *
     * @param activityFile 实例对象
     * @return 实例对象
     */
    ActivityFile update(ActivityFile activityFile);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(String id);

}
