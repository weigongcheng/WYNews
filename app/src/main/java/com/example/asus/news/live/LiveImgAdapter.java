package com.example.asus.news.live;

import android.app.Activity;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.android.volley.toolbox.ImageLoader;
import com.example.asus.news.App;
import com.example.asus.news.R;
import com.example.asus.news.util.BitmapCache;

import java.util.List;

/**
 * Created by ASUS on 2016/11/6.
 */


    public class LiveImgAdapter extends PagerAdapter {
        private List<String> list;
        private final ImageLoader loader;
        private Context context;
        private List<String> titles;

        public LiveImgAdapter(List<String> list,List<String> titles, Context context) {
            this.list = list;
            this.context = context;
            this.titles=titles;
            loader = new ImageLoader(((App) ((Activity) context).getApplication()).getRequestQueue(), new BitmapCache(context));

        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView imageView = new ImageView(context);
            ImageLoader.ImageListener listener=ImageLoader.getImageListener(imageView, R.drawable.cover, R.drawable.cover);
            loader.get(list.get(position), listener,imageView.getWidth(),imageView.getHeight());
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            container.addView(imageView);
            return imageView;
        }
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles.get(position);
        }
    }
