package com.zrgj.educms.controller;

import com.zrgj.commonutils.R;
import com.zrgj.educms.entity.CmsBanner;
import com.zrgj.educms.service.CmsBannerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//@CrossOrigin //跨域
@RestController
@RequestMapping("/educms/banner")
@Api(description = "网站前台Banner列表")

public class BannerApiController {
    @Autowired
    private CmsBannerService bannerService;
    @ApiOperation(value = "获取首页banner")
    @GetMapping("getAllBanner")
    public R index() {
        List<CmsBanner> list = bannerService.selectIndexList();
        return R.ok().data("bannerList", list);
    }
}

