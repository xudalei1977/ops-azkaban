package com.leiyu.ops.azkaban;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * This is main application
 *
 * @author Pitt
 * @date 2020-07-12
 */
@SpringBootApplication
@MapperScan(basePackages = { "com.leiyu.ops.azkaban.mapper" })
@Slf4j
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		log.info("Azkaban service started successfully.");
	}
}