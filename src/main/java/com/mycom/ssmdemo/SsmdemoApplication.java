package com.mycom.ssmdemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

//@EnableScheduling       // 开启基于注解的定时任务
@MapperScan({"com.mycom.ssmdemo.mapper"})
@SpringBootApplication
public class SsmdemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SsmdemoApplication.class, args);
	}

}
