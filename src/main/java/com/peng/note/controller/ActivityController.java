package com.peng.note.controller;

import com.peng.note.entity.Activity;
import com.peng.note.service.ActivityService;
import com.peng.note.utils.ResultUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (Activity)表控制层
 *
 * @author makejava
 * @since 2022-02-26 14:23:48
 */
@RestController
@RequestMapping("activity")
public class ActivityController {
    /**
     * 服务对象
     */
    @Resource
    private ActivityService activityService;

    /**
     * 分页查询
     *
     * @param activity 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @GetMapping
    public ResponseEntity<Page<Activity>> queryByPage(Activity activity, PageRequest pageRequest) {
        return ResponseEntity.ok(this.activityService.queryByPage(activity, pageRequest));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResultUtils queryById(@PathVariable("id") String id) {
        return ResultUtils.ok(this.activityService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param activity 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<Activity> add(Activity activity) {
        return ResponseEntity.ok(this.activityService.insert(activity));
    }

    /**
     * 编辑数据
     *
     * @param activity 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResultUtils edit(Activity activity) {
        return ResultUtils.ok(this.activityService.update(activity));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(String id) {
        return ResponseEntity.ok(this.activityService.deleteById(id));
    }

}

