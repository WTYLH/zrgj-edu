package com.zrgj.serviceedu.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zrgj.servicebase.exceptionhandler.GuliException;
import com.zrgj.serviceedu.entity.excel.ExcelSubjectData;
import com.zrgj.serviceedu.entity.subject.OneSubject;
import com.zrgj.serviceedu.entity.subject.TwoSubject;
import com.zrgj.serviceedu.listener.SubjectExcelListener;
import com.zrgj.serviceedu.entity.EduSubject;
import com.zrgj.serviceedu.mapper.EduSubjectMapper;
import com.zrgj.serviceedu.service.EduSubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author wty
 * @since 2023-07-25
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {

    //添加课程分类
    //poi读取excel内容
    @Override
    public void importSubjectData(MultipartFile file, EduSubjectService subjectService)
    {
        try {
            //1 获取文件输入流
            InputStream inputStream = file.getInputStream();
            // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
            EasyExcel.read(inputStream, ExcelSubjectData.class, new SubjectExcelListener(subjectService)).sheet().doRead();
        }catch(Exception e) {
            e.printStackTrace();
            throw new GuliException(20002,"添加课程分类失败");
        }
    }

    @Override
    public List<OneSubject> getAllOneTwoSubject() {

        //1.查出所有的一级分类
        //2.查出所有二级分类
        //3，封装一级分类
        //4.封装二级分类


        //最终要的到的数据列表
        ArrayList<OneSubject> OneSubjectList = new ArrayList<>();
        //获取一级分类数据记录
        QueryWrapper<EduSubject> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("pid", 0);
        queryWrapper.orderByAsc("sort", "id");
        List<EduSubject> subjects = baseMapper.selectList(queryWrapper);
        //获取二级分类数据记录
        QueryWrapper<EduSubject> queryWrapper2 = new QueryWrapper<>();
        queryWrapper2.ne("pid", 0);
        queryWrapper2.orderByAsc("sort", "id");
        List<EduSubject> subSubjects = baseMapper.selectList(queryWrapper2);
        //填充一级分类vo数据
        int count = subjects.size();
        for (int i = 0; i < count; i++) {
            EduSubject subject = subjects.get(i);
            //创建一级类别vo对象
            OneSubject oneSubject = new OneSubject();
            BeanUtils.copyProperties(subject, oneSubject);
            OneSubjectList.add(oneSubject);
            //填充二级分类vo数据
            ArrayList<TwoSubject> TwoSubjectList = new ArrayList<>();
            int count2 = subSubjects.size();
            for (int j = 0; j < count2; j++) {
                EduSubject subSubject = subSubjects.get(j);
                if(subject.getId().equals(subSubject.getPid())){
                    //创建二级类别vo对象
                    TwoSubject twoSubject = new TwoSubject();
                    BeanUtils.copyProperties(subSubject, twoSubject);
                    TwoSubjectList.add(twoSubject);
                }
            }
            //把一级下面所有二级分类放到一级分类里面
            oneSubject.setChildren(TwoSubjectList);
        }
        return OneSubjectList;
    }

}
