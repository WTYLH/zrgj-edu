package com.zrgj.serviceedu.service;

import com.zrgj.serviceedu.entity.EduVideo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zrgj.serviceedu.entity.vo.VideoInfoForm;

/**
 * <p>
 * 课程视频 服务类
 * </p>
 *
 * @author wty
 * @since 2023-07-26
 */
public interface EduVideoService extends IService<EduVideo> {

    void saveVideoInfo(VideoInfoForm videoInfoForm);

    boolean removeVideoById(String id);

    void updateVideoInfoById(VideoInfoForm videoInfoForm);

    VideoInfoForm getVideoInfoFormById(String id);

    boolean removeByCourseId(String courseId);
}
