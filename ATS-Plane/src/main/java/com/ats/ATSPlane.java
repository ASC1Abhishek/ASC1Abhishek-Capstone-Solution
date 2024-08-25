package com.ats;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ATSPlane
{
    public static void main(String[] args) {
        SpringApplication.run(ATSPlane.class, args);
    }
}
