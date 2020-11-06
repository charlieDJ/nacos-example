package com.cloud.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@EnableFeignClients
@SpringBootApplication
@EnableDiscoveryClient
public class ProviderExampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProviderExampleApplication.class, args);
    }

    @RestController
    class EchoController {
        @RequestMapping(value = "/echo/{string}", method = RequestMethod.GET)
        public String echo(@PathVariable String string) {
            System.err.println(string);
            return "Hello Nacos Discovery " + string;
        }

        @RequestMapping(value = "/call", method = RequestMethod.GET)
        public void call(@RequestParam("user") String user, HttpServletRequest request) {
            String cookie = request.getHeader("Cookie");
            System.out.println("cookie: " + cookie);
            System.err.println(user);
        }

    }
}

