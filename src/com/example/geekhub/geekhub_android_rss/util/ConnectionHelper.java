package com.example.geekhub.geekhub_android_rss.util;

import android.util.Log;
import com.example.geekhub.geekhub_android_rss.objects.ArticleCollection;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ConnectionHelper {

    private static final String URL = "http://android-developers.blogspot.com/feeds/posts/default?alt=json";

    public static ArticleCollection getArticles() throws IOException, JSONException {
        String responce = makeGetRequest();
        JSONObject jsonObject = new JSONObject(responce);
        return ArticleCollection.unloadJson(jsonObject);
    }

    private static String makeGetRequest() throws IOException {
        StringBuilder builder = new StringBuilder();
        HttpClient client = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(URL);

        HttpResponse response = client.execute(httpGet);
        StatusLine statusLine = response.getStatusLine();
        int statusCode = statusLine.getStatusCode();

        if (statusCode == 200) {
            HttpEntity entity = response.getEntity();
            InputStream content = entity.getContent();
            BufferedReader reader = new BufferedReader(new InputStreamReader(content));
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }
        }

        return builder.toString();
    }
}
