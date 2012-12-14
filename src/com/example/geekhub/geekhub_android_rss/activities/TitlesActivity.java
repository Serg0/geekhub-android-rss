package com.example.geekhub.geekhub_android_rss.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.Window;
import com.example.geekhub.geekhub_android_rss.BaseActivity;
import com.example.geekhub.geekhub_android_rss.R;
import com.example.geekhub.geekhub_android_rss.fragments.TitlesFragment;

public class TitlesActivity extends BaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.titles_activity);

        getSupportActionBar().setTitle("Latest news");

        if (savedInstanceState == null) {
            handleIntentExtras(getIntent());
        }
    }

    private void handleIntentExtras(Intent intent) {
        TitlesFragment fragment = new TitlesFragment();
        fragment.setArguments(intent.getExtras());
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.titles_frag, fragment).commit();
    }
}
