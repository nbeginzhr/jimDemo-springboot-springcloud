package cn.haoyu.common.restController;

import cn.haoyu.common.entity.ChatContent;
import cn.haoyu.common.entity.Group;
import cn.haoyu.common.entity.Member;
import cn.haoyu.common.server.ChatContentServer;
import cn.haoyu.common.server.GroupServer;
import cn.haoyu.common.server.MemberServer;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by haoyu on 2018/5/25.
 */
@RestController
public class RegisRestController {
    private final Logger logger = LoggerFactory.getLogger(RegisRestController.class);

    private final Gson gson = new Gson();

    @Autowired
    private GroupServer groupServer;
    @Autowired
    private MemberServer memberServer;
    @Autowired
    private ChatContentServer chatContentServer;


    @PostMapping(value = "/group/createGroup")
    public String createGroup(@RequestParam String name,@RequestParam List<String> members) {
        Group group = groupServer.createGroup(name, members);
        return gson.toJson(group);
    }

    @PostMapping(value = "/chat/textContent")
    public String textContent(@RequestParam String groupHid, @RequestParam String message, @RequestParam String fromHid) {
        ChatContent content = chatContentServer.saveTextContent(groupHid, message, fromHid);
        return gson.toJson(content);
    }

    // 根据账号hid 查询个人群聊信息
    @GetMapping(value = "/member/getByAccountHid")
    public Member getByAccountHid(@RequestParam String accountHid){
        return memberServer.findByAccountHid(accountHid);
    }

    // 登陆设置在线
    @GetMapping(value = "/member/online")
    public Boolean online(@RequestParam  Boolean online,@RequestParam String hid){
        return memberServer.changeOnline(online,hid);
    }

    @PostMapping(value = "/group/createGroups")
    public String createGroups() {
        ArrayList<String> members = new ArrayList<String>();
        members.add("1feLHON0SP2L");
        members.add("ofzL8Op0Sm2w");
        members.add("ff7LUO50Sr2J");
        List<Group> groups = groupServer.createGroups( members);
        return gson.toJson(groups);
    }



}