package com.example.asus.news.news.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.example.asus.news.App;
import com.example.asus.news.DetailActivity;
import com.example.asus.news.R;
import com.example.asus.news.news.model.ADS;
import com.example.asus.news.util.BitmapCache;

import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by ASUS on 2016/10/31.
 */

public class VpAdapter extends PagerAdapter {
    private final ImageLoader loader;
    private List<ADS> list;
    private Context context;

    public VpAdapter(List<ADS> list, Context context) {
        this.list = list;
        this.context = context;
        loader = new ImageLoader(((App) ((Activity) context).getApplication()).getRequestQueue(), new BitmapCache(context));
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        ADS ads = list.get(position%list.size());
        String imgsrc = ads.getImgsrc();
        ImageView iv = new ImageView(context);
        ImageLoader.ImageListener listener=ImageLoader.getImageListener(iv, R.drawable.cover, R.drawable.cover);
        loader.get(imgsrc, listener,iv.getWidth(),iv.getHeight());
        iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
        container.addView(iv);
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("flag",0);
                intent.putExtra("url",list.get(position%list.size()).getUrl());
                context.startActivity(intent);
            }
        });
        return iv;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
