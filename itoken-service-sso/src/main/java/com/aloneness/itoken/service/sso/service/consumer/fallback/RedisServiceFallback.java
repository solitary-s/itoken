package com.aloneness.itoken.service.sso.service.consumer.fallback;

import com.aloneness.itoken.common.utils.Fallback;
import com.aloneness.itoken.service.sso.service.consumer.RedisService;
import org.springframework.stereotype.Component;

@Component
public class RedisServiceFallback implements RedisService {
    @Override
    public String put(String key, String value, long seconds) {
        return null;
    }

    @Override
    public String get(String key) {
        return null;
    }
}
