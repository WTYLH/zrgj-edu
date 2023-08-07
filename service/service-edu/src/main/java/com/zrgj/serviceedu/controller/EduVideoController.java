package com.zrgj.serviceedu.controller;


import com.zrgj.commonutils.R;
import com.zrgj.serviceedu.service.EduVideoService;
import com.zrgj.serviceedu.entity.vo.VideoInfoForm;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author wty
 * @since 2023-07-26
 */
@RestController
@RequestMapping("/eduservice/edu-video")
//@CrossOrigin
public class EduVideoController {
    @Autowired
    private EduVideoService eduVideoService;


//
//    @ApiOperation(value = "新增课时")
//    @PostMapping("addVideo")
//    public R addVideo(
//            @ApiParam(name = "videoForm", value = "课时对象", required = true)
//            @RequestBody EduVideo eduVideo) {
//        eduVideoService.save(eduVideo);
//        return R.ok();
//    }
//
//    //删除小节
//    //需要完善，删除小节的时候，需要把里面的视频删除掉
//    @ApiOperation(value = "根据ID删除课时")
//    @DeleteMapping("{id}")
//    public R deleteVideo(
//            @ApiParam(name = "id", value = "课时ID", required = true)
//            @PathVariable String id) {
//        boolean result = eduVideoService.removeById(id);
//        if (result) {
//            return R.ok();
//        } else {
//            return R.error().message("删除失败");
//        }
//    }

//    @Override
//    public VideoInfoForm getVideoInfoFormById(String id) {
////从video表中取数据
//        Video video = this.getById(id);
//        if (video == null) {
//            throw new GuliException(20001, "数据不存在");
//        }
//        //创建videoInfoForm对象
//        VideoInfoForm videoInfoForm = new VideoInfoForm();
//        BeanUtils.copyProperties(video, videoInfoForm);
//        return videoInfoForm;
//    }
//
//    @Override
//    public void updateVideoInfoById(VideoInfoForm videoInfoForm) {
////保存课时基本信息
//        Video video = new Video();
//        BeanUtils.copyProperties(videoInfoForm, video);
//        boolean result = this.updateById(video);
//        if (!result) {
//            throw new GuliException(20001, "课时信息保存失败");
//        }
//    }

    @ApiOperation(value = "新增课时")
    @PostMapping("save-video-info")
    public R save(
            @ApiParam(name = "videoForm", value = "课时对象", required = true)
            @RequestBody VideoInfoForm videoInfoForm) {
        eduVideoService.saveVideoInfo(videoInfoForm);
        return R.ok();
    }


    @ApiOperation(value = "根据ID查询课时")
    @GetMapping("video-info/{id}")
    public R getVideInfoById(
            @ApiParam(name = "id", value = "课时ID", required = true)
            @PathVariable String id) {
        VideoInfoForm videoInfoForm = eduVideoService.getVideoInfoFormById(id);
        return R.ok().data("item", videoInfoForm);
    }

    @ApiOperation(value = "更新课时")
    @PutMapping("update-video-info/{id}")
    public R updateCourseInfoById(
            @ApiParam(name = "VideoInfoForm", value = "课时基本信息", required = true)
            @RequestBody VideoInfoForm videoInfoForm,
            @ApiParam(name = "id", value = "课时ID", required = true)
            @PathVariable String id) {
        eduVideoService.updateVideoInfoById(videoInfoForm);
        return R.ok();
    }

    @ApiOperation(value = "根据ID删除课时")
    @DeleteMapping("{id}")
    public R removeById(
            @ApiParam(name = "id", value = "课时ID", required = true)
            @PathVariable String id) {
        boolean result = eduVideoService.removeVideoById(id);
        if (result) {
            return R.ok();
        } else {
            return R.error().message("删除失败");
        }
    }



}

