package com.greendao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table STORY.
 */
public class Story {

    private Long id;
    private Integer newsId;
    private String json;

    public Story() {
    }

    public Story(Long id) {
        this.id = id;
    }

    public Story(Long id, Integer newsId, String json) {
        this.id = id;
        this.newsId = newsId;
        this.json = json;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNewsId() {
        return newsId;
    }

    public void setNewsId(Integer newsId) {
        this.newsId = newsId;
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

}
