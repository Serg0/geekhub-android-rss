package com.example.geekhub.geekhub_android_rss.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import com.actionbarsherlock.view.Window;
import com.example.geekhub.geekhub_android_rss.BaseActivity;
import com.example.geekhub.geekhub_android_rss.R;
import com.example.geekhub.geekhub_android_rss.fragments.DetailsFragment;
import com.example.geekhub.geekhub_android_rss.util.Intents;

public class DetailsActivity extends BaseActivity {

    private String title;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        setContentView(R.layout.details_activity);

        setSupportProgressBarIndeterminateVisibility(false);

        if (savedInstanceState == null) {
            handleIntentExtras(getIntent());
        } else {
            title = savedInstanceState.getString(Intents.EXTRA_TITLE);
            getSupportActionBar().setTitle(title);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(Intents.EXTRA_TITLE, title);
    }

    private void handleIntentExtras(Intent intent) {
        if (intent.hasExtra(Intents.EXTRA_TITLE)) {
            title = intent.getStringExtra(Intents.EXTRA_TITLE);
            getSupportActionBar().setTitle(title);
        }

        DetailsFragment fragment = new DetailsFragment();
        fragment.setArguments(intent.getExtras());
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.details_frag, fragment).commit();
    }
}
