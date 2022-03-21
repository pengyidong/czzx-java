package com.peng.note.service;

import com.peng.note.entity.ActivityFile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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
     * 批量新增数据
     *
     * @param entities 实例对象列表
     * @return 新增的行数
     */
    Integer batchInsert(List<ActivityFile> entities);

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

    /**
     *
     * @param request
     * @param fileType 文件类型 1是图片，2是文件
     * @param activityId 活动id
     * @return
     */
    List<String> multiUpload(HttpServletRequest request, String fileType, String activityId);

    /**
     * 删除单个文件
     * @param fileName
     * @return
     */
    Boolean deleteFile(String fileName);

    /**
     * 批量删除文件
     * @param fileNames
     * @return
     */
    Boolean multiDelete(List<String> fileNames);

    /**
     * 根据活动id查询文件记录
     * @param activityId
     * @return
     */
    List<ActivityFile> queryByActivity(String activityId);


}
