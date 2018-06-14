package cn.haoyu.common.restController;

import cn.haoyu.common.entity.Group;
import cn.haoyu.common.entity.GroupChatRecord;
import cn.haoyu.common.entity.Member;
import cn.haoyu.common.enums.GroupStatus;
import cn.haoyu.common.server.ChatContentServer;
import cn.haoyu.common.server.GroupServer;
import cn.haoyu.common.server.MemberServer;
import cn.haoyu.common.utils.HID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by haoyu on 2018/5/24.
 */
@RestController
public class ComRestController {

    @Autowired
    private GroupServer groupServer;
    @Autowired
    private MemberServer memberServer;
    @Autowired
    private ChatContentServer chatContentServer;

    @GetMapping(value = "/api/test")
    public ResponseEntity<String> test(){
        List<String> groupMember = new ArrayList<String>();
        for(int i=0;i <3;i++) {
            Member member = new Member();
            member.setHid(HID.create());
            member.setAccountHid(HID.create());
            member.setName("haoyu");
            groupMember.add(member.getHid());
            memberServer.save(member);
        }

        Group group = new Group();
        group.setHid(HID.create());
        group.setName("haoyu");
        group.setCreateTime(new Date());
        group.setUseFor("qunliao");
        group.setStatus(GroupStatus.ACTIVE.name());
        group.setMemberList(groupMember);
        groupServer.save(group);

        Member fetch;
        GroupChatRecord record = new GroupChatRecord();
        record.setGroupHid(group.getHid());
        List<GroupChatRecord> records = new ArrayList<GroupChatRecord>();
        records.add(record);
        for(String memberHid:groupMember){
            fetch = memberServer.fetch(memberHid);
            fetch.setChatRecords(records);
            memberServer.save(fetch);
        }



        return ResponseEntity.ok().body("success");
    }

}
