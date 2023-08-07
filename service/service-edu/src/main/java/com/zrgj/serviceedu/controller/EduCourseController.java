package com.zrgj.serviceedu.controller;


import com.zrgj.commonutils.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zrgj.serviceedu.entity.vo.CourseQuery;
import com.zrgj.serviceedu.service.EduChapterService;
import com.zrgj.serviceedu.service.EduCourseService;
import com.zrgj.serviceedu.entity.EduCourse;
import com.zrgj.serviceedu.entity.chapter.ChapterVo;
import com.zrgj.serviceedu.entity.vo.CourseInfoVo;
import com.zrgj.serviceedu.entity.vo.CoursePublishVo;
import com.zrgj.serviceedu.entity.vo.CourseWebVo;
import com.zrgj.serviceedu.front.CourseQueryVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author wty
 * @since 2023-07-26
 */


@Api(description="课程管理")
//@CrossOrigin //跨域
@RestController
@RequestMapping("/eduservice/edu-course")
public class EduCourseController {
    @Autowired
    private EduCourseService eduCourseService;

    ////    @ApiOperation(value = "新增课程")
////    @PostMapping("addCourseInfo")
////    public R saveCourseInfo(
////            @ApiParam(name = "CourseInfoForm", value = "课程基本信息", required = true)
////            @RequestBody CourseInfoVo courseInfoVo){
////         String courseId = eduCourseService.saveCourseInfo(courseInfoVo);
////        if(!StringUtils.isEmpty(courseId)){
////            return R.ok().data("courseId", courseId);
////        }else{
////            return R.error().message("保存失败");
////        }
////    }
//    //添加课程基本信息的方法
//    @PostMapping("addCourseInfo")
//    public R addCourseInfo(@RequestBody CourseInfoVo courseInfoVo){
//        eduCourseService.saveCourseInfo(courseInfoVo);
//        return R.ok();
//    }
    @ApiOperation(value = "新增课程")
    @PostMapping("saveCourseInfo")
    public R saveCourseInfo(
            @ApiParam(name = "CourseInfoVo", value = "课程基本信息", required =
                    true)
            @RequestBody CourseInfoVo courseInfoVo) {
        String courseID = eduCourseService.saveCourseInfo(courseInfoVo);
        if (!StringUtils.isEmpty(courseID)) {
            return R.ok().data("courseID", courseID);
        } else {
            return R.error().message("保存失败");
        }
    }

    @GetMapping("getCourseInfo/{courseId}")
    public R getCourseInfo(@PathVariable String courseId) {
        CourseInfoVo courseInfoVo = eduCourseService.getCourseInfo(courseId);
        return R.ok().data("courseInfoVo", courseInfoVo);
    }

    //修改课程信息
    @PostMapping("updateCourseInfo")
    public R updateCourseInfo(@RequestBody CourseInfoVo courseInfoVo) {
        eduCourseService.updateCourseInfo(courseInfoVo);
        return R.ok();
    }

    @ApiOperation(value = "根据ID获取课程发布信息")
    @GetMapping("course-publish-info/{id}")
    public R getCoursePublishVoById(
            @ApiParam(name = "id", value = "课程ID", required = true)
            @PathVariable String id) {
        CoursePublishVo courseInfoForm = eduCourseService.getCoursePublishVoById(id);
        return R.ok().data("item", courseInfoForm);
    }

    @ApiOperation(value = "根据id发布课程")
    @PutMapping("publish-course/{id}")
    public R publishCourseById(
            @ApiParam(name = "id", value = "课程ID", required = true)
            @PathVariable String id) {
        eduCourseService.publishCourseById(id);
        boolean result = eduCourseService.publishCourseById(id);
        if (result) {
            return R.ok();
        } else {
            return R.error().message("删除失败");
        }
    }

    @ApiOperation(value = "分页课程列表")
    @GetMapping("{page}/{limit}")
    public R pageQuery(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Long page,
            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit,
            @ApiParam(name = "courseQuery", value = "查询对象", required = false)
            CourseQuery courseQuery){
        Page<EduCourse> pageParam = new Page<>(page, limit);
        eduCourseService.pageQuery(pageParam, courseQuery);
        List<EduCourse> records = pageParam.getRecords();
        long total = pageParam.getTotal();
        return R.ok().data("total", total).data("rows", records);
    }
    @ApiOperation(value = "根据ID删除课程")
    @DeleteMapping("{id}")
    public R removeById(
            @ApiParam(name = "id", value = "课程ID", required = true)
            @PathVariable String id){
        boolean result = eduCourseService.removeCourseById(id);
        if(result){
            return R.ok();
        }else{
            return R.error().message("删除失败");
        }

    }
    //前台课程列表
    @ApiOperation(value = "分页课程列表")
    @PostMapping(value = "front/{page}/{limit}")
    public R pageList(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Long page,
            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit,
            @ApiParam(name = "courseQuery", value = "查询对象", required = false)
            @RequestBody(required = false) CourseQueryVo courseQuery){
        Page<EduCourse> pageParam = new Page<EduCourse>(page, limit);
        Map<String, Object> map = eduCourseService.pageListWeb(pageParam, courseQuery);
        return R.ok().data(map);
    }

    //前台根据ID查询课程详细信息
    @Autowired
    private EduChapterService chapterService;
    @ApiOperation(value = "根据ID查询课程")
    @GetMapping(value = "getFrontCourseInfo/{courseId}")
    public R getById(
            @ApiParam(name = "courseId", value = "课程ID", required = true)
            @PathVariable String courseId){
        //查询课程信息和讲师信息
        CourseWebVo courseWebVo = eduCourseService.selectInfoWebById(courseId);
        //查询当前课程的章节信息
        List<ChapterVo> chapterVoList = chapterService.getChapterVideoByCourseId(courseId);
        return R.ok().data("course", courseWebVo).data("chapterVoList", chapterVoList);
    }
}


