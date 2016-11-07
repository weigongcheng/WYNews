package com.example.asus.news.live.view;

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
import com.example.asus.news.live.model.FLMainBean;
import com.example.asus.news.live.model.LiveReviewBean;
import com.example.asus.news.util.BitmapCache;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ASUS on 2016/11/2.
 */

public class FlMainAdapter extends BaseAdapter {
    private final ImageLoader loader;
    private Context context;
    private LayoutInflater inflater;

    private List<LiveReviewBean> list;
    public FlMainAdapter(Context context,List<LiveReviewBean> list) {
        this.context = context;
        this.list=list;
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
            view = inflater.inflate(R.layout.fragment_live_fl_item, viewGroup, false);
            holder=new ViewHolder(view);
            view.setTag(holder);
        }else
        {
            holder= (ViewHolder) view.getTag();
        }
       LiveReviewBean data = list.get(i);
        holder.count.setText(data.getUserCount()+"参与");
        holder.title.setText(data.getRoomName());
        if (data.getLiveStatus()==0){
            holder.ivType.setImageResource(R.drawable.night_a5v);
        }else if (data.getLiveStatus()==1)
        {
            holder.ivType.setImageResource(R.drawable.night_a5y);
        }else{
            holder.ivType.setImageResource(R.drawable.night_a61);
        }
        ImageLoader.ImageListener listener3=ImageLoader.getImageListener(holder.cover, R.drawable.cover, R.drawable.cover);
        loader.get(data.getImage(), listener3,holder.cover.getWidth(),holder.cover.getHeight());
        return view;
    }

    static class ViewHolder {
        @BindView(R.id.cover)
        ImageView cover;
        @BindView(R.id.iv_type)
        ImageView ivType;
        @BindView(R.id.count)
        TextView count;
        @BindView(R.id.title)
        TextView title;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
