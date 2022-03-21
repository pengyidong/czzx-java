package com.peng.note.service;

import com.peng.note.entity.Activity;
import com.peng.note.service.impl.ActivityServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * (Activity)表服务接口
 *
 * @author makejava
 * @since 2022-02-26 14:23:51
 */
public interface ActivityService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Activity queryById(String id);

    /**
     * 分页查询
     *
     * @param activity 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    Page<Activity> queryByPage(Activity activity, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param activity 实例对象
     * @return 实例对象
     */
    Activity insert(Activity activity);

    /**
     * 修改数据
     *
     * @param activity 实例对象
     * @return 实例对象
     */
    Activity update(Activity activity);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(String id);

    /**
     * 查出所有活动信息
     * @return
     */
    List<Activity> queryAll();

    /**
     * 统计日期对应的活动数量
     * @return
     */
    List<ActivityServiceImpl.NumberOfActivityDate> countNumberOfActivityDate();

}
