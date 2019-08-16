package com.dto;

import java.util.Date;

/**
 * 存放cookies
 * 时间：2019年8月16日14:57:14
 * 作者：杰松
 */
public class CookieDto {

    private long user_id; //用户id
    private String cookie;  //cookie 由uuid产生，为唯一
    private Date created_at; //创建时间
    private Date updated_at; //修改时间

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public String getCookie() {
        return cookie;
    }

    public void setCookie(String cookie) {
        this.cookie = cookie;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }
}
