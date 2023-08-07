package com.zrgj.educenter.controller;


import com.zrgj.commonutils.JwtUtils;
import com.zrgj.commonutils.R;
import com.zrgj.educenter.entity.StudentMember;
import com.zrgj.educenter.entity.Vo.LoginVo;
import com.zrgj.educenter.entity.Vo.RegisterVo;
import com.zrgj.educenter.service.StudentMemberService;
import com.zrgj.servicebase.exceptionhandler.GuliException;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author wty
 * @since 2023-08-01
 */
@RestController
@RequestMapping("/educenter/student-member")
//@CrossOrigin
public class StudentMemberController {
    @Autowired
    private StudentMemberService memberService;
    @ApiOperation(value = "会员登录")
    @PostMapping("login")
    public R login(@RequestBody LoginVo loginVo) {
        String token = memberService.login(loginVo);
        return R.ok().data("token", token);
    }
    @ApiOperation(value = "会员注册")
    @PostMapping("register")
    public R register(@RequestBody RegisterVo registerVo){
        memberService.register(registerVo);
        return R.ok();
    }
    @ApiOperation(value = "根据token获取登录信息")
    @GetMapping("auth/getLoginInfo")
    public R getLoginInfo(HttpServletRequest request){
        try {
            String memberId = JwtUtils.getMemberIdByJwtToken(request);
            LoginVo loginVo = memberService.getLoginInfo(memberId);
            return R.ok().data("item", loginVo);
        }catch (Exception e){
            e.printStackTrace();
            throw new GuliException(20001,"error");
        }
    }

    //根据token字符串获取用户信息,实现用户id获取用户信息，返回用户信息对象
    @PostMapping("getInfoUc/{id}")
    public StudentMember getInfo(@PathVariable String id) {
        //根据用户id获取用户信息
        StudentMember memeber = memberService.getById(id);
        return memeber;
    }

    //统计某一天的注册人数
    @GetMapping(value = "countregister/{day}")
    public R registerCount(
            @PathVariable String day){
        Integer count = memberService.countRegisterByDay(day);
        return R.ok().data("countRegister", count);
    }
}

