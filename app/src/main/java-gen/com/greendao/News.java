package com.greendao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table NEWS.
 */
public class News {

    private Long id;
    private String json;

    public News() {
    }

    public News(Long id) {
        this.id = id;
    }

    public News(Long id, String json) {
        this.id = id;
        this.json = json;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

}
