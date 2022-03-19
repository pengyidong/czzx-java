package com.peng.note.service;

import com.peng.note.entity.ActivityDockingStaffs;
import com.peng.note.entity.ActivityTaskOwner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * (ActivityDockingStaffs)表服务接口
 *
 * @author makejava
 * @since 2022-02-20 13:32:57
 */
public interface ActivityDockingStaffsService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    ActivityDockingStaffs queryById(String id);

    /**
     * 分页查询
     *
     * @param activityDockingStaffs 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    Page<ActivityDockingStaffs> queryByPage(ActivityDockingStaffs activityDockingStaffs, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param activityDockingStaffs 实例对象
     * @return 实例对象
     */
    ActivityDockingStaffs  insert(ActivityDockingStaffs activityDockingStaffs);


    /**
     * 修改数据
     *
     * @param activityDockingStaffs 实例对象
     * @return 实例对象
     */
    ActivityDockingStaffs update(ActivityDockingStaffs activityDockingStaffs);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(String id);

    boolean deleteByActivityId(String activityId);

    List<String> queryByActivityId(String activityId);



}
