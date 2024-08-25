package com.ats;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ATSAdmin {
    public static void main(String[] args) {
        SpringApplication.run(ATSAdmin.class, args);
    }
}
