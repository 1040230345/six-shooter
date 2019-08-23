package com.dto;

/**
 * 仓库实体类
 */
public class RepositoriesDto {
    private int rep_id; //仓库id
    private String rep_name; //仓库名称
    private String rep_creator; //仓库创建者
    private String rep_thumb; //仓库图标
    private String rep_bio; //仓库简介
    private String githuburl; //github仓库地址
    private String isopen; //是否开放
    private String created_at; //创建时间
    private String updated_at; //修改时间

    public int getRep_id() {
        return rep_id;
    }

    public void setRep_id(int rep_id) {
        this.rep_id = rep_id;
    }

    public String getRep_name() {
        return rep_name;
    }

    public void setRep_name(String rep_name) {
        this.rep_name = rep_name;
    }

    public String getRep_creator() {
        return rep_creator;
    }

    public void setRep_creator(String rep_creator) {
        this.rep_creator = rep_creator;
    }

    public String getRep_thumb() {
        return rep_thumb;
    }

    public void setRep_thumb(String rep_thumb) {
        this.rep_thumb = rep_thumb;
    }

    public String getRep_bio() {
        return rep_bio;
    }

    public void setRep_bio(String rep_bio) {
        this.rep_bio = rep_bio;
    }

    public String getGithuburl() {
        return githuburl;
    }

    public void setGithuburl(String githuburl) {
        this.githuburl = githuburl;
    }

    public String getIsopen() {
        return isopen;
    }

    public void setIsopen(String isopen) {
        this.isopen = isopen;
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
