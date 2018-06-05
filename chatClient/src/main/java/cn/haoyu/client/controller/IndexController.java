package cn.haoyu.client.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by haoyu on 2018/5/23.
 */
@Controller
public class IndexController {

    @GetMapping(value = "/index")
    public String index(){
        return "index";
    }
}
