package com.peng.note.controller;

import com.peng.note.entity.ActivityTaskOwner;
import com.peng.note.service.ActivityTaskOwnerService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (ActivityTaskOwner)表控制层
 *
 * @author makejava
 * @since 2022-02-20 13:33:01
 */
@RestController
@RequestMapping("activityTaskOwner")
public class ActivityTaskOwnerController {
    /**
     * 服务对象
     */
    @Resource
    private ActivityTaskOwnerService activityTaskOwnerService;

    /**
     * 分页查询
     *
     * @param activityTaskOwner 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @GetMapping
    public ResponseEntity<Page<ActivityTaskOwner>> queryByPage(ActivityTaskOwner activityTaskOwner, PageRequest pageRequest) {
        return ResponseEntity.ok(this.activityTaskOwnerService.queryByPage(activityTaskOwner, pageRequest));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<ActivityTaskOwner> queryById(@PathVariable("id") String id) {
        return ResponseEntity.ok(this.activityTaskOwnerService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param activityTaskOwner 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<ActivityTaskOwner> add(ActivityTaskOwner activityTaskOwner) {
        return ResponseEntity.ok(this.activityTaskOwnerService.insert(activityTaskOwner));
    }

    /**
     * 编辑数据
     *
     * @param activityTaskOwner 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<ActivityTaskOwner> edit(ActivityTaskOwner activityTaskOwner) {
        return ResponseEntity.ok(this.activityTaskOwnerService.update(activityTaskOwner));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(String id) {
        return ResponseEntity.ok(this.activityTaskOwnerService.deleteById(id));
    }

}

