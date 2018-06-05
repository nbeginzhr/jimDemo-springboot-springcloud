package cn.haoyu.jimChat.processor;

import cn.haoyu.common.entity.GroupChatRecord;
import cn.haoyu.common.entity.Haccount;
import cn.haoyu.common.entity.Member;
import cn.haoyu.jimChat.commonInterface.AccountDao;
import cn.haoyu.jimChat.commonInterface.GroupChatDao;
import cn.haoyu.jimChat.utils.MD5Util;
import cn.haoyu.jimChat.utils.SpringUtils;
import org.jim.common.packets.Group;
import org.jim.common.packets.LoginReqBody;
import org.jim.common.packets.User;
import org.jim.server.command.handler.processor.login.LoginProcessorIntf;
import org.tio.core.ChannelContext;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by haoyu on 2018/5/24.
 */
public class HaoLoginProcess implements LoginProcessorIntf {


    public User getUser(LoginReqBody loginBody, ChannelContext channelContext) {
        AccountDao accountDao = SpringUtils.getBean(AccountDao.class);
        Haccount account = accountDao.findByUserNameAndPwd(loginBody.getLoginname(), MD5Util.encode(loginBody.getPassword()));
        if(null == account) return null;
        GroupChatDao groupChatDao = SpringUtils.getBean(GroupChatDao.class);
        Member member = groupChatDao.getByAccountHid(account.getHid());
        if(null == member) return null;
        return memberToUser(member);
    }

    private User memberToUser(Member member){
        User user = new User();
        user.setId(member.getHid());
        user.setNick(member.getName());
        if(member.isOnline()){
            user.setStatus("online");
        }else{
            user.setStatus("offline");
        }
        List<GroupChatRecord> chatRecords = member.getChatRecords();
        if(null == chatRecords || chatRecords.size() == 0) return user;
        List<Group> groups = new ArrayList<Group>();
        for(GroupChatRecord record: chatRecords){
            Group group = new Group();
            group.setGroup_id(record.getGroupHid());
            group.setName(record.getGroupName());
            groups.add(group);
        }
        user.setGroups(groups);
        return user;
    }

    public boolean isProtocol(ChannelContext channelContext) {
        return true;
    }

    public String name() {
        return "haoyu";
    }
}
