package com.peng.note.service.impl;

import com.peng.note.entity.ActivityDockingStaffs;
import com.peng.note.dao.ActivityDockingStaffsDao;
import com.peng.note.service.ActivityDockingStaffsService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.util.List;

/**
 * (ActivityDockingStaffs)表服务实现类
 *
 * @author makejava
 * @since 2022-02-20 13:32:57
 */
@Service("activityDockingStaffsService")
public class ActivityDockingStaffsServiceImpl implements ActivityDockingStaffsService {
    @Resource
    private ActivityDockingStaffsDao activityDockingStaffsDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public ActivityDockingStaffs queryById(String id) {
        return this.activityDockingStaffsDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param activityDockingStaffs 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<ActivityDockingStaffs> queryByPage(ActivityDockingStaffs activityDockingStaffs, PageRequest pageRequest) {
        long total = this.activityDockingStaffsDao.count(activityDockingStaffs);
        return new PageImpl<>(this.activityDockingStaffsDao.queryAllByLimit(activityDockingStaffs, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param activityDockingStaffs 实例对象
     * @return 实例对象
     */
    @Override
    public ActivityDockingStaffs insert(ActivityDockingStaffs activityDockingStaffs) {
        this.activityDockingStaffsDao.insert(activityDockingStaffs);
        return activityDockingStaffs;
    }

    /**
     * 修改数据
     *
     * @param activityDockingStaffs 实例对象
     * @return 实例对象
     */
    @Override
    public ActivityDockingStaffs update(ActivityDockingStaffs activityDockingStaffs) {
        this.activityDockingStaffsDao.update(activityDockingStaffs);
        return this.queryById(activityDockingStaffs.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String id) {
        return this.activityDockingStaffsDao.deleteById(id) > 0;
    }

    @Override
    public boolean deleteByActivityId(String activityId) {
        return activityDockingStaffsDao.deleteByActivityId(activityId);
    }

    @Override
    public List<String> queryByActivityId(String activityId) {
        return activityDockingStaffsDao.queryByActivityId(activityId);
    }
}
