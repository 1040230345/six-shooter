package com.dto;

/**
 * 存放cookies
 * 时间：2019年8月16日14:57:14
 * 作者：杰松
 */
public class CookieDto {

    private int user_id; //用户id
    private String cookie;  //cookie 由uuid产生，为唯一
    private String created_at; //创建时间
    private String updated_at; //修改时间

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getCookie() {
        return cookie;
    }

    public void setCookie(String cookie) {
        this.cookie = cookie;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }
}
