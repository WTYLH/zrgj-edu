package com.zrgj.educms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zrgj.educms.entity.CmsBanner;
import com.zrgj.educms.mapper.CmsBannerMapper;
import com.zrgj.educms.service.CmsBannerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 首页banner表 服务实现类
 * </p>
 *
 * @author wty
 * @since 2023-07-31
 */
@Service
public class CmsBannerServiceImpl extends ServiceImpl<CmsBannerMapper, CmsBanner>
        implements CmsBannerService {
    @Cacheable(value = "banner", key = "'selectIndexList'")
    @Override
    public List<CmsBanner> selectIndexList() {
        List<CmsBanner> list = baseMapper.selectList(new
                QueryWrapper<CmsBanner>().orderByDesc("sort"));
        return list;
    }
    @Override
    public void pageBanner(Page<CmsBanner> pageParam, Object o) {
        baseMapper.selectPage(pageParam,null);
    }
    @Override
    public CmsBanner getBannerById(String id) {
        return baseMapper.selectById(id);
    }
    @CacheEvict(value = "banner", allEntries=true)
    @Override
    public void saveBanner(CmsBanner banner) {
        baseMapper.insert(banner);
    }
    @CacheEvict(value = "banner", allEntries=true)
    @Override
    public void updateBannerById(CmsBanner banner) {
        baseMapper.updateById(banner);
    }
    @CacheEvict(value = "banner", allEntries=true)
    @Override
    public void removeBannerById(String id) {
        baseMapper.deleteById(id);
    }
}
