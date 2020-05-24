package com.aloneness.itoken.service.sso.service.consumer;

import com.aloneness.itoken.service.sso.service.consumer.fallback.RedisServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import sun.awt.SunHints;

@FeignClient(value = "itoken-service-redis", fallback = RedisServiceFallback.class)
@Service
public interface RedisService {

    @PostMapping(value = "/put")
    public String put(@RequestParam(value = "key") String key,
                    @RequestParam(value = "value") String value,
                    @RequestParam(value = "seconds") long seconds);

    @GetMapping(value = "/get")
    public String get(@RequestParam(value = "key") String key);
}
