package com.example.asus.news.live.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.android.volley.toolbox.ImageLoader;
import com.example.asus.news.App;
import com.example.asus.news.R;
import com.example.asus.news.live.LiveDetailsActivity;
import com.example.asus.news.live.model.HotMainFirstBean;
import com.example.asus.news.util.BitmapCache;

import java.util.List;

/**
 * Created by ASUS on 2016/11/2.
 */

public class VpImgAdapter extends PagerAdapter {
    private List<String> list;
    private final ImageLoader loader;
    private Context context;
    private List<String> titles;
    private List<Integer> tt;
    public VpImgAdapter(List<String> list,List<String> titles, Context context) {
        this.list = list;
        this.context = context;
        this.titles=titles;
        loader = new ImageLoader(((App) ((Activity) context).getApplication()).getRequestQueue(), new BitmapCache(context));

    }
    public VpImgAdapter(List<String> list,List<Integer> tt, Context context,List<String> titles) {
        this.list = list;
        this.context = context;
        this.tt=tt;
        loader = new ImageLoader(((App) ((Activity) context).getApplication()).getRequestQueue(), new BitmapCache(context));

    }
    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        ImageView imageView = new ImageView(context);
        ImageLoader.ImageListener listener=ImageLoader.getImageListener(imageView, R.drawable.cover, R.drawable.cover);
        loader.get(list.get(position%list.size()), listener,imageView.getWidth(),imageView.getHeight());
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    Intent intent = new Intent(context, LiveDetailsActivity.class);
                    Log.d("zanZQ", "onItemClick: " + tt.get(position%tt.size()));
                    intent.putExtra("id", tt.get(position%tt.size())+"");
                    context.startActivity(intent);
            }
        });
        container.addView(imageView);
        return imageView;
    }
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position%list.size());
    }
}
