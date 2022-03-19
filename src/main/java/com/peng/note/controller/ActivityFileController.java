package com.peng.note.controller;

import com.peng.note.entity.Activity;
import com.peng.note.entity.ActivityFile;
import com.peng.note.service.ActivityFileService;
import com.peng.note.service.ActivityService;
import com.peng.note.utils.ResultUtils;
import com.peng.note.utils.SnowFlakeUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.UUID;

/**
 * (ActivityFile)表控制层
 *
 * @author makejava
 * @since 2022-03-06 13:23:55
 */
@RestController
@RequestMapping("activityFile")
public class ActivityFileController {
    /**
     * 服务对象
     */
    @Resource
    private ActivityFileService activityFileService;

    @Autowired
    private ActivityService activityService;

    static  String uploadPath = "E:\\worker\\img";

    /**
     * 分页查询
     *
     * @param activityFile 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @GetMapping
    public ResponseEntity<Page<ActivityFile>> queryByPage(ActivityFile activityFile, PageRequest pageRequest) {
        return ResponseEntity.ok(this.activityFileService.queryByPage(activityFile, pageRequest));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<ActivityFile> queryById(@PathVariable("id") String id) {
        return ResponseEntity.ok(this.activityFileService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param file 实体
     * @return 新增结果
     */
    @PostMapping
    public ResultUtils add(String activityId, MultipartFile file) {
        //校验活动id是否为空，为空禁止上传
        if (StringUtils.isEmpty(activityId)){
            return ResultUtils.build(500, "活动id为空");
        }
        //查询活动id是否存在
        Activity activity = activityService.queryById(activityId);
        if (activity == null){
            return ResultUtils.build(500, "没有改活动存在");
        }

        //不为空执行上传
        //判断文件夹是否创建，未创建则创建文件夹
        File dir = new File(uploadPath);
        if (!dir.exists() || !dir.isDirectory()){
            dir.mkdirs();
        }

        String outPath = uploadPath + "\\" + UUID.randomUUID().toString();

        //上传 - 分布式文件系统（阿里云SSO文件系统）
        try (
                InputStream in = file.getInputStream();
                OutputStream out = new FileOutputStream(outPath);
        ) {
            //文件拷贝
            IOUtils.copy(in, out);
        } catch (Exception e){
            e.printStackTrace();
        }

        //设置反斜杠
        outPath = outPath.replace("\\", "/");
        ActivityFile activityFile = new ActivityFile();
        String filename = file.getOriginalFilename();
        String id = new SnowFlakeUtils.IdWorker().nextId();
        activityFile.setId(id);
        activityFile.setCreatetime(new Date());
        activityFile.setCreateby("admin");
        activityFile.setFileName(filename);
        activityFile.setFileType("123");
        String[] split = filename.split("\\.");
        String s = split[1];
        activityFile.setFileExtension(s);
        activityFile.setActivityId(activityId);
        activityFile.setFilePath(outPath);
        return ResultUtils.ok();
    }

    /**
     * 编辑数据
     *
     * @param activityFile 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<ActivityFile> edit(ActivityFile activityFile) {
        return ResponseEntity.ok(this.activityFileService.update(activityFile));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(String id) {
        return ResponseEntity.ok(this.activityFileService.deleteById(id));
    }

}

