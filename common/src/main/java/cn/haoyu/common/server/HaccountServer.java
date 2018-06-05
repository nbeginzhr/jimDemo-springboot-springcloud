package cn.haoyu.common.server;

import cn.haoyu.common.entity.Haccount;
import cn.haoyu.common.repository.HaccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by haoyu on 2018/5/26.
 */
@Component("haccountServer")
public class HaccountServer {

    @Autowired
    private HaccountRepository haccountRepository;

    public Haccount findByUserNameAndPwd(String username,String encodedPwd){
        return haccountRepository.findByUsernameAndPassword(username,encodedPwd);
    }

}
