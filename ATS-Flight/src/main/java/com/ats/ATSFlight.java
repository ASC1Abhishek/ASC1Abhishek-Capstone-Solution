package com.ats;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ATSFlight
{
    public static void main(String[] args) {
        SpringApplication.run(ATSFlight.class, args);
    }
}
