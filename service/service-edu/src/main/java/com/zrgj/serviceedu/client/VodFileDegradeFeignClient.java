package com.zrgj.serviceedu.client;

import com.zrgj.commonutils.R;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class VodFileDegradeFeignClient implements VodClient {
    @Override
    public R removeVideo(String videoId) {
        return R.error().message("time out");
    }
    @Override
    public R removeVideoList(List videoIdList) {
        return R.error().message("time out");
    }
}
