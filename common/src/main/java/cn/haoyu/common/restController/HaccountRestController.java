package cn.haoyu.common.restController;

import cn.haoyu.common.entity.Haccount;
import cn.haoyu.common.server.HaccountServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by haoyu on 2018/5/26.
 */
@RestController
public class HaccountRestController {

    private Logger logger = LoggerFactory.getLogger(HaccountRestController.class);

    @Autowired
    private HaccountServer haccountServer;

    @GetMapping(value = "/haccount/findAccount")
    public Haccount findByUserNameAndPwd(@RequestParam String username,@RequestParam String encodedPwd){
        return haccountServer.findByUserNameAndPwd(username,encodedPwd);
    }

}
