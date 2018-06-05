package cn.haoyu.jclient.sendServer;

import akka.actor.AbstractLoggingActor;
import cn.haoyu.jclient.handler.HaoClientAioHandler;
import org.jim.common.packets.ChatBody;
import org.jim.common.packets.Command;
import org.jim.common.packets.LoginReqBody;
import org.jim.common.tcp.TcpPacket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tio.client.AioClient;
import org.tio.client.ClientChannelContext;
import org.tio.client.ClientGroupContext;
import org.tio.client.ReconnConf;
import org.tio.core.Aio;
import org.tio.core.Node;

/**
 * Created by haoyu on 2018/6/2.
 */
public class SendActor extends AbstractLoggingActor {

    private final Logger logger = LoggerFactory.getLogger(SendActor.class);

    private String groupsStr = "iIJNv430ToSY, YIiNi4T0ToSN, MINNu4y0TCSW, 4ITNb4Y0TfSv, xITNN4W0TJSn, TISNg4F0TjSK, nI8NW4c0TTSK, dIxN9470TsSV, " +
            "SIEN14k0TASm, qIGNt4S0TPSW, PIdNt4u0TPS4, 6IgNZ460TRSA, nIxNq460TCSB, xIZNT450TTSP, JIpNA4P0T7Sj, kItNu470T7Sw, IIKNQ4M0TiS5, " +
            "FINN4400TSSs, bI4Nc4S0TnSZ, eIUN04Z0TWSg, xIQNv4C0TwSd, hIJNE4h0TxSV, TIWNO4Z0TcSh, AIUNn4x0TtS5, 7I3N5400TfS3, uIaNl4q0THSq, " +
            "gIGNL4o0TyS6, 4IjNS4A0TGSZ, bIrN94A0TFSn, EI1NJ4x0ThS5, TIXNG4v0TPS2, 7IXNO4g0TLS5, UIcN0490TGS0, vI8NI4A0TRSV, NISNE4V0ToST, " +
            "UIBNz490TxS3, zIUNi4F0TxSm, LIqNu4u0TlSb, RIFNj470TWSs, KIQNh4m0T2SP, LISNS4i0TVSu, fIxNb4d0ThSY, eIfNo4q0TpS7, AIdN7480TUSs, " +
            "IIWNg4f0ToSg, iIQNf4H0TxSl, iIlNS4R0TcSf, BIZNr4W0TYSw, TIrN14m0TvSX, nI7Nj4B0TgSB, 2IhNe410T2SH, CIYNe4w0TbSJ, QIwNE4q0TKS5, " +
            "4IoN04u0TMSp, OIWNb4U0TWSw, 3IfN4470TQSU, TIiN34z0TwSy, kIMNE4D0TaSe, HISNS4l0TTSu, 1IcNM4E0TCS1, NIhN04S0TqSy, PIwN64Z0TTSa," +
            " QIiN04j0TrSZ, 9IgN6460TQSR, PIGNx4L0T9Ss, mIwNf4C0TlSI, wImNn4T0T1SN, rIPNW410TDSx, zI0Nf4L0TlS1, EIJN14i0TdSE, KIINh4E0TBSy, " +
            "wIeNp4o0TXSQ, yIENa4V0TxSp, aIGNs4k0TrSQ, WIENi4E0T3SB, AIsNe4J0T7SG, MINNH4n0TjSM, LICNd4X0TSSz, EIKN14G0TWS0, OICNC400TJSp," +
            " tIgNN470TDSW, IIXNs4c0TMSd, FIaNB4Y0TgS5, CIeNY4W0TOSe, hItNt4S0T6S8, rIPNm440TuS5, oIHNX400TPSP, 6I9Nn4I0T3Sh, 5IMNi4G0T9Sa," +
            " 4IYNg400T4Sy, YIlNM430TWSG, OI8N44q0T5SI, QIWND4W0TaSI, oIvNY4J0TsSa, DI3Nc4b0TYS0, xIlNd4w0TbSQ, kIPNq4x0TRSb, KI4NY4c0TCSX," +
            " pI4Ny4a0TYSQ, 0fNL5OP0S92B";


    public static class SendRequest {
        private Integer count;
        public SendRequest(Integer count) {
            this.count = count;
        }
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder().match(SendRequest.class, s -> {
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
            String[] groupHids = groupsStr.split(", ");
            ChatBody chatBody ;
            TcpPacket chatPacket;
            for(int i = 0; i<100 ;i++){
                chatBody = new ChatBody()
                        .setFrom("1feLHON0SP2L")
                        .setTo("ofzL8Op0Sm2w")
                        .setMsgType(0)
                        .setChatType(1)
                        .setGroup_id(groupHids[i])
                        .setContent("好雨知时节,actorNu--->" + s.count + ", i----->" + i);
                chatPacket = new TcpPacket(Command.COMMAND_CHAT_REQ, chatBody.toByte());
                Aio.send(clientChannelContext, chatPacket);
                logger.info("actor____count--->{},{}", s.count,chatBody.getGroup_id());
            }
            aioClient.stop();
        }).matchAny(o -> {
            logger.error("****************actor 消息格式错误******************");
        }).build();
    }
}
