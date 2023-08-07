package com.zrgj.serviceedu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zrgj.serviceedu.mapper.EduChapterMapper;
import com.zrgj.serviceedu.service.EduChapterService;
import com.zrgj.serviceedu.service.EduVideoService;
import com.zrgj.servicebase.exceptionhandler.GuliException;
import com.zrgj.serviceedu.entity.EduChapter;
import com.zrgj.serviceedu.entity.EduVideo;
import com.zrgj.serviceedu.entity.chapter.ChapterVo;
import com.zrgj.serviceedu.entity.chapter.VideoVo;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author wty
 * @since 2023-07-26
 */
@Service
public class EduChapterServiceImpl extends ServiceImpl<EduChapterMapper, EduChapter> implements EduChapterService {


    //注入小节service
    @Autowired
    private EduVideoService eduVideoService;

    @Override
    public List<ChapterVo> getChapterVideoByCourseId(String courseId) {

        // 根据课程id查询课程里面所有的章节，课程大纲列表
        //根据课程id查询课程里面所有的小节
        //遍历查询章节list集合进行封装
        //遍历查询小节list集合，进行封装

        //最终要的到的数据列表
        ArrayList<ChapterVo> chapterVoArrayList = new ArrayList<>();
        //获取章节信息
        QueryWrapper<EduChapter> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("course_id", courseId);
        queryWrapper1.orderByAsc("sort", "id");
        List<EduChapter> eduChapterList = baseMapper.selectList(queryWrapper1);
        //获取小姐课时信息
        QueryWrapper<EduVideo> queryWrapper2 = new QueryWrapper<>();
        queryWrapper2.eq("course_id", courseId);
        queryWrapper2.orderByAsc("sort", "id");
        List<EduVideo> eduVideoList = eduVideoService.list(queryWrapper2);
        //填充章节vo数据
        for (int i = 0; i < eduChapterList.size(); i++) {
            EduChapter eduChapter = eduChapterList.get(i);
            //创建章节vo对象
            ChapterVo chapterVo = new ChapterVo();
            BeanUtils.copyProperties(eduChapter, chapterVo);
            chapterVoArrayList.add(chapterVo);
            //填充课时vo数据
            ArrayList<VideoVo> videoVoArrayList = new ArrayList<>();
            for (int j = 0; j < eduVideoList.size(); j++) {
                EduVideo eduVideo = eduVideoList.get(j);
                if (chapterVo.getId().equals(eduVideo.getChapterId())) {
                    //创建课时vo对象
                    VideoVo videoVo = new VideoVo();
                    BeanUtils.copyProperties(eduVideo, videoVo);
                    videoVoArrayList.add(videoVo);
                }
            }
            chapterVo.setChildren(videoVoArrayList);
        }
        return chapterVoArrayList;
    }

    @Override
    public boolean deleteChapter(String chapterId) {
        //以下是两种方式：

        //根据id查询是否存在视频，如果有则提示用户尚有子节点
//        if (eduVideoService.(chapterId)) {
//            throw new GuliException(20001, "该分章节下存在视频课程，请先删除视频课程");
//        }
//        Integer result = baseMapper.deleteById(chapterId);
//        return null != result && result > 0;
        QueryWrapper<EduVideo> wrapper = new QueryWrapper<>();
        wrapper.eq("chapter_id",chapterId);
        //查出几条记录
        int count = eduVideoService.count(wrapper);
        //判断
        if(count >0){
            //不进行删除
            throw new GuliException(20001,"该分章节下存在视频课程，请先删除视频课程");
        }else{
            //没章节，删除
            Integer result = baseMapper.deleteById(chapterId);
            return null != result && result > 0;
        }
    }

    @Override
    public boolean removeByCourseId(String courseId) {
        QueryWrapper<EduChapter> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("course_id", courseId);
        Integer count = baseMapper.delete(queryWrapper);
        return null != count && count > 0;
    }
}
