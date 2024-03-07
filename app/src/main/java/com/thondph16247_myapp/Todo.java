package com.thondph16247_myapp;

import java.util.HashMap;

public class Todo {
    private String id,title,content;

    public Todo(String id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public Todo() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    //ham xu ly du lieu trong firebase
    public HashMap<String,Object> convertHashMap()
    {
        HashMap<String,Object> work=new HashMap<>();
        work.put("id",id);
        work.put("title",title);
        work.put("content",content);
        return work;
    }
}
