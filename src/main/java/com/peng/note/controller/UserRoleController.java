package com.peng.note.controller;

import com.peng.note.aop.UserLogin;
import com.peng.note.entity.UserPw;
import com.peng.note.entity.UserRole;
import com.peng.note.entity.eum.RoleEnum;
import com.peng.note.service.RoleService;
import com.peng.note.service.UserPwService;
import com.peng.note.service.UserRoleService;
import com.peng.note.utils.ResultUtils;
import com.peng.note.utils.UserHandle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * (UserRole)表控制层
 *
 * @author makejava
 * @since 2022-02-20 13:33:03
 */
@RestController
@RequestMapping("userRole")
@UserLogin
public class UserRoleController {
    /**
     * 服务对象
     */
    @Resource
    private UserRoleService userRoleService;

    @Autowired
    private UserPwService userPwService;

    @Autowired
    private RoleService roleService;

    /**
     * 分页查询
     *
     * @param userRole 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @GetMapping
    public ResponseEntity<Page<UserRole>> queryByPage(UserRole userRole, PageRequest pageRequest) {
        return ResponseEntity.ok(this.userRoleService.queryByPage(userRole, pageRequest));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public List<UserRole> queryById(@PathVariable("id") String id) {
        return this.userRoleService.queryById(id);
    }

    /**
     * 新增数据
     *
     * @param userRole 实体
     * @return 新增结果
     */
    @PostMapping
    public ResultUtils add(UserRole userRole) {
        //校验该用户是否在数据库存在
        String userId = userRole.getUserId();
        UserPw userPw = userPwService.queryById(userId);
        if (userPw != null){
            //校验添加角色是否在角色EUM存在
            Integer roleId = userRole.getRoleId();
            if (roleService.queryById(roleId) == null){
                return ResultUtils.build(500,"系统不存在该角色");
            }
            //存在判断添加人是否有执行权限，有给该用户添加该角色
            UserPw userInfo = UserHandle.get();
            List<Integer> list = userRoleService.queryRoleList(userInfo.getUserId());
            if (list.contains(RoleEnum.ADMIN.getCode())){
                //校验添加角色是否含有该角色
                List<Integer> listRole = userRoleService.queryRoleList(userRole.getUserId());
                if (listRole.contains(roleId)){
                    return ResultUtils.build(500,"用户已有该角色");
                }
                userRoleService.insert(userRole);
                return ResultUtils.build(200,"添加成功");
            }
            return ResultUtils.build(500,"没有添加角色权限");

        }return ResultUtils.build(500,"该用户未注册不允许添加角色");
    }

    /**
     * 编辑数据
     *
     * @param userRole 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<UserRole> edit(UserRole userRole) {
        return ResponseEntity.ok(this.userRoleService.update(userRole));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(String id) {
        return ResponseEntity.ok(this.userRoleService.deleteById(id));
    }


    @RequestMapping(value = "delete",method = RequestMethod.POST)
    public ResultUtils deleteByUserId(String userId,Integer roleId){
        return ResultUtils.build(200,userRoleService.deleteByUserId(userId, roleId).toString());
    }
}

