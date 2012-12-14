package com.example.geekhub.geekhub_android_rss.objects;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Vector;

public class ArticleCollection {

    Vector<Article> articles = new Vector<Article>();

    public Vector<Article> asVector() {
        return articles;
    }

    public Article get(int index) {
        return articles.get(index);
    }

    private void add(Article article) {
        articles.addElement(article);
    }

    public static ArticleCollection unloadJson(JSONObject jsonData) throws JSONException{
        ArticleCollection articleCollection = new ArticleCollection();

        JSONObject jsonFeed = jsonData.getJSONObject("feed");
        JSONArray jsonEntry = jsonFeed.getJSONArray("entry");

        for (int i = 0; i < jsonEntry.length(); i++) {
            articleCollection.add(Article.fromJson(jsonEntry.getJSONObject(i)));
        }

        return articleCollection;
    }
}
