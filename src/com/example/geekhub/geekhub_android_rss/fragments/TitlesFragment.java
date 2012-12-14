package com.example.geekhub.geekhub_android_rss.fragments;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.example.geekhub.geekhub_android_rss.BaseActivity;
import com.example.geekhub.geekhub_android_rss.BaseFragment;
import com.example.geekhub.geekhub_android_rss.R;
import com.example.geekhub.geekhub_android_rss.objects.Article;
import com.example.geekhub.geekhub_android_rss.objects.ArticleCollection;
import com.example.geekhub.geekhub_android_rss.util.ConnectionHelper;
import com.example.geekhub.geekhub_android_rss.util.Intents;
import org.holoeverywhere.LayoutInflater;
import org.holoeverywhere.widget.ListView;
import org.holoeverywhere.widget.TextView;
import org.json.JSONException;

import java.io.IOException;

public class TitlesFragment extends BaseFragment {

    private View view;
    private BaseActivity activity;
    private ArticleCollection articles;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = (BaseActivity) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.titles_fragment, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        loadData();
    }

    private void loadData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    articles = ConnectionHelper.getArticles();

                    updateUi();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void updateUi() {
        final ListView list = (ListView) view.findViewById(R.id.list);

        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ArticlesAdapter adapter = new ArticlesAdapter(articles);

                if (list.getAdapter() == null) {
                    list.setAdapter(adapter);
                } else {
                    adapter.notifyDataSetChanged();
                }
            }
        });

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Article article = (Article) adapterView.getItemAtPosition(position);
                startActivity(Intents.getDetailsIntent(activity, article.getTitle(), article.getContent()));
            }
        });
    }

    private class ArticlesAdapter extends BaseAdapter {

        private ArticleCollection articles;

        private ArticlesAdapter(ArticleCollection articles) {
            this.articles = articles;
        }

        @Override
        public int getCount() {
            return articles.asVector().size();
        }

        @Override
        public Object getItem(int i) {
            return articles.get(i);
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int position, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = LayoutInflater.from(activity).inflate(R.layout.list_item);
            }

            Article article = (Article) getItem(position);

            TextView title = (TextView) view.findViewById(R.id.text);
            title.setText(article.getTitle());

            return view;
        }
    }
}
