package com.example.geekhub.geekhub_android_rss.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import com.actionbarsherlock.view.ActionProvider;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import com.actionbarsherlock.widget.ShareActionProvider;
import com.example.geekhub.geekhub_android_rss.BaseActivity;
import com.example.geekhub.geekhub_android_rss.BaseFragment;
import com.example.geekhub.geekhub_android_rss.R;
import com.example.geekhub.geekhub_android_rss.util.Intents;
import org.holoeverywhere.LayoutInflater;
import org.holoeverywhere.widget.Toast;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class DetailsFragment extends BaseFragment {

    private View view;
    private BaseActivity activity;
    private String title;
    private String content;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = (BaseActivity) getActivity();

        Bundle args = getArguments();
        if (args != null) {
            title = args.getString(Intents.EXTRA_TITLE);
            content = args.getString(Intents.EXTRA_CONTENT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.details_fragment, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        updateUi();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.findItem(R.id.menu_like).setTitle("Like");
        MenuItem share = menu.findItem(R.id.menu_share);
        share.setVisible(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_like:
                item.setVisible(false);
                Toast.makeText(activity, "Like pressed", Toast.LENGTH_SHORT).show();
                activity.setSupportProgressBarIndeterminateVisibility(true);
                break;
            case R.id.one:
                Toast.makeText(activity, "Share pressed", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void updateUi() {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {
                    WebView webView = (WebView) view.findViewById(R.id.webView);
                    webView.getSettings().setBuiltInZoomControls(true);
                    webView.loadData(URLEncoder.encode(content, "UTF-8").replaceAll("\\+", "%20"), "text/html", "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
