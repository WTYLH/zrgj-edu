package com.zrgj.serviceedu.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zrgj.serviceedu.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zrgj.serviceedu.entity.vo.CourseInfoVo;
import com.zrgj.serviceedu.entity.vo.CoursePublishVo;
import com.zrgj.serviceedu.entity.vo.CourseQuery;
import com.zrgj.serviceedu.entity.vo.CourseWebVo;
import com.zrgj.serviceedu.front.CourseQueryVo;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author wty
 * @since 2023-07-26
 */
public interface EduCourseService extends IService<EduCourse> {

    String saveCourseInfo(CourseInfoVo courseInfoVo);

    CourseInfoVo getCourseInfo(String courseId);

    void updateCourseInfo(CourseInfoVo courseInfoVo);

    CoursePublishVo getCoursePublishVoById(String id);

    boolean publishCourseById(String id);

    void pageQuery(Page<EduCourse> pageParam, CourseQuery courseQuery);

    boolean removeCourseById(String id);

    List<EduCourse> selectByTeacherId(String teacherId);

    Map<String, Object> pageListWeb(Page<EduCourse> pageParam, CourseQueryVo courseQuery);

    /**
     * 获取课程信息
     * @param id
     * @return
     */
    CourseWebVo selectInfoWebById(String id);

    /**
     * 更新课程浏览数
     * @param id
     */
    void updatePageViewCount(String id);
}
