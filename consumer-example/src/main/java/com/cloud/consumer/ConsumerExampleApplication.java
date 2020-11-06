package com.cloud.consumer;

import com.cloud.consumer.feign.CallProviderClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@EnableFeignClients
@SpringBootApplication
@EnableDiscoveryClient
public class ConsumerExampleApplication {

    @LoadBalanced
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    public static void main(String[] args) {
        SpringApplication.run(ConsumerExampleApplication.class, args);
    }

    @RestController
    public class TestController {

        private final RestTemplate restTemplate;
        @Autowired
        private CallProviderClient callProviderClient;

        @Autowired
        public TestController(RestTemplate restTemplate) {
            this.restTemplate = restTemplate;
        }

        @RequestMapping(value = "/echo/{str}", method = RequestMethod.GET)
        public String echo(@PathVariable String str) {
            return restTemplate.getForObject("http://service-provider/provider/echo/" + str, String.class);
        }

        @RequestMapping(value = "/call", method = RequestMethod.GET)
        public String call(@RequestParam("user") String user) {
            callProviderClient.call(user, "123456");
            return "OK";
        }
    }
}

