package com.dto;

/**
 * 项目成员实体
 */
public class Repositories_membersDto {
    private int rep_id; //项目id
    private int user_id; //用户id
    private String rep_role; //项目职位
    private String created_at; //创建时间
    private String updated_at; //修改时间

    public int getRep_id() {
        return rep_id;
    }

    public void setRep_id(int rep_id) {
        this.rep_id = rep_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getRep_role() {
        return rep_role;
    }

    public void setRep_role(String rep_role) {
        this.rep_role = rep_role;
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
