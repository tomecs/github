package com.feng;

import com.feng.github.config.FengApplilcation;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

/**
 * @description:
 * @author: fengmenglei
 * @date: 2018/2/10
 */
@FengApplilcation
@EnableConfigurationProperties
public class GithubApplication {
    public static void main(String[] args) {
        SpringApplication.run(GithubApplication.class, args);
    }
}
