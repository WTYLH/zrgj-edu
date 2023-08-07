package com.zrgj.serviceedu.entity.subject;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;


//课程分类管理的实体类（代表一级分类标题）
@Data
public class OneSubject {

    private String id;

    private String title;
    //一个一级分类有多个二级分类
    private List<TwoSubject> children = new ArrayList<>();
}
