package cn.haoyu.common.entity;

import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.List;

/**
 * Created by haoyu on 2018/5/24.
 */
public class Member implements Serializable {

    private static final long serialVersionUID = -4852914253384836498L;

    private String hid;
    private String accountHid;
    private String name;
    private boolean isOnline = false;
    private List<GroupChatRecord> chatRecords;

    @Id
    public String getHid() {
        return hid;
    }

    public void setHid(String hid) {
        this.hid = hid;
    }

    public String getAccountHid() {
        return accountHid;
    }

    public void setAccountHid(String accountHid) {
        this.accountHid = accountHid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isOnline() {
        return isOnline;
    }

    public void setOnline(boolean online) {
        isOnline = online;
    }

    public List<GroupChatRecord> getChatRecords() {
        return chatRecords;
    }

    public void setChatRecords(List<GroupChatRecord> chatRecords) {
        this.chatRecords = chatRecords;
    }
}
