package cn.haoyu.jclient.restController;

import cn.haoyu.jclient.handler.HaoClientAioHandler;
import org.jim.common.packets.ChatBody;
import org.jim.common.packets.Command;
import org.jim.common.packets.LoginReqBody;
import org.jim.common.tcp.TcpPacket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.tio.client.AioClient;
import org.tio.client.ClientChannelContext;
import org.tio.client.ClientGroupContext;
import org.tio.client.ReconnConf;
import org.tio.core.Aio;
import org.tio.core.Node;

/**
 * Created by haoyu on 2018/6/2.
 */
@RestController
public class JavaChatRestController {

    private final Logger logger = LoggerFactory.getLogger(JavaChatRestController.class);

    @GetMapping(value = "/api/send")
    public ResponseEntity<String> send(@RequestParam Integer count) throws Exception {
        ClientGroupContext clientGroupContext = null;
        AioClient aioClient = null;
        ClientChannelContext clientChannelContext = null;
        HaoClientAioHandler haoClientAioHandler = new HaoClientAioHandler();
        ReconnConf reconnConf = new ReconnConf(5000L);

        Node serverNode = new Node("127.0.0.1", 9090);
        clientGroupContext = new ClientGroupContext(haoClientAioHandler, null, reconnConf);
        aioClient = new AioClient(clientGroupContext);
        clientChannelContext = aioClient.connect(serverNode);
        byte[] loginBody = new LoginReqBody("haoyu", "123456").toByte();
        TcpPacket loginPacket = new TcpPacket(Command.COMMAND_LOGIN_REQ, loginBody);
        Aio.send(clientChannelContext, loginPacket);
        ChatBody chatBody;
        TcpPacket chatPacket;

        chatBody = new ChatBody()
                .setFrom("1feLHON0SP2L")
                .setTo("ofzL8Op0Sm2w")
                .setMsgType(0)
                .setChatType(1)
                .setGroup_id("testGroupId")
                .setContent("好雨知时节" );
        chatPacket = new TcpPacket(Command.COMMAND_CHAT_REQ, chatBody.toByte());
        Aio.send(clientChannelContext, chatPacket);
        logger.info("actor____count--->{}", chatBody.getGroup_id());

        return ResponseEntity.ok().body("send success!");
    }
}
