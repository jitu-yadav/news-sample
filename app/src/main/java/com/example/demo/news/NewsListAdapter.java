package com.example.demo.news;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.List;

public class NewsListAdapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<News> newsList;
    private ImageLoader imageLoader = AppController.getInstance().getImageLoader();

    NewsListAdapter(Activity activity, List<News> newsList) {
        this.activity = activity;
        this.newsList = newsList;
    }

    @Override
    public int getCount() {
        return newsList.size();
    }

    @Override
    public Object getItem(int location) {
        return newsList.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            assert inflater != null;
            convertView = inflater.inflate(R.layout.list_item, null);
        }

        if (imageLoader == null)
            imageLoader = AppController.getInstance().getImageLoader();
        NetworkImageView thumbsNail = (NetworkImageView) convertView
                .findViewById(R.id.thumbnail);
        TextView newsTitle = (TextView) convertView.findViewById(R.id.title);
        TextView newsDetails = (TextView) convertView.findViewById(R.id.newsDetails);

        News m = newsList.get(position);

        thumbsNail.setImageUrl(m.getThumbnailUrl(), imageLoader);

        newsTitle.setText(m.getNewsTitle());

        newsDetails.setText(String.valueOf(m.getNewsDetails()));

        convertView.setTag(m.getNewsDetailsUrl());

        return convertView;
    }

}
