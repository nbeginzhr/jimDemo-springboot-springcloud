package cn.haoyu.common.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by haoyu on 2018/5/1.
 */
@Entity
public class Role implements Serializable {
    private static final long serialVersionUID = 3315181403279825661L;

    private String hid;
    private String haccountHid;
    private String role;
    private String authority;

    public Role() {
    }

    public Role(String hid, String haccountHid, String role, String authority) {
        this.hid = hid;
        this.haccountHid = haccountHid;
        this.role = role;
        this.authority = authority;
    }

    @Id
    @Column(name = "HID")
    public String getHid() {
        return hid;
    }

    public void setHid(String hid) {
        this.hid = hid;
    }

    @Column(name = "HACCOUNTHID")
    public String getHaccountHid() {
        return haccountHid;
    }

    public void setHaccountHid(String haccountHid) {
        this.haccountHid = haccountHid;
    }

    @Column(name = "ROLE")
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    @Column(name = "AUTHORITY")
    public String getAuthority() {
        return this.authority;
    }

}
