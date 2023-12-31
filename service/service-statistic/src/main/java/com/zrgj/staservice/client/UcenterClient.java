package com.zrgj.staservice.client;

import com.zrgj.commonutils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient("service-ucenter")
public interface UcenterClient {
    @GetMapping(value = "/educenter/student-member/countregister/{day}")
    public R registerCount(@PathVariable("day") String day);
}
