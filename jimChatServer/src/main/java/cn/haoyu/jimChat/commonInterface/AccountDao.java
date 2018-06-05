package cn.haoyu.jimChat.commonInterface;

import cn.haoyu.common.entity.Haccount;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by haoyu on 2018/5/26.
 */
@Component("accountDao")
@FeignClient(value = "common")
@RequestMapping("/common")
public interface AccountDao {

    @GetMapping(value = "/haccount/findAccount")
    Haccount findByUserNameAndPwd(@RequestParam(value = "username") String username, @RequestParam(value = "encodedPwd") String encodedPwd);
}
