package cn.haoyu.jimChat.commonInterface;

import cn.haoyu.common.entity.Member;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by haoyu on 2018/5/25.
 */
@Component("groupChatDao")
@FeignClient(value = "common")
@RequestMapping("/common")
public interface GroupChatDao {

    @PostMapping(value = "/group/createGroup")
    String createGroup(@RequestParam(value = "name") String name,
                       @RequestParam(value = "members") List<String> members);

    @PostMapping(value = "/chat/textContent")
    String saveTextContent(@RequestParam(value = "groupHid") String groupHid,
                           @RequestParam(value = "message") String message,
                           @RequestParam(value = "fromHid") String fromHid);

    @GetMapping(value = "/member/getByAccountHid")
    Member getByAccountHid(@RequestParam(value = "accountHid") String accountHid);

    @GetMapping(value = "/member/online")
    Boolean online(@RequestParam(value = "online") Boolean online, @RequestParam(value = "hid") String hid);
}
