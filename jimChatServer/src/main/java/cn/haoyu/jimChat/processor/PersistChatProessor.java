package cn.haoyu.jimChat.processor;

import cn.haoyu.jimChat.commonInterface.GroupChatDao;
import cn.haoyu.jimChat.utils.SpringUtils;
import org.jim.common.packets.ChatBody;
import org.jim.server.command.handler.processor.chat.AbstractChatProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tio.core.ChannelContext;

/**
 * Created by haoyu on 2018/5/25.
 */
public class PersistChatProessor extends AbstractChatProcessor {
    private final Logger logger = LoggerFactory.getLogger(PersistChatProessor.class);


    @Override
    public void doHandler(ChatBody chatBody, ChannelContext channelContext) {
        GroupChatDao groupChatDao = SpringUtils.getBean(GroupChatDao.class);
        String result = groupChatDao.saveTextContent(chatBody.getGroup_id(), chatBody.getContent(), chatBody.getFrom());
    }


}
