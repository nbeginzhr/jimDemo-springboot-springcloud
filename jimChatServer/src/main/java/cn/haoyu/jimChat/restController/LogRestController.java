//package cn.haoyu.jimChat.restController;
//
//import cn.haoyu.common.utils.HID;
//import cn.haoyu.jimChat.entity.LoginLog;
//import cn.haoyu.jimChat.server.LoginLogServer;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.Date;
//
///**
// * Created by haoyu on 2018/5/26.
// */
//@RestController
//public class LogRestController {
//
//    @Autowired
//    private LoginLogServer loginLogServer;
//
//    @GetMapping(value = "/api/test")
//    public String test(){
//        LoginLog log = new LoginLog();
//        log.setHid(HID.generate());
//        log.setMemberHid("1feLHON0SP2L");
//        log.setLoginTime(new Date());
//        loginLogServer.save(log);
//        return "success";
//    }
//
//
//
//}
