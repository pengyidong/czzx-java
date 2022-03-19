package com.peng.note.controller;

import com.peng.note.aop.UserLogin;
import com.peng.note.entity.Activity;
import com.peng.note.entity.ActivityQuery;
import com.peng.note.entity.Note;
import com.peng.note.entity.UserPw;
import com.peng.note.service.NoteService;
import com.peng.note.service.UserRoleService;
import com.peng.note.utils.ExportExcelUtil;
import com.peng.note.utils.ResultUtils;
import com.peng.note.utils.UserHandle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;

/**
 * @Author : code
 * @Date 2022/2/11 20:57
 * @Version 1.0
 */
@RestController
@RequestMapping("/note")
public class NoteController {

    @Autowired
    private NoteService noteService;

    @Autowired
    private UserRoleService userRoleService;

    @RequestMapping(value = "/queryByPage",method = RequestMethod.POST)
    @UserLogin
    public ResultUtils queryByPage(@RequestBody ActivityQuery activityQuery){
        //按开始时间排序查询活动
//        Date date = new Date(endtime);
//        Date date1 = new Date(starttime);
//        activity.setEndtime(date);
//        activity.setStarttime(date1);
        PageRequest pageRequest = PageRequest.of(activityQuery.getNumber(), activityQuery.getSize());
        return ResultUtils.ok(noteService.queryByPage(activityQuery.getActivity(), pageRequest));
    }


    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @UserLogin
    public ResultUtils addActivity(@RequestBody Note note){
        UserPw userPw = UserHandle.get();
        List<Integer> roleList = userRoleService.queryRoleList(userPw.getUserId());
        if (!roleList.contains(1)){
            //没有管理员权限
            return ResultUtils.build(500, "没有管理员权限");
        }
        noteService.add(note);
        return ResultUtils.ok();
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    @UserLogin
    public ResultUtils updateActivity(@RequestBody Note note){
        UserPw userPw = UserHandle.get();
        List<Integer> roleList = userRoleService.queryRoleList(userPw.getUserId());
        if (!roleList.contains(1)){
            //没有管理员权限
            return ResultUtils.build(500, "没有管理员权限");
        }
        if (StringUtils.isEmpty(note.getActivity().getId())){
            return ResultUtils.build(500, "活动id不能为空");
        }
        noteService.update(note);
        return ResultUtils.ok();
    }


    @RequestMapping(value = "/delete",method = RequestMethod.DELETE)
    @UserLogin
    public ResultUtils delete(@RequestParam String activityId){
        noteService.delete(activityId);
        return ResultUtils.ok();
    }



    @RequestMapping(value = "/export",method = RequestMethod.POST)
    @UserLogin
    public ResultUtils delete(@RequestParam List<String> activityIdList){
        //文件导出，含图片
        return null;
    }



}
