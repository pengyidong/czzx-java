package com.peng.note.service;

import com.peng.note.entity.Activity;
import com.peng.note.entity.Note;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author : code
 * @Date 2022/2/11 20:56
 * @Version 1.0
 */
public interface NoteService {

     String add(Note note);

    void update(Note note);

    Page<Note> queryByPage(Activity activite, PageRequest page);

    Boolean delete(String activityId);
}
