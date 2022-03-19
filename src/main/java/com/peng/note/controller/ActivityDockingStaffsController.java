package com.peng.note.controller;

import com.peng.note.entity.ActivityDockingStaffs;
import com.peng.note.service.ActivityDockingStaffsService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (ActivityDockingStaffs)表控制层
 *
 * @author makejava
 * @since 2022-02-20 13:32:55
 */
@RestController
@RequestMapping("activityDockingStaffs")
public class ActivityDockingStaffsController {
    /**
     * 服务对象
     */
    @Resource
    private ActivityDockingStaffsService activityDockingStaffsService;

    /**
     * 分页查询
     *
     * @param activityDockingStaffs 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @GetMapping
    public ResponseEntity<Page<ActivityDockingStaffs>> queryByPage(ActivityDockingStaffs activityDockingStaffs, PageRequest pageRequest) {
        return ResponseEntity.ok(this.activityDockingStaffsService.queryByPage(activityDockingStaffs, pageRequest));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<ActivityDockingStaffs> queryById(@PathVariable("id") String id) {
        return ResponseEntity.ok(this.activityDockingStaffsService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param activityDockingStaffs 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<ActivityDockingStaffs> add(ActivityDockingStaffs activityDockingStaffs) {
        return ResponseEntity.ok(this.activityDockingStaffsService.insert(activityDockingStaffs));
    }

    /**
     * 编辑数据
     *
     * @param activityDockingStaffs 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<ActivityDockingStaffs> edit(ActivityDockingStaffs activityDockingStaffs) {
        return ResponseEntity.ok(this.activityDockingStaffsService.update(activityDockingStaffs));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(String id) {
        return ResponseEntity.ok(this.activityDockingStaffsService.deleteById(id));
    }

}

