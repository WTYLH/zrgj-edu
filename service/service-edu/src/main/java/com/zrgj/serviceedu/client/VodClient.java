package com.zrgj.serviceedu.client;

import com.zrgj.commonutils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@FeignClient(name ="service-vod",fallback = VodFileDegradeFeignClient.class)
@Component
public interface VodClient {
    @DeleteMapping(value = "/eduvod/video/removeViedo/{videoId}")
    public R removeVideo(@PathVariable("videoId") String videoId);


    @DeleteMapping(value = "/eduvod/video/delete-batch")
    public R removeVideoList(@RequestParam("videoIdList") List<String> videoIdList);


}
