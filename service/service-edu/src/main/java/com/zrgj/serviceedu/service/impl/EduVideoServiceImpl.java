package com.zrgj.serviceedu.service.impl;

import com.zrgj.commonutils.R;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zrgj.servicebase.exceptionhandler.GuliException;
import com.zrgj.serviceedu.client.VodClient;
import com.zrgj.serviceedu.mapper.EduVideoMapper;
import com.zrgj.serviceedu.service.EduVideoService;
import com.zrgj.serviceedu.entity.EduVideo;
import com.zrgj.serviceedu.entity.vo.VideoInfoForm;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author wty
 * @since 2023-07-26
 */
@Service
public class EduVideoServiceImpl extends ServiceImpl<EduVideoMapper, EduVideo> implements EduVideoService {


    @Autowired
    private VodClient vodClient;

    @Override
    public void saveVideoInfo(VideoInfoForm videoInfoForm) {
        EduVideo eduVideo = new EduVideo();
        BeanUtils.copyProperties(videoInfoForm, eduVideo);
        boolean result = this.save(eduVideo);
        if (!result) {
            throw new GuliException(20001, "课时信息保存失败");
        }
    }

    @Override
    public VideoInfoForm getVideoInfoFormById(String id) {
//从video表中取数据
        EduVideo eduVideo = this.getById(id);
        if (eduVideo == null) {
            throw new GuliException(20001, "数据不存在");
        }
        //创建videoInfoForm对象
        VideoInfoForm videoInfoForm = new VideoInfoForm();
        BeanUtils.copyProperties(eduVideo, videoInfoForm);
        return videoInfoForm;
    }

    //    @Override
//    public boolean removeVideoById(String id) {
//        //删除视频资源 TODO
//        Integer result = baseMapper.deleteById(id);
//        return null != result && result > 0;
//    }
    @Override
    public boolean removeVideoById(String id) {
        //查询云端视频id
        EduVideo eduVideo = baseMapper.selectById(id);
        String videoSourceId = eduVideo.getVideoSourceId();
        //删除视频资源
        if (!StringUtils.isEmpty(videoSourceId)) {
            R resultStatus = vodClient.removeVideo(videoSourceId);
            if(resultStatus.getCode() == 20001){
                throw new GuliException(20001,"删除视频失败，熔断器...");
            }
        }
        Integer result = baseMapper.deleteById(id);
        return null != result && result > 0;
    }

    @Override
    public void updateVideoInfoById(VideoInfoForm videoInfoForm) {
//保存课时基本信息
        EduVideo eduVideo = new EduVideo();
        BeanUtils.copyProperties(videoInfoForm, eduVideo);
        boolean result = this.updateById(eduVideo);
        if (!result) {
            throw new GuliException(20001, "课时信息保存失败");
        }
    }


    @Override
    public boolean removeByCourseId(String courseId) {
        //根据课程id查询所有视频列表
        QueryWrapper<EduVideo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("course_id", courseId);
        queryWrapper.select("video_source_id");
        List<EduVideo> videoList = baseMapper.selectList(queryWrapper);
        //得到所有视频列表的云端原始视频id
        List<String> videoSourceIdList = new ArrayList<>();
        for (int i = 0; i < videoList.size(); i++) {
            EduVideo eduVideo = videoList.get(i);
            String videoSourceId = eduVideo.getVideoSourceId();
            if(!StringUtils.isEmpty(videoSourceId)){
                videoSourceIdList.add(videoSourceId);
            }
        }
        //调用vod服务删除远程视频
        if(videoSourceIdList.size() > 0){
            vodClient.removeVideoList(videoSourceIdList);
        }
        //删除video表示的记录
        QueryWrapper<EduVideo> queryWrapper2 = new QueryWrapper<>();
        queryWrapper2.eq("course_id", courseId);
        Integer count = baseMapper.delete(queryWrapper2);
        return null != count && count > 0;
    }
}
