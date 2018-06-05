package cn.haoyu.common.entity;

import java.io.Serializable;

/**
 * Created by haoyu on 2018/5/24.
 */
public class GroupChatRecord implements Serializable {

    private static final long serialVersionUID = -6201858147775687436L;

    private String groupHid;
    private String groupName;
    private Long msgOffset = 0L;

    public String getGroupHid() {
        return groupHid;
    }

    public void setGroupHid(String groupHid) {
        this.groupHid = groupHid;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Long getMsgOffset() {
        return msgOffset;
    }

    public void setMsgOffset(Long msgOffset) {
        this.msgOffset = msgOffset;
    }
}
