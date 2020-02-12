package com.mycom.ssmdemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan({"com.mycom.ssmdemo.mapper"})
@SpringBootApplication
public class SsmdemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SsmdemoApplication.class, args);
	}

}
