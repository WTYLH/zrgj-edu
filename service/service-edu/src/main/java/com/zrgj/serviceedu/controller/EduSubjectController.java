package com.zrgj.serviceedu.controller;


import com.zrgj.commonutils.R;
import com.zrgj.serviceedu.entity.subject.OneSubject;
import com.zrgj.serviceedu.service.EduSubjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author wty
 * @since 2023-07-25
 */
@Api("课程标题分类管理")
@RestController
@RequestMapping("/eduservice/edu-subject")
//@CrossOrigin
public class EduSubjectController {

    @Autowired
    private EduSubjectService eduSubjectService;
    //添加课程分类
    @ApiOperation(value = "Excel批量导入")
    @PostMapping("addSubject")
    public R addSubject(MultipartFile file) {
    // 获取上传的excel文件 MultipartFile
    // 返回错误提示信息
        eduSubjectService.importSubjectData(file, eduSubjectService);
    // 判断返回集合是否为空
        return R.ok();
    }

    //课程分类所有列表
    @GetMapping("getAllSubject")
    public R getAllSubject(){
        List<OneSubject> oneSubjectsList = eduSubjectService.getAllOneTwoSubject();
        return R.ok().data("items", oneSubjectsList);
    }
}

