package cn.haoyu.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * Created by haoyu on 2018/5/23.
 */
@SpringBootApplication(scanBasePackages = "cn.haoyu.client")
@ServletComponentScan(basePackages = "cn.haoyu.client")
public class ChatClientApplication extends SpringBootServletInitializer {

    public static void main(String[] args){
        SpringApplication.run(ChatClientApplication.class);
    }

}
