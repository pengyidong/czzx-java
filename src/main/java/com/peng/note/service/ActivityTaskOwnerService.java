package com.peng.note.service;

import com.peng.note.entity.ActivityTaskOwner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * (ActivityTaskOwner)表服务接口
 *
 * @author makejava
 * @since 2022-02-20 13:33:02
 */
public interface ActivityTaskOwnerService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    ActivityTaskOwner queryById(String id);

    /**
     * 分页查询
     *
     * @param activityTaskOwner 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    Page<ActivityTaskOwner> queryByPage(ActivityTaskOwner activityTaskOwner, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param activityTaskOwner 实例对象
     * @return 实例对象
     */
    ActivityTaskOwner insert(ActivityTaskOwner activityTaskOwner);

    /**
     * 修改数据
     *
     * @param activityTaskOwner 实例对象
     * @return 实例对象
     */
    ActivityTaskOwner update(ActivityTaskOwner activityTaskOwner);

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
