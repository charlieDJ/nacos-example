package com.cloud.consumer.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "service-provider/provider")
public interface CallProviderClient {

    @RequestMapping("/call")
    void call(@RequestParam("user") String user, @RequestHeader("Cookie") String cookie);

}
