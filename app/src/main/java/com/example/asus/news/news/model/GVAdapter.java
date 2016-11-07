package com.example.asus.news.news.model;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.example.asus.news.App;
import com.example.asus.news.R;
import com.example.asus.news.util.BitmapCache;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ASUS on 2016/11/6.
 */

public class GVAdapter extends BaseAdapter {
    private final ImageLoader loader;
    private Context context;
    private LayoutInflater inflater;
    private List<SpBean.视频Bean> list;

    public GVAdapter(Context context, List<SpBean.视频Bean> list) {
        this.context = context;
        this.list = list;
        inflater = LayoutInflater.from(context);
        loader = new ImageLoader(((App) ((Activity) context).getApplication()).getRequestQueue(), new BitmapCache(context));

    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null) {
            view = inflater.inflate(R.layout.gv_item, viewGroup, false);
            holder=new ViewHolder(view);
            view.setTag(holder);
        }else{
            holder= (ViewHolder) view.getTag();
        }
        SpBean.视频Bean bean = list.get(i);
        holder.name.setText(bean.getTopicName());
        holder.title.setText(bean.getTopicDesc());
        ImageLoader.ImageListener listener3 = ImageLoader.getImageListener(holder.img, R.drawable.cover, R.drawable.cover);
        loader.get(bean.getCover(), listener3, holder.img.getWidth(), holder.img.getHeight());
        ImageLoader.ImageListener listener = ImageLoader.getImageListener(holder.face, R.drawable.cover, R.drawable.cover);
        loader.get(bean.getTopicImg(), listener, holder.face.getWidth(), holder.face.getHeight());

        return view;
    }

    static class ViewHolder {
        @BindView(R.id.img)
        ImageView img;
        @BindView(R.id.title)
        TextView title;
        @BindView(R.id.face)
        ImageView face;
        @BindView(R.id.name)
        TextView name;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
