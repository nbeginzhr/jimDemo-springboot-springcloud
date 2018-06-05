package cn.haoyu.jimChat;

import cn.haoyu.jimChat.listener.JoinGroupListener;
import cn.haoyu.jimChat.processor.HaoLoginProcess;
import cn.haoyu.jimChat.processor.HaoWsHandShakeProcess;
import cn.haoyu.jimChat.processor.PersistChatProessor;
import org.jim.common.ImConfig;
import org.jim.common.packets.Command;
import org.jim.server.ImServerStarter;
import org.jim.server.command.CommandManager;
import org.jim.server.command.handler.ChatReqHandler;
import org.jim.server.command.handler.HandshakeReqHandler;
import org.jim.server.command.handler.LoginReqHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

import java.io.IOException;

/**
 * Created by haoyu on 2018/5/23.
 */
@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
@EnableHystrix
public class JimChatApplication extends SpringBootServletInitializer {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(JimChatApplication.class);
        startListener();
    }

    public static void startListener() throws IOException {
        ImConfig imConfig = new ImConfig(null, 9090);
//        HttpConfig httpConfig = new HttpConfig();
//        // 不设缓存
//        httpConfig.setMaxLiveTimeOfStaticRes(0);
////        httpConfig.setPageRoot("templates");
//        httpConfig.setScanPackages(new String[]{"cn.haoyu.jimChat"});
//        imConfig.setHttpConfig(httpConfig);
        ImServerStarter imServerStarter = new ImServerStarter(imConfig, new JoinGroupListener());
        HandshakeReqHandler handshakeHandler = CommandManager.getCommand(Command.COMMAND_HANDSHAKE_REQ, HandshakeReqHandler.class);
        handshakeHandler.addProcessor(new HaoWsHandShakeProcess());
        LoginReqHandler loginReqHandler = CommandManager.getCommand(Command.COMMAND_LOGIN_REQ, LoginReqHandler.class);
        loginReqHandler.addProcessor(new HaoLoginProcess());

        ChatReqHandler chatHandler = CommandManager.getCommand(Command.COMMAND_CHAT_REQ, ChatReqHandler.class);
        chatHandler.addProcessor(new PersistChatProessor());
        imServerStarter.start();
    }

}
