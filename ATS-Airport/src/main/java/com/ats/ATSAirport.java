package com.ats;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ATSAirport
{
    public static void main(String[] args) {
        SpringApplication.run(ATSAirport.class, args);
    }
}