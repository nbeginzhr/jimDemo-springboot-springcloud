package cn.haoyu.jclient.sendServer;

import akka.actor.AbstractLoggingActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by haoyu on 2018/6/2.
 */
public class ForwardActor extends AbstractLoggingActor {

    private final Logger logger = LoggerFactory.getLogger(ForwardActor.class);

    public static class ForwardRequest{
        private Integer count;

        public ForwardRequest(Integer count){
            this.count = count;
        }
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder().match(ForwardRequest.class, s -> {
            for(int i =0;i<s.count;i++){
                getContext().actorOf(Props.create(SendActor.class),"sendActor__" + i).tell(new SendActor.SendRequest(i), ActorRef.noSender());
            }
        }).matchAny(o -> {
            logger.error("***********forwardActor class cass fail ! ***************");
        }).build();
    }
}
