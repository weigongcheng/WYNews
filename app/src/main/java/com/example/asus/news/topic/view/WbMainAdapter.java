package com.example.asus.news.topic.view;

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
import com.example.asus.news.topic.model.DataBean;
import com.example.asus.news.util.BitmapCache;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ASUS on 2016/11/3.
 */

public class WbMainAdapter extends BaseAdapter {
    private final ImageLoader loader;
    private List<DataBean.ExpertListBean> list;
    private LayoutInflater inflater;
    private Context context;

    public WbMainAdapter(List<DataBean.ExpertListBean> list, Context context) {
        this.list = list;
        this.context = context;
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
            view = inflater.inflate(R.layout.topic_wb_item, viewGroup, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        DataBean.ExpertListBean bean = list.get(i);
        holder.count.setText(bean.getConcernCount() + "关注");
        holder.face.setImageURI(bean.getHeadpicurl());
        ImageLoader.ImageListener listener2 = ImageLoader.getImageListener(holder.img, R.drawable.cover, R.drawable.cover);
        loader.get(bean.getPicurl(), listener2, holder.img.getWidth(), holder.img.getHeight());
        holder.name.setText(bean.getName() + "/" + bean.getTitle());
        holder.type.setText(bean.getClassification());
        holder.problemcount.setText(bean.getQuestionCount() + "提问");
        holder.introduce.setText(bean.getAlias());
        return view;
    }


    static class ViewHolder {
        @BindView(R.id.name)
        TextView name;
        @BindView(R.id.img)
        ImageView img;
        @BindView(R.id.face)
        SimpleDraweeView face;
        @BindView(R.id.introduce)
        TextView introduce;
        @BindView(R.id.type)
        TextView type;
        @BindView(R.id.count)
        TextView count;
        @BindView(R.id.problemcount)
        TextView problemcount;
        @BindView(R.id.addFlow)
        TextView addFlow;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
