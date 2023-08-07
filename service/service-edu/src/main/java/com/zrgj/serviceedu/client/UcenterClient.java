package com.zrgj.serviceedu.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//@Component
//@FeignClient(name="service-ucenter",fallback = UcenterClientImpl.class)
//public interface UcenterClient {
//    //根据用户id获取用户信息
//    @GetMapping("/ucenterservice/member/getUcenterPay/{memberId}")
//    public UcenterMemberPay getUcenterPay(@PathVariable("memberId") String memberId);
//}
//@Component
//public class UcenterClientImpl implements UcenterClient {
//    @Override
//    public UcenterMemberPay getUcenterPay(String memberId) {
//        return null;
//    }
//}