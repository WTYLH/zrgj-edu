package com.zrgj.educenter.mapper;

import com.zrgj.educenter.entity.StudentMember;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 会员表 Mapper 接口
 * </p>
 *
 * @author wty
 * @since 2023-08-01
 */
public interface StudentMemberMapper extends BaseMapper<StudentMember> {

    Integer selectRegisterCount(String day);
}
