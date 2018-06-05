package cn.haoyu.common.entity;

import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by haoyu on 2018/5/24.
 */
public class Group implements Serializable {

    private static final long serialVersionUID = 9048768770666652234L;

    private String hid;
    private String name; // 群组名字
    private List<String> memberList; // 群组成员hid
    private Date createTime;
    private String createMan;
    private String status; // 启用关闭 ,枚举 GroupStatus
    private String useFor; //用途说明

    @Id
    public String getHid() {
        return hid;
    }

    public void setHid(String hid) {
        this.hid = hid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getMemberList() {
        return memberList;
    }

    public void setMemberList(List<String> memberList) {
        this.memberList = memberList;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateMan() {
        return createMan;
    }

    public void setCreateMan(String createMan) {
        this.createMan = createMan;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUseFor() {
        return useFor;
    }

    public void setUseFor(String useFor) {
        this.useFor = useFor;
    }
}
