package com.peng.note.controller;

import com.peng.note.entity.Role;
import com.peng.note.service.RoleService;
import com.peng.note.utils.ResultUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Role)表控制层
 *
 * @author makejava
 * @since 2022-02-20 13:33:02
 */
@RestController
@RequestMapping("role")
public class RoleController {
    /**
     * 服务对象
     */
    @Resource
    private RoleService roleService;

    /**
     * 分页查询
     *
     *
     * @return 查询结果
     */
    @GetMapping
    public ResultUtils queryByPage() {
        return ResultUtils.ok(roleService.queryByPage());
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResultUtils queryById(@PathVariable("id") Integer id) {
        return ResultUtils.ok(this.roleService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param role 实体
     * @return 新增结果
     */
    @PostMapping
    public ResultUtils add(Role role) {
        return ResultUtils.ok(this.roleService.insert(role));
    }

    /**
     * 编辑数据
     *
     * @param role 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResultUtils edit(Role role) {
        return ResultUtils.ok(this.roleService.update(role));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResultUtils deleteById(Integer id) {
        return ResultUtils.ok(this.roleService.deleteById(id));
    }

}

