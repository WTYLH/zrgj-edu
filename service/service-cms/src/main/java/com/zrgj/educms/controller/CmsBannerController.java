package com.zrgj.educms.controller;


import com.zrgj.commonutils.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zrgj.educms.service.CmsBannerService;
import com.zrgj.educms.entity.CmsBanner;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 首页banner表 前端控制器
 * </p>
 *
 * @author wty
 * @since 2023-07-31
 */
//@CrossOrigin
@RestController
@Api(description = "网站后台Banner列表")
@RequestMapping("/educms/cms-banner")

public class CmsBannerController {
    @Autowired
    private CmsBannerService bannerService;
    //1.分页查询Banner
    @ApiOperation(value = "获取Banner分页列表")
    @GetMapping("{page}/{limit}")
    public R index(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Long page,
            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit) {
        Page<CmsBanner> pageParam = new Page<>(page, limit);
        bannerService.pageBanner(pageParam,null);
        return R.ok().data("items", pageParam.getRecords()).data("total",
                pageParam.getTotal());
    }
    //2.获取Banner
    @ApiOperation(value = "获取Banner")
    @GetMapping("get/{id}")
    public R get(@PathVariable String id) {
        CmsBanner banner = bannerService.getBannerById(id);
        return R.ok().data("item", banner);
    }
    @ApiOperation(value = "新增Banner")
    @PostMapping("save")
    public R save(@RequestBody CmsBanner banner) {
        bannerService.saveBanner(banner);
        return R.ok();
    }
    @ApiOperation(value = "修改Banner")
    @PutMapping("update")
    public R updateById(@RequestBody CmsBanner banner) {
        bannerService.updateBannerById(banner);
        return R.ok();
    }
    @ApiOperation(value = "删除Banner")
    @DeleteMapping("remove/{id}")
    public R remove(@PathVariable String id) {
        bannerService.removeBannerById(id);
        return R.ok();
    }
}


