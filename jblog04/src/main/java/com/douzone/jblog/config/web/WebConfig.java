package com.douzone.jblog.config.web;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.douzone.config.web.FileUploadConfig;
import com.douzone.config.web.MessageConfig;
import com.douzone.config.web.MvcConfig;
import com.douzone.config.web.SecurityConfig;

@Configuration
@EnableAspectJAutoProxy
@Import({MvcConfig.class, SecurityConfig.class,  MessageConfig.class, FileUploadConfig.class})
@ComponentScan({"com.douzone.jblog.controller", "com.douzone.jblog.exception"})
public class WebConfig implements WebMvcConfigurer{
	
}
