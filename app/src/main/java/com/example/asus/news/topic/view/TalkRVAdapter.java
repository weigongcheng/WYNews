package com.example.asus.news.topic.view;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.example.asus.news.App;
import com.example.asus.news.R;
import com.example.asus.news.topic.model.TalkHeadBean;
import com.example.asus.news.util.BitmapCache;

import java.util.List;

/**
 * Created by ASUS on 2016/11/3.
 */

public class TalkRVAdapter extends RecyclerView.Adapter {
    private final ImageLoader loader;
    List<TalkHeadBean.话题Bean> talkHeadBeanList;
    private Context context;
    private LayoutInflater inflater;

    public TalkRVAdapter(List<TalkHeadBean.话题Bean> talkHeadBeanList, Context context) {
        this.talkHeadBeanList = talkHeadBeanList;
        this.context = context;
        inflater=LayoutInflater.from(context);
        loader = new ImageLoader(((App) ((Activity) context).getApplication()).getRequestQueue(), new BitmapCache(context));

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.topic_rv_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyViewHolder myViewHolder = (MyViewHolder) holder;
        TalkHeadBean.话题Bean Bean = talkHeadBeanList.get(position);
        myViewHolder.name.setText("#"+Bean.getTopicName()+"#");
        ImageLoader.ImageListener listener=ImageLoader.getImageListener(myViewHolder.face, R.drawable.cover, R.drawable.cover);
        loader.get(Bean.getPicUrl(), listener,myViewHolder.face.getWidth(),myViewHolder.face.getHeight());


    }

    @Override
    public int getItemCount() {
        return talkHeadBeanList.size();
    }
    private class MyViewHolder extends RecyclerView.ViewHolder {

        final ImageView face;
        final TextView name;

        public MyViewHolder(View itemView) {
            super(itemView);
            face = ((ImageView) itemView.findViewById(R.id.face));
            name = ((TextView) itemView.findViewById(R.id.name));
        }
    }
}
