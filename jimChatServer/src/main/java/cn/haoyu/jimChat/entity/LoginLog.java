package cn.haoyu.jimChat.entity;

import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by haoyu on 2018/5/26.
 */
public class LoginLog implements Serializable{

    private static final long serialVersionUID = -7689725345482338338L;
    private String hid;
    private String memberHid;
    private Date loginTime;

    @Id
    public String getHid() {
        return hid;
    }

    public void setHid(String hid) {
        this.hid = hid;
    }

    public String getMemberHid() {
        return memberHid;
    }

    public void setMemberHid(String memberHid) {
        this.memberHid = memberHid;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }
}
