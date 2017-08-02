package com.yasin.nissen.os_scout.Model;
import java.text.SimpleDateFormat;

/**
 * Created by ynissen on 7/14/17.
 */
public class GitResponse {

    private Integer id;
    private String name;
    private String language;
    private Integer forks_count;
    private Integer open_issues;
    private Integer watchers;
    private String created_at;
    private String updated_at;
    private String html_url;
    public ProjectOwner owner;


    public GitResponse(){

    }

    public ProjectOwner getOwner() {
        return owner;
    }

    public void setOwner(ProjectOwner owner) {
        this.owner = owner;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Integer getForks_count() {
        return forks_count;
    }

    public void setForks_count(Integer forks_count) {
        this.forks_count = forks_count;
    }

    public Integer getOpen_issues() {
        return open_issues;
    }

    public void setOpen_issues(Integer open_issues) {
        this.open_issues = open_issues;
    }

    public Integer getWatchers() {
        return watchers;
    }

    public void setWatchers(Integer watchers) {
        this.watchers = watchers;
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

    public String getHtml_url() {
        return html_url;
    }

    public void setHtml_url(String html_url) {
        this.html_url = html_url;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "GitResponse{" +
                "id=" + id +" ; "+"name=" + name+" ; "
                +"language=" + language+" ; "
                +"forks_count=" + forks_count+" ; "
                +"open_issues=" + open_issues+" ; "
                +"watchers=" + watchers+" ; "
                +"created_id=" + created_at+" ; "
                +"updated_at=" + updated_at+" ; "

                +"html_url=" + html_url+
                '}';
    }
}

//+"owner=" + owner.getLogin()+" ; "