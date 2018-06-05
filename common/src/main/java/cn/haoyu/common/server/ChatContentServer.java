package cn.haoyu.common.server;

import cn.haoyu.common.entity.ChatContent;
import cn.haoyu.common.enums.ChatMsgType;
import cn.haoyu.common.repository.ChatContentReposiroty;
import cn.haoyu.common.utils.HID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by haoyu on 2018/5/24.
 */
@Component("chatContentServer")
public class ChatContentServer {
    private Logger logger = LoggerFactory.getLogger(ChatContentServer.class);

    @Autowired
    private ChatContentReposiroty chatContentReposiroty;


    public ChatContent save(ChatContent content){
        return chatContentReposiroty.save(content);
    }

    public ChatContent saveTextContent(String groupHid, String message, String fromHid) {
        logger.info("保存聊天记录,线程：{}，fromHid:{},groupHid:{},content:{}",Thread.currentThread().getName(),fromHid,groupHid,message);
        ChatContent content = new ChatContent();
        content.setHid(HID.generate());
        content.setGroupHid(groupHid);
        content.setFromMember(fromHid);
        content.setMessage(message);
        content.setMsgType(ChatMsgType.TEXT.name());
        content.setSendTime(new Date());
        return save(content);
    }
}
