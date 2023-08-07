package com.zrgj.serviceedu.mapper;

import com.zrgj.serviceedu.entity.EduCourse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zrgj.serviceedu.entity.vo.CoursePublishVo;
import com.zrgj.serviceedu.entity.vo.CourseWebVo;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author wty
 * @since 2023-07-26
 */
public interface EduCourseMapper extends BaseMapper<EduCourse> {
    CoursePublishVo getCoursePublishInfo(String id);

    //Mapper中关联查询课程和讲师信息
    CourseWebVo selectInfoWebById(String courseId);
}
