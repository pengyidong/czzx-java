package com.peng.note.service.impl;

import com.peng.note.dao.ActivityFileDao;
import com.peng.note.entity.Activity;
import com.peng.note.dao.ActivityDao;
import com.peng.note.entity.ActivityFile;
import com.peng.note.service.ActivityFileService;
import com.peng.note.service.ActivityService;
import lombok.Builder;
import lombok.Data;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

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

    @Resource
    private ActivityFileDao activityFileDao;

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
        //如果图片名称的集合不为空，则根据文件名称查询文件记录表，更新记录的活动id
        if(activity.getFileNames() != null && !activity.getFileNames().isEmpty()){
            List<ActivityFile> activityFileList = activityFileDao.queryByNewName(activity.getFileNames());
            //给活动id赋值
            List<ActivityFile> fileList = activityFileList.stream().map(item -> {
                item.setActivityId(activity.getId());
                return item;
            }).collect(Collectors.toList());
            //得到所有文件id
            List<String> ids = activityFileList.stream().map(item -> item.getId()).collect(Collectors.toList());
            //先批量删除，再批量新增，防止在循环体中执行update操作
            activityFileDao.batchDelete(ids);
            activityFileDao.insertBatch(fileList);
        }
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
        //如果图片名称集合不为空，则根据文件名称查询文件记录表，更新最新的文件记录
        if(activity.getFileNames() != null && !activity.getFileNames().isEmpty()){
            List<ActivityFile> activityFileList = activityFileDao.queryByNewName(activity.getFileNames());
            //遍历这个集合，找到已经多余的文件记录，删除
            List<String> ids = new ArrayList<>();
            for (ActivityFile file : activityFileList) {
                if(!activity.getFileNames().contains(file.getNewName())){
                    ids.add(file.getId());
                }
                //猜测还存在一种可能：编辑时，上传的文件还是没有指定活动id，导致活动id为空，此时这里需要添加上活动id
            }
            //批量删除多余的文件记录
            if(!ids.isEmpty()){
                activityFileDao.batchDelete(ids);
            }
        }
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
        //先将文件记录删除掉
        activityFileDao.deleteByActivity(id);
        return this.activityDao.deleteById(id) > 0;
    }

    /**
     * 查出所有活动列表
     * @return
     */
    @Override
    public List<Activity> queryAll() {
        return activityDao.queryAll();
    }

    /**
     * 统计日期对应的活动数量
     * @return
     */
    @Override
    public List<NumberOfActivityDate> countNumberOfActivityDate(){
        //查出所有的活动列表
        List<Activity> activityList = activityDao.queryAll();
        //根据年月日分组，key:日期  value:活动列表
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        TreeMap<String, List<Activity>> treeMap = new TreeMap<>();
        for (Activity activity : activityList) {
            String date = format.format(activity.getStarttime());
            if(treeMap.containsKey(date)){
                treeMap.get(date).add(activity);
            }else{
                List<Activity> list = new ArrayList<>();
                list.add(activity);
                treeMap.put(date, list);
            }
        }
        //组装内部类格式
        List<NumberOfActivityDate> list = new ArrayList<>();
        for (Map.Entry<String, List<Activity>> entry : treeMap.entrySet()) {
            list.add(NumberOfActivityDate.builder()
                    .date(entry.getKey())
                    .number(entry.getValue().size())
                    .build());
        }
        return list;
    }

    @Data
    @Builder
    public static class NumberOfActivityDate{
        private String date;
        private Integer number;
    }
}
