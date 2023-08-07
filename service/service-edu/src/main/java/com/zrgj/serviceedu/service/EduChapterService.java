package com.zrgj.serviceedu.service;

import com.zrgj.serviceedu.entity.EduChapter;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zrgj.serviceedu.entity.chapter.ChapterVo;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author wty
 * @since 2023-07-26
 */
public interface EduChapterService extends IService<EduChapter> {
    List<ChapterVo> getChapterVideoByCourseId(String courseId);

    boolean deleteChapter(String chapterId);
    boolean removeByCourseId(String courseId);
}
