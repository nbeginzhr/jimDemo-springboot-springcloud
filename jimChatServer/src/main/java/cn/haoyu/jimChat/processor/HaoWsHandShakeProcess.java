package cn.haoyu.jimChat.processor;

import org.jim.common.ImPacket;
import org.jim.common.http.HttpConst;
import org.jim.common.http.HttpRequest;
import org.jim.common.packets.Command;
import org.jim.common.packets.LoginReqBody;
import org.jim.common.utils.JsonKit;
import org.jim.common.ws.WsRequestPacket;
import org.jim.common.ws.WsResponsePacket;
import org.jim.common.ws.WsSessionContext;
import org.jim.server.command.CommandManager;
import org.jim.server.command.handler.LoginReqHandler;
import org.jim.server.command.handler.processor.handshake.WsHandshakeProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tio.core.ChannelContext;

/**
 * Created by haoyu on 2018/5/24.
 */
public class HaoWsHandShakeProcess extends WsHandshakeProcessor {

    private final Logger logger = LoggerFactory.getLogger(HaoWsHandShakeProcess.class);

    @Override
    public ImPacket handshake(ImPacket packet, ChannelContext channelContext) throws Exception {
        WsRequestPacket wsRequestPacket = (WsRequestPacket) packet;
        WsSessionContext wsSessionContext = (WsSessionContext) channelContext.getAttribute();
        // 握手响应
        if (wsRequestPacket.isHandShake()) {
            LoginReqHandler loginHandler = (LoginReqHandler) CommandManager.getCommand(Command.COMMAND_LOGIN_REQ);
            HttpRequest request = wsSessionContext.getHandshakeRequestPacket();
            String username = request.getParams().get("username") == null ? null : (String)request.getParams().get("username")[0];
            String password = request.getParams().get("password") == null ? null : (String)request.getParams().get("password")[0];
            String token = request.getParams().get("token") == null ? null : (String)request.getParams().get("token")[0];
            LoginReqBody loginBody = new LoginReqBody(username,password,token);
            byte[] loginBytes = JsonKit.toJsonBytes(loginBody);
            request.setBody(loginBytes);
            request.setBodyString(new String(loginBytes, HttpConst.CHARSET_NAME));
            Object loginResponse = loginHandler.handler(request, channelContext);
            if(loginResponse == null)
                return null;

            wsSessionContext.setHandshaked(true);
            WsResponsePacket wsResponsePacket = new WsResponsePacket();
            wsResponsePacket.setHandShake(true);
            wsResponsePacket.setCommand(Command.COMMAND_HANDSHAKE_RESP);
            return wsResponsePacket;
        }
        return null;
    }

}
