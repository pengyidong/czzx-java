package com.peng.note.service.impl;

import com.peng.note.entity.ActivityTaskOwner;
import com.peng.note.dao.ActivityTaskOwnerDao;
import com.peng.note.service.ActivityTaskOwnerService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.util.List;

/**
 * (ActivityTaskOwner)表服务实现类
 *
 * @author makejava
 * @since 2022-02-20 13:33:02
 */
@Service("activityTaskOwnerService")
public class ActivityTaskOwnerServiceImpl implements ActivityTaskOwnerService {
    @Resource
    private ActivityTaskOwnerDao activityTaskOwnerDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public ActivityTaskOwner queryById(String id) {
        return this.activityTaskOwnerDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param activityTaskOwner 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<ActivityTaskOwner> queryByPage(ActivityTaskOwner activityTaskOwner, PageRequest pageRequest) {
        long total = this.activityTaskOwnerDao.count(activityTaskOwner);
        return new PageImpl<>(this.activityTaskOwnerDao.queryAllByLimit(activityTaskOwner, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param activityTaskOwner 实例对象
     * @return 实例对象
     */
    @Override
    public ActivityTaskOwner insert(ActivityTaskOwner activityTaskOwner) {
        this.activityTaskOwnerDao.insert(activityTaskOwner);
        return activityTaskOwner;
    }

    /**
     * 修改数据
     *
     * @param activityTaskOwner 实例对象
     * @return 实例对象
     */
    @Override
    public ActivityTaskOwner update(ActivityTaskOwner activityTaskOwner) {
        this.activityTaskOwnerDao.update(activityTaskOwner);
        return this.queryById(activityTaskOwner.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String id) {
        return this.activityTaskOwnerDao.deleteById(id) > 0;
    }

    @Override
    public boolean deleteByActivityId(String activityId) {
        return activityTaskOwnerDao.deleteByActivityId(activityId);
    }

    @Override
    public List<String> queryByActivityId(String activityId) {
        return activityTaskOwnerDao.queryByActivityId(activityId);
    }
}
