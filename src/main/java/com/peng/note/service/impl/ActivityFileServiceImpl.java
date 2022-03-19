package com.peng.note.service.impl;

import com.peng.note.entity.ActivityFile;
import com.peng.note.dao.ActivityFileDao;
import com.peng.note.service.ActivityFileService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;

/**
 * (ActivityFile)表服务实现类
 *
 * @author makejava
 * @since 2022-03-06 13:23:58
 */
@Service("activityFileService")
public class ActivityFileServiceImpl implements ActivityFileService {
    @Resource
    private ActivityFileDao activityFileDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public ActivityFile queryById(String id) {
        return this.activityFileDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param activityFile 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<ActivityFile> queryByPage(ActivityFile activityFile, PageRequest pageRequest) {
        long total = this.activityFileDao.count(activityFile);
        return new PageImpl<>(this.activityFileDao.queryAllByLimit(activityFile, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param activityFile 实例对象
     * @return 实例对象
     */
    @Override
    public ActivityFile insert(ActivityFile activityFile) {
        this.activityFileDao.insert(activityFile);
        return activityFile;
    }

    /**
     * 修改数据
     *
     * @param activityFile 实例对象
     * @return 实例对象
     */
    @Override
    public ActivityFile update(ActivityFile activityFile) {
        this.activityFileDao.update(activityFile);
        return this.queryById(activityFile.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String id) {
        return this.activityFileDao.deleteById(id) > 0;
    }
}
