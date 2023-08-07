package com.zrgj.serviceedu.controller;


import com.zrgj.commonutils.R;
import com.zrgj.serviceedu.service.EduChapterService;
import com.zrgj.serviceedu.entity.EduChapter;
import com.zrgj.serviceedu.entity.chapter.ChapterVo;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author wty
 * @since 2023-07-26
 */
@RestController
@RequestMapping("/eduservice/edu-chapter")
//@CrossOrigin
public class EduChapterController {

    @Autowired
    private EduChapterService eduChapterService;

    //课程大纲列表，根据课程id进行查询
    @GetMapping("getChapterVideo/{courseId}")
    public R getChapterListByCourseId(@ApiParam(name = "courseId", value = "课程ID", required = true)
                                          @PathVariable String courseId){
        List<ChapterVo> chapterVoList = eduChapterService.getChapterVideoByCourseId(courseId);
        return R.ok().data("items", chapterVoList);
    }
    @ApiOperation(value = "新增章节")
    @PostMapping("addChapter")
    public R addChapter(
            @ApiParam(name = "chapterVo", value = "章节对象", required = true)
            @RequestBody EduChapter eduChapter){
        eduChapterService.save(eduChapter);
        return R.ok();
    }
    @ApiOperation(value = "根据ID查询章节")
    @GetMapping("getChapterInfo/{chapterId}")
    public R getChapterInfo(
            @ApiParam(name = "id", value = "章节ID", required = true)
            @PathVariable String chapterId){
        EduChapter eduChapter = eduChapterService.getById(chapterId);
        return R.ok().data("items", eduChapter);
    }
    @ApiOperation(value = "修改章节")
    @PostMapping("updateChapter")
    public R updateChapter(
            @ApiParam(name = "chapter", value = "章节对象", required = true)
            @RequestBody EduChapter eduChapter){
        eduChapterService.updateById(eduChapter);
        return R.ok();
    }
    //章节中有小节，故需要自定义删除方法
    //当章节中有小节的时候，不允许删除章节
    @ApiOperation(value = "根据ID删除章节")
    @DeleteMapping("deleteChapter/{chapterId}")
    public R deleteChapter(
            @ApiParam(name = "id", value = "章节ID", required = true)
            @PathVariable String chapterId){
        boolean result = eduChapterService.deleteChapter(chapterId);
        if(result){
            return R.ok();
        }else{
            return R.error().message("删除失败");
        }
    }
}

