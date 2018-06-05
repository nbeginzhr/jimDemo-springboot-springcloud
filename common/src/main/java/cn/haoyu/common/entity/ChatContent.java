package cn.haoyu.common.entity;

import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by haoyu on 2018/5/24.
 */
public class ChatContent implements Serializable {
    private static final long serialVersionUID = 5203094169763469184L;

    private String hid;
    private String groupHid;
    private String message;
    private String msgType; // 消息类型 : TEXT,FILE,VOICE,VIDEO,IMG  枚举：ChatMsgType
    private Date sendTime;
    private String fromMember; // 发送人 Hid

    @Id
    public String getHid() {
        return hid;
    }

    public void setHid(String hid) {
        this.hid = hid;
    }

    public String getGroupHid() {
        return groupHid;
    }

    public void setGroupHid(String groupHid) {
        this.groupHid = groupHid;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public String getFromMember() {
        return fromMember;
    }

    public void setFromMember(String fromMember) {
        this.fromMember = fromMember;
    }
}
