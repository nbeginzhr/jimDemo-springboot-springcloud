package cn.haoyu.common;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * Created by haoyu on 2018/5/24.
 */
@ServletComponentScan(basePackages = "cn.haoyu.common")
@SpringBootApplication
@EnableEurekaClient
public class CommonApplication extends SpringBootServletInitializer {
    public static void main(String[] agrs) {
        SpringApplication.run(CommonApplication.class);
    }
}
