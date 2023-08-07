package com.zrgj.educenter.service;

import com.zrgj.educenter.entity.StudentMember;
import com.zrgj.educenter.entity.Vo.LoginVo;
import com.zrgj.educenter.entity.Vo.RegisterVo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author wty
 * @since 2023-08-01
 */
public interface StudentMemberService extends IService<StudentMember> {

    String login(LoginVo loginVo);

    void register(RegisterVo registerVo);

    LoginVo getLoginInfo(String memberId);

    Integer countRegisterByDay(String day);
}
