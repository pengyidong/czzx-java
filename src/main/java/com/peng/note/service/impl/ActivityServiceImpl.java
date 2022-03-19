package com.peng.note.service.impl;

import com.peng.note.entity.Activity;
import com.peng.note.dao.ActivityDao;
import com.peng.note.service.ActivityService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;

/**
 * (Activity)表服务实现类
 *
 * @author makejava
 * @since 2022-02-26 14:23:51
 */
@Service("activityService")
public class ActivityServiceImpl implements ActivityService {
    @Resource
    private ActivityDao activityDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Activity queryById(String id) {
        return this.activityDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param activity 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<Activity> queryByPage(Activity activity, PageRequest pageRequest) {
        long total = this.activityDao.count(activity);
        return new PageImpl<>(this.activityDao.queryAllByLimit(activity, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param activity 实例对象
     * @return 实例对象
     */
    @Override
    public Activity insert(Activity activity) {
        this.activityDao.insert(activity);
        return activity;
    }

    /**
     * 修改数据
     *
     * @param activity 实例对象
     * @return 实例对象
     */
    @Override
    public Activity update(Activity activity) {
        this.activityDao.update(activity);
        return this.queryById(activity.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String id) {
        return this.activityDao.deleteById(id) > 0;
    }
}
