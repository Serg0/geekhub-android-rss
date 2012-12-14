package com.example.geekhub.geekhub_android_rss.objects;

import org.json.JSONObject;

public class Article {

    private String title;
    private String content;

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

    public static Article fromJson(JSONObject jsonData) {
        Article article = new Article();
        article.setTitle(jsonData.optJSONObject("title").optString("$t"));
        article.setContent(jsonData.optJSONObject("content").optString("$t"));

        return article;
    }
}
