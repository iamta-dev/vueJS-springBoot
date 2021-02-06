package com.okta.springbootvue;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import lombok.*;
import java.util.stream.Stream;
import java.util.Arrays;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.core.Ordered;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import java.util.Collections;

import java.util.Date;
import java.util.Optional;

import com.okta.springbootvue.entity.test.*;
import com.okta.springbootvue.repository.test.*;

@SpringBootApplication  
public class SpringBootVueApplication {  
  
    public static void main(String[] args) {  
      SpringApplication.run(SpringBootVueApplication.class, args);  
    }  

    @Bean  
    ApplicationRunner init(StudentRepository studentRepository) {  
        return args -> {  
            // Student syatem
            Object[][] dataStudent = new Object[][] {
                {"อรธิวา เจริญศักดิ์","B6015695","CPE","0902654562"},
                {"วันชนะชัย เทียมภาค","B6045558","IT","0902654562"},
                {"วรวิทย์ แก้วกองกาด","B5601559","MT","0902654562"}, 
                {"ยินดี แก้มานี","B5601450","MT","0902654562"}, 
                {"กำลัง แก้กาด","B5921549","IT","0902654562"}, 
                {"มานี กองกาด","B5801349","MT","0902654562"}, 
                {"สมดี สำรอง","B6071553","ME","0902654562"}, 
              };
                    
            for (int i = 0; i < dataStudent.length; i++) {
              Student student = new Student();

              student.setName((String) dataStudent[i][0]);

              student.setStuCode((String) dataStudent[i][1]);

              student.setSubject((String) dataStudent[i][2]);

              student.setTel((String) dataStudent[i][3]);

              studentRepository.save(student);
            }
        };  
    }  

    @Bean
    public FilterRegistrationBean simpleCorsFilter() {
      UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
      CorsConfiguration config = new CorsConfiguration();
      config.setAllowCredentials(true);
      // *** URL below needs to match the Vue client URL and port ***
      config.setAllowedOrigins(Collections.singletonList("*"));
      config.setAllowedMethods(Collections.singletonList("*"));
      config.setAllowedHeaders(Collections.singletonList("*"));
      source.registerCorsConfiguration("/**", config);
      FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<>(new CorsFilter(source));
      bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
      return bean;
    }
 
}