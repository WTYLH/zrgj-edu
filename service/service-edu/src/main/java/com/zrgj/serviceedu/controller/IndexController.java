package com.zrgj.serviceedu.controller;

import com.zrgj.commonutils.R;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zrgj.serviceedu.entity.EduTeacher;
import com.zrgj.serviceedu.service.EduCourseService;
import com.zrgj.serviceedu.service.EduTeacherService;
import com.zrgj.serviceedu.entity.EduCourse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//@CrossOrigin
@RestController
@RequestMapping("/eduservice/Search")

public class IndexController {
    @Autowired
    private EduCourseService courseService;
    @Autowired
    private EduTeacherService teacherService;
    //查询前8条热门课程，查询前4条名师
    @GetMapping("index")
    public R index() {
        //查询前8条热门课程
        QueryWrapper<EduCourse> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("id");
        wrapper.last("limit 8");
        List<EduCourse> eduList = courseService.list(wrapper);
        //查询前4条名师
        QueryWrapper<EduTeacher> wrapperTeacher = new QueryWrapper<>();
        wrapperTeacher.orderByDesc("id");
        wrapperTeacher.last("limit 4");
        List<EduTeacher> teacherList = teacherService.list(wrapperTeacher);
        return R.ok().data("eduList",eduList).data("teacherList",teacherList);
    }
}
