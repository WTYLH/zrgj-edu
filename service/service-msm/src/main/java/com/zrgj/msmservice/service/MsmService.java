package com.zrgj.msmservice.service;

import java.util.Map;

public interface MsmService {
    boolean send(String phone, String SMS_462205644, Map<String, Object> param);
}
