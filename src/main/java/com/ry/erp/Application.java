package com.ry.erp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
@MapperScan(basePackages= {"com.ry.erp.sys.mapper"})
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	// 为了打包springboot项目
	protected SpringApplicationBuilder configure (SpringApplicationBuilder builder) {
		return builder.sources(this.getClass());
	}
}
