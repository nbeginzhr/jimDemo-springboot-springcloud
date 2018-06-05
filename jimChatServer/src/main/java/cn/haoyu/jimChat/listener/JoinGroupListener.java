package cn.haoyu.jimChat.listener;

import org.jim.common.ImPacket;
import org.jim.common.ImSessionContext;
import org.jim.common.packets.Command;
import org.jim.common.packets.Group;
import org.jim.common.packets.User;
import org.jim.common.utils.JsonKit;
import org.jim.server.command.handler.JoinGroupReqHandler;
import org.jim.server.listener.ImServerAioListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tio.core.ChannelContext;
import org.tio.core.intf.Packet;

/**
 * Created by haoyu on 2018/5/26.
 */
public class JoinGroupListener extends ImServerAioListener {

    private final Logger logger = LoggerFactory.getLogger(JoinGroupListener.class);

    @Override
    public void onAfterSent(ChannelContext channelContext, Packet packet, boolean isSentSuccess) {
        ImPacket imPacket = (ImPacket)packet;
        if(imPacket.getCommand() == Command.COMMAND_LOGIN_RESP || imPacket.getCommand() == Command.COMMAND_HANDSHAKE_RESP){//握手 登陆
            ImSessionContext imSessionContext = (ImSessionContext)channelContext.getAttribute();
            User user = imSessionContext.getClient().getUser();
            if(user.getGroups() != null){
                for(Group group : user.getGroups()){//绑定群组并发送加入群组通知
                    ImPacket groupPacket = new ImPacket(Command.COMMAND_JOIN_GROUP_REQ, JsonKit.toJsonBytes(group));
                    try {
                        new JoinGroupReqHandler().handler(groupPacket, channelContext);
                    } catch (Exception e) {
                        logger.error(e.toString(),e);
                    }
                }
            }
        }
    }
}
