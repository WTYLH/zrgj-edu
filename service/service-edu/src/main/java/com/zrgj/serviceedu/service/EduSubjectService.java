package com.zrgj.serviceedu.service;

import com.zrgj.serviceedu.entity.subject.OneSubject;
import com.zrgj.serviceedu.entity.EduSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author wty
 * @since 2023-07-25
 */
public interface EduSubjectService extends IService<EduSubject> {
    //添加课程分类
    void importSubjectData(MultipartFile file, EduSubjectService subjectService);

    //课程分类列表（树形）
    List<OneSubject> getAllOneTwoSubject();
}
