package com.wang;

import org.mybatis.spring.annotation.MapperScan;


import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.annotation.ComponentScan;
 
@SpringBootApplication
//@EnableEurekaClient
@MapperScan("com.wang.dao")//扫描
//@EnableEurekaClient//服务注册
//@EnableDiscoveryClient//服务发现

public class Application {
	

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
