package com.peng.note.service.impl;

import com.peng.note.entity.ActivityFile;
import com.peng.note.dao.ActivityFileDao;
import com.peng.note.service.ActivityFileService;
import com.peng.note.utils.SnowFlakeUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.apache.commons.io.FileUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

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

    @Value("${url.upload}")
    private String fileUploadUrl;

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
     * 批量新增数据
     *
     * @param entities 实例对象列表
     * @return 新增的行数
     */
    @Override
    public Integer batchInsert(List<ActivityFile> entities) {
        int row = this.activityFileDao.insertBatch(entities);
        return row;
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

    /**
     * 批量上传文件
     * @param request
     * @param fileType 文件类型 1是图片，2是文件
     * @param activityId 活动id
     * @return
     */
    @Override
    public List<String> multiUpload(HttpServletRequest request, String fileType, String activityId) {
        List<MultipartFile> files = ((MultipartHttpServletRequest)request).getFiles("file");

        String uploadUrl = fileUploadUrl;
        if("1".equals(fileType)){
            uploadUrl += "image/";
        }else if("2".equals(fileType)) {
            uploadUrl += "file/";
        }

        //不存在则创建目录
        File dir = new File(uploadUrl);
        if(!dir.exists()){
            dir.mkdir();
        }

        //上传的文件名列表
        List<String> fileNames = new ArrayList<>();

        //活动文件列表
        List<ActivityFile> activityFileList = new ArrayList<>();

        try {
            for (int i = 0; i < files.size(); i++) {
                MultipartFile file = files.get(i);
                //将上传的文件名替换为uuid
                //上传的文件名称 + 后缀
                String fileName = file.getOriginalFilename();
                //无后缀原文件名
                String prefixName = fileName.substring(0, fileName.lastIndexOf("."));
                //后缀
                String suffixName = fileName.substring(fileName.lastIndexOf("."));
                //无后缀新的文件名
                String newName = UUID.randomUUID().toString().replace("-", "");

                //组成活动文件对象
                ActivityFile activityFile = new ActivityFile();
                activityFile.setId(new SnowFlakeUtils.IdWorker().nextId());
                activityFile.setCreatetime(new Date());
                activityFile.setCreateby("admin");
                activityFile.setActivityId(activityId);
                activityFile.setFileType(fileType);
                activityFile.setFileName(prefixName + suffixName);
                activityFile.setNewName(newName + suffixName);
                activityFile.setFileExtension(suffixName);
                activityFile.setFilePath(uploadUrl + newName + suffixName);
                activityFileList.add(activityFile);

                //添加新的文件名
                fileNames.add(newName + suffixName);
                if ((file.isEmpty())){
                    return null;
                }
                File fileSource = new File(uploadUrl, newName + suffixName);
                FileUtils.copyInputStreamToFile(file.getInputStream(), fileSource);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        //添加到数据库中
        if(activityFileList.size() > 0){
            batchInsert(activityFileList);
        }
        return fileNames;
    }

    /**
     * 删除单个文件
     * @param fileName
     * @return
     */
    @Override
    public Boolean deleteFile(String fileName) {
        String filePath = fileUploadUrl + fileName;
        File file = new File(filePath);
        boolean isDeleted = false;
        if (file.exists()) {
            isDeleted = file.delete();
        }
        //去除后缀，并按照文件名称从数据库中删除记录
        activityFileDao.deleteByNewFile(Arrays.asList(fileName.substring(0, fileName.lastIndexOf("."))));
        return isDeleted;
    }

    /**
     * 根据文件名称列表删除多个文件
     * @param fileNames
     * @return
     */
    @Override
    public Boolean multiDelete(List<String> fileNames) {
        for (String fileName : fileNames) {
            String filePath = fileUploadUrl + fileName;
            File file = new File(filePath);
            if (file.exists()) {
                file.delete();
            }
        }
        //将所有文件名去除后缀
        List<String> files = fileNames.stream().map(item -> item.substring(0, item.lastIndexOf("."))).collect(Collectors.toList());
        //从数据库中删除记录
        activityFileDao.deleteByNewFile(files);
        return true;
    }

    /**
     * 根据活动id查询文件记录
     * @param activityId
     * @return
     */
    @Override
    public List<ActivityFile> queryByActivity(String activityId) {
        return activityFileDao.queryByActivity(activityId);
    }
}
