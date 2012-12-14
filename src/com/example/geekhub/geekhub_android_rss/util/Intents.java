package com.example.geekhub.geekhub_android_rss.util;

import android.content.Context;
import android.content.Intent;
import com.example.geekhub.geekhub_android_rss.activities.DetailsActivity;

public class Intents {

    public static final String EXTRA_TITLE = "com.example.geekhub.geekhub_android_rss.EXTRA_TITLE";
    public static final String EXTRA_CONTENT = "com.example.geekhub.geekhub_android_rss.EXTRA_CONTENT";

    public static Intent getDetailsIntent(Context context, String title, String content) {
        Intent intent = new Intent(context, DetailsActivity.class);
        intent.putExtra(EXTRA_TITLE, title);
        intent.putExtra(EXTRA_CONTENT, content);
        return intent;
    }
}
