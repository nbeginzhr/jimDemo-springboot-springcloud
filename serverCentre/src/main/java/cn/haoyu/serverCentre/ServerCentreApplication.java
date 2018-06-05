package cn.haoyu.serverCentre;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Created by haoyu on 2018/5/25.
 */
@SpringBootApplication
@EnableEurekaServer
public class ServerCentreApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(ServerCentreApplication.class);
    }
}
