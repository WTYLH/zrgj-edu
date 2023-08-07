package com.zrgj.serviceedu.controller;

import com.zrgj.commonutils.R;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

@Api("登录页面")
@RestController
@RequestMapping("/eduservice/user")
//@CrossOrigin //解决跨域访问
//不能使用网关进行跨域后再跨域
public class EduLoginController {

    //login方法,返回token
    @PostMapping("login")
    public R login(){
        return R.ok();
    }

    //info方法，返回三个roles、name、avatar
    @GetMapping ("info")
    public R info(){
        return R.ok().data("roles","[admin]").data("name","admin").data("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
    }
}
