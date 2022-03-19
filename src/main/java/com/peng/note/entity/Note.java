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
@Data
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


}
