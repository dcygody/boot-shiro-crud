package com.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.convert.converter.Converter;

import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootApplication
public class SpringBootWeb05Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootWeb05Application.class, args);
	}

	@Bean
	public Converter<String, Date> addNewConvert() {
		return new Converter<String, Date>() {
			@Override
			public Date convert(String source) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Date date = null;
				try {
					date = sdf.parse((String) source);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return date;
			}
		};
	}

//	@Bean
//	public Converter<String, Date> addNewConvert() {
//		return (source)->{
//			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//			Date date = null;
//			try {
//				date = sdf.parse((String) source);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//			return date;
//		};
//	}

}
