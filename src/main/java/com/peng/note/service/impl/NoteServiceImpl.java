package com.peng.note.service.impl;

import com.peng.note.entity.*;
import com.peng.note.service.*;
import com.peng.note.utils.SnowFlakeUtils;
import com.peng.note.utils.UserHandle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


/**
 * @Author : code
 * @Date 2022/2/11 20:56
 * @Version 1.0
 */
@Service
public class NoteServiceImpl implements NoteService {

    @Autowired
    private ActivityService activityService;

    @Autowired
    private ActivityTaskOwnerService activityTaskOwnerService;

    @Autowired
    private ActivityDockingStaffsService activityDockingStaffsService;

    @Autowired
    private UserService userService;


    @Override
    @Transactional
    public String add(Note note) {
        //TODO 创建新的活动项
        String activityId = new SnowFlakeUtils.IdWorker().nextId();
        Date now = new Date();

        UserPw userPw = UserHandle.get();
        User user = userService.queryById(userPw.getUserId());
        String nickName = user.getNickName();
        Activity activity = note.getActivity();
        activity.setId(activityId);
        activity.setCreatetime(now);
        activity.setCreateby(nickName);
        //保存入活动表,获取生成的活动id
        activityService.insert(activity);
        if (!CollectionUtils.isEmpty(note.getDockingStaffs())){
            //对接人不为空，保存入表 TODO
            List<String> dockingStaffs = note.getDockingStaffs();
            for (String dockingStaff : dockingStaffs) {
                ActivityDockingStaffs activityDockingStaffs = new ActivityDockingStaffs();
                activityDockingStaffs.setActivityId(activityId);
                activityDockingStaffs.setCreatetime(now);
                activityDockingStaffs.setCreateby(nickName);
                activityDockingStaffs.setDockingStaffs(dockingStaff);
                activityDockingStaffsService.insert(activityDockingStaffs);
            }


        }
        if (!CollectionUtils.isEmpty(note.getParticipants())){
            //参与人不为空，保存入表 TODO
            List<String> participants = note.getParticipants();
            for (String participant : participants) {
                ActivityTaskOwner activityTaskOwner = new ActivityTaskOwner();
                activityTaskOwner.setActivityId(activityId);
                activityTaskOwner.setCreateby(nickName);
                activityTaskOwner.setCreatetime(now);
                activityTaskOwner.setTaskOwner(participant);
                activityTaskOwnerService.insert(activityTaskOwner);
            }
        }
        return null;
    }

    @Override
    public void update(Note note) {
        //获取当前登录人信息
        UserPw userPw = UserHandle.get();
        String userName = userPw.getUserName();
        Date now = new Date();
        Activity activity = note.getActivity();
        String activityId = activity.getId();
        activity.setUpdateby(userName);
        activity.setUpdatetime(now);
        activityService.update(activity);
        //根据活动id清空对接人表和参与人表
        activityDockingStaffsService.deleteByActivityId(activityId);
        activityTaskOwnerService.deleteByActivityId(activityId);


        if (!CollectionUtils.isEmpty(note.getDockingStaffs())){
            //根据活动id清空对接人表，并插入 TODO
            ActivityDockingStaffs activityDockingStaffs = new ActivityDockingStaffs();
            activityDockingStaffs.setActivityId(activityId);
            activityDockingStaffs.setCreatetime(now);
            activityDockingStaffs.setCreateby(userName);
            activityDockingStaffsService.insert(activityDockingStaffs);
        }
        if (!CollectionUtils.isEmpty(note.getParticipants())){
            //根据活动id清空参与人表，并插入 TODO
            ActivityDockingStaffs activityDockingStaffs = new ActivityDockingStaffs();
            activityDockingStaffs.setActivityId(activityId);
            activityDockingStaffs.setCreatetime(now);
            activityDockingStaffs.setCreateby(userName);
            activityDockingStaffsService.insert(activityDockingStaffs);
        }
    }

    @Override
    public Page<Note> queryByPage(Activity noteActivity, PageRequest page) {
        Page<Activity> activities = activityService.queryByPage(noteActivity, page);
        long totalElements = activities.getTotalElements();
        List<Activity> activityStream = activities.get().collect(Collectors.toList());
        List<Note> noteList = new ArrayList<>();
        for (Activity activity : activityStream) {
            Note note = new Note();
            String activityId = activity.getId();
            List<String> activityTaskOwners = activityTaskOwnerService.queryByActivityId(activityId);

            List<String> activityDockingStaffs = activityDockingStaffsService.queryByActivityId(activityId);
            note.setActivity(activity);
            note.setDockingStaffs(activityDockingStaffs);
            note.setParticipants(activityTaskOwners);
            noteList.add(note);
        }
        Page<Note> notePage = new PageImpl<>(noteList,page,totalElements);
        return notePage;
    }

    @Override
    public Boolean delete(String activityId) {
        activityService.deleteById(activityId);
        activityDockingStaffsService.deleteByActivityId(activityId);
        activityTaskOwnerService.deleteByActivityId(activityId);
        return true;
    }
}
