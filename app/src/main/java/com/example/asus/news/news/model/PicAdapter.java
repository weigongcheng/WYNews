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
 * Created by ASUS on 2016/11/5.
 */

public class PicAdapter extends BaseAdapter {
    private final ImageLoader loader;
    private Context context;
    private LayoutInflater inflater;
    private List<PicBean> list;

    public PicAdapter(Context context, List<PicBean> list) {
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
            view = inflater.inflate(R.layout.fragmeny_news_picitem, viewGroup, false);
            holder=new ViewHolder(view);
            view.setTag(holder);
        }else{
            holder= (ViewHolder) view.getTag();
        }
        PicBean picBean = list.get(i);
        holder.title.setText(picBean.getTitle());
        holder.downTimes.setText(picBean.getDownTimes()+"");
        holder.replyCount.setText(picBean.getReplyCount()+"");
        holder.upTimes.setText(picBean.getUpTimes()+"");
        if (picBean.getImgsrc()!=null&&picBean.getImgsrc().length()>0){
            holder.img.setVisibility(View.VISIBLE);
            ImageLoader.ImageListener listener3 = ImageLoader.getImageListener(holder.img, R.drawable.cover, R.drawable.cover);
            loader.get(picBean.getImgsrc(), listener3, holder.img.getWidth(), holder.img.getHeight());
        }else{
            holder.img.setVisibility(View.GONE);
        }

        return view;
    }

    static class ViewHolder {
        @BindView(R.id.img)
        ImageView img;
        @BindView(R.id.title)
        TextView title;
        @BindView(R.id.upTimes)
        TextView upTimes;
        @BindView(R.id.downTimes)
        TextView downTimes;
        @BindView(R.id.share)
        ImageView share;
        @BindView(R.id.replyCount)
        TextView replyCount;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
