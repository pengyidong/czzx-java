package com.peng.note.controller;

import com.peng.note.entity.Activity;
import com.peng.note.entity.ActivityFile;
import com.peng.note.service.ActivityFileService;
import com.peng.note.service.ActivityService;
import com.peng.note.utils.FileUtil;
import com.peng.note.utils.ResultUtils;
import com.peng.note.utils.SnowFlakeUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Date;
import java.util.List;
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

    @Value("${url.upload}")
    private String fileUploadUrl;

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

    /**
     *
     * @param request
     * @param fileType 文件类型 1是图片，2是文件
     * @param activityId 活动id
     * @return
     */
    @PostMapping("/multiUpload/{fileType}")
    public ResultUtils multiUpload(HttpServletRequest request,
                                   @PathVariable("fileType") String fileType, @RequestParam(value = "activityId", required = false) String activityId){
        List<String> fileList = activityFileService.multiUpload(request, fileType, activityId);
        if(fileList == null){
            return ResultUtils.build(500, "文件上传失败");
        }
        return ResultUtils.ok(fileList);
    }

    /**
     * 删除单个文件
     * @param fileName 类似  aaa.doc  的文件名
     * @return
     */
    @DeleteMapping("/deleteFile/{fileName}")
    public ResultUtils delete(@PathVariable("fileName") String fileName){
        return ResultUtils.ok(activityFileService.deleteFile(fileName));
    }

    /**
     * 批量删除文件
     * @param fileList 类似  aaa.doc  的文件名集合
     * @return
     */
    @DeleteMapping(value = "/multiDelete")
    public ResultUtils multiDelete(@RequestBody List<String> fileList){
        return ResultUtils.ok(activityFileService.multiDelete(fileList));
    }

    @GetMapping("/file/{type}/{fileName}")
    public void renderFile(HttpServletResponse response, @PathVariable String type, @PathVariable String fileName){
        if(type == null || "".equals(type) ||fileName == null || "".equals(fileName)) {
            return;
        }

        // /www/upload/
        StringBuilder filePath = new StringBuilder(fileUploadUrl);
        if("1".equals(type)){
            filePath.append("image/");
        }else if("2".equals(type)){
            filePath.append("file/");
        }
        filePath.append(fileName);

        try {
            byte[] bytes = FileUtil.toByteArray(filePath.toString());
            response.getOutputStream().write(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}

