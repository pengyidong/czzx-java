package com.peng.note.entity;

import com.peng.note.entity.vo.TaskBaseVO;
import lombok.Data;

import java.io.File;
import java.util.Date;
import java.util.List;

/**
 * @Author : code
 * @Date 2022/2/11 20:42
 * @Version 1.0
 */
public class Note  {

    /**
     * 对接人员
     */
    private List<String> dockingStaffs;

    /**
     * 参与人员
     */
    private List<String> participants;

    private Activity activity;

    /**
     * 上传的文件名称列表（新文件名，无需加后缀）
     */
    private List<String> fileNames;

    public List<String> getDockingStaffs() {
        return dockingStaffs;
    }

    public void setDockingStaffs(List<String> dockingStaffs) {
        this.dockingStaffs = dockingStaffs;
    }

    public List<String> getParticipants() {
        return participants;
    }

    public void setParticipants(List<String> participants) {
        this.participants = participants;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public List<String> getFileNames() {
        return fileNames;
    }

    public void setFileNames(List<String> fileNames) {
        this.fileNames = fileNames;
    }
}
