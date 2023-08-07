package com.zrgj.educms.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zrgj.educms.entity.CmsBanner;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 首页banner表 服务类
 * </p>
 *
 * @author wty
 * @since 2023-07-31
 */
public interface CmsBannerService extends IService<CmsBanner> {

    void pageBanner(Page<CmsBanner> pageParam, Object o);

    CmsBanner getBannerById(String id);

    void saveBanner(CmsBanner banner);

    void updateBannerById(CmsBanner banner);

    void removeBannerById(String id);

    List<CmsBanner> selectIndexList();
}
