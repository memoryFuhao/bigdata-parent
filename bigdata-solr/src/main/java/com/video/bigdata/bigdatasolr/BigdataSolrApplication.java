package com.video.bigdata.bigdatasolr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.video.bigdata.bigdatasolr")
public class BigdataSolrApplication extends SpringBootServletInitializer{

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(BigdataSolrApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(BigdataSolrApplication.class,args);
	}
}

