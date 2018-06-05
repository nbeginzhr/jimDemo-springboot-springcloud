package cn.haoyu.common.server;

import cn.haoyu.common.entity.Group;
import cn.haoyu.common.enums.GroupStatus;
import cn.haoyu.common.repository.GroupRepository;
import cn.haoyu.common.utils.HID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Created by haoyu on 2018/5/24.
 */
@Component("groupServer")
public class GroupServer {

    @Autowired
    private GroupRepository groupRepository;

    public Group save(Group group){
        return groupRepository.save(group);
    }

    public Group createGroup(String name, List<String> members) {
        Group group = new Group();
        group.setHid(HID.generate());
        group.setName(name);
        group.setCreateTime(new Date());
        group.setUseFor("qunliao");
        group.setStatus(GroupStatus.ACTIVE.name());
        group.setMemberList(members);
        return save(group);
    }

    public List<Group> createGroups(List<String> members) {
        List<String> groupHids = new ArrayList<String>();
        List<Group> result = new ArrayList<Group>();
        Random random = new Random();
        Group group ;
        for(int i =0 ;i<99 ;i++){
            group = createGroup(random.nextInt(6) + "", members);
            result.add(group);
            groupHids.add(group.getHid());
        }
        System.out.println(groupHids.toString());
        return result;
    }
}
