package com.zrgj.serviceedu.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zrgj.serviceedu.entity.EduTeacher;
import com.zrgj.serviceedu.entity.vo.TeacherQuery;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author atguigu
 * @since 2023-07-21
 */
public interface EduTeacherService extends IService<EduTeacher> {

    void pageQuery(Page<EduTeacher> pageParam, TeacherQuery teacherQuery);
    public Map<String, Object> pageListWeb(Page<EduTeacher> pageParam);
}
