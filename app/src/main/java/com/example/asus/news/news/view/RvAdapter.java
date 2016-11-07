package com.example.asus.news.news.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.example.asus.news.App;
import com.example.asus.news.DetailActivity;
import com.example.asus.news.R;
import com.example.asus.news.news.model.BodyBean;
import com.example.asus.news.util.BitmapCache;

import java.util.List;

/**
 * Created by ASUS on 2016/10/31.
 */

public class RvAdapter extends RecyclerView.Adapter {
    private final ImageLoader loader;
    private Context context;
    private LayoutInflater inflater;
    private List<BodyBean> list;

    public RvAdapter(Context context, List<BodyBean> list) {
        this.context = context;
        this.list = list;
        inflater=LayoutInflater.from(context);
        loader = new ImageLoader(((App) ((Activity) context).getApplication()).getRequestQueue(), new BitmapCache(context));
    }

    @Override
    public int getItemViewType(int position) {
        BodyBean bodyBean = list.get(position);
        int type = bodyBean.getType();
        return type;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType==0){
            View view = inflater.inflate(R.layout.rv_item_vpg, parent, false);
            return new VpHolder(view);
        }else if (viewType==1){
            View view = inflater.inflate(R.layout.rv_item_imgs, parent, false);
            return new PsHolder(view);
        }else {
            View view = inflater.inflate(R.layout.rv_item, parent, false);
            return new MyViewHolder(view);
        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final BodyBean bodyBean = list.get(position);
        if (position==list.size()-4){
            refreshListener.refreshView();
        }
        if (holder instanceof VpHolder){
                  final VpHolder vpHolder = (VpHolder) holder;
                  VpAdapter vpAdapter = new VpAdapter(bodyBean.getList(), context);
                 vpHolder.viewPager.setAdapter(vpAdapter);

              vpHolder.viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                  @Override
                  public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                  }

                  @Override
                  public void onPageSelected(int position) {
                   vpHolder.title.setText(bodyBean.getList().get(position).getTitle());

                  }

                  @Override
                  public void onPageScrollStateChanged(int state) {

                  }
              });
              }else if (holder instanceof PsHolder){
                  PsHolder psHolder = (PsHolder) holder;
            List<String> imgextra = bodyBean.getImgextra();
            if (imgextra!=null&&imgextra.size()>0) {
                ImageLoader.ImageListener listener1=ImageLoader.getImageListener(psHolder.img1, R.drawable.cover, R.drawable.cover);
                loader.get(imgextra.get(0), listener1,psHolder.img1.getWidth(),psHolder.img1.getHeight());
                ImageLoader.ImageListener listener2=ImageLoader.getImageListener(psHolder.img2, R.drawable.cover, R.drawable.cover);
                loader.get(imgextra.get(1), listener2,psHolder.img2.getWidth(),psHolder.img2.getHeight());
                ImageLoader.ImageListener listener3=ImageLoader.getImageListener(psHolder.img3, R.drawable.cover, R.drawable.cover);
                loader.get(bodyBean.getImgsrc(), listener3,psHolder.img3.getWidth(),psHolder.img3.getHeight());
            }
            psHolder.source.setText(bodyBean.getSource());
            psHolder.title.setText(bodyBean.getTitle());
              }else {
            MyViewHolder myViewHolder = (MyViewHolder) holder;
            myViewHolder.comments.setText(bodyBean.getPtime());
            myViewHolder.title.setText(bodyBean.getTitle());
            myViewHolder.source.setText(bodyBean.getSource());
            ImageLoader.ImageListener listener=ImageLoader.getImageListener(myViewHolder.icon, R.drawable.cover, R.drawable.cover);
            loader.get(bodyBean.getImgsrc(), listener);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    private class MyViewHolder extends RecyclerView.ViewHolder {

        public final TextView title;
        public final ImageView icon;
        public final TextView source;
        public final TextView comments;

        public MyViewHolder(View itemView) {
            super(itemView);
            title = ((TextView) itemView.findViewById(R.id.title));
            icon = ((ImageView) itemView.findViewById(R.id.icon));
            source = ((TextView) itemView.findViewById(R.id.beFrom));
            comments = ((TextView) itemView.findViewById(R.id.comments));
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, DetailActivity.class);
                    intent.putExtra("flag",0);
                    intent.putExtra("url",list.get(getLayoutPosition()).getUrl());
                    context.startActivity(intent);
                }
            });
        }
    }
    private class VpHolder extends RecyclerView.ViewHolder {

        private final ViewPager viewPager;
        public final TextView title;

        public VpHolder(View itemView) {
            super(itemView);
            viewPager = (ViewPager) itemView.findViewById(R.id.viewpager);
            title = ((TextView) itemView.findViewById(R.id.title));

        }

    }
    private class PsHolder extends RecyclerView.ViewHolder {


        private final ImageView img1;
        private final ImageView img2;
        private final ImageView img3;
        private final TextView title;
        private final TextView source;

        public PsHolder(View itemView) {
            super(itemView);
            img1 = ((ImageView) itemView.findViewById(R.id.img1));
            img2 = (ImageView) itemView.findViewById(R.id.img2);
            img3 = (ImageView) itemView.findViewById(R.id.img3);
            title = ((TextView) itemView.findViewById(R.id.title));
            source = ((TextView) itemView.findViewById(R.id.beFrom));
             itemView.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View view) {
                     Intent intent = new Intent(context, DetailActivity.class);
                     intent.putExtra("flag",0);
                     intent.putExtra("url",list.get(getLayoutPosition()).getUrl());
                     context.startActivity(intent);
                 }
             });
        }
    }
    public interface RefreshListener {
        void refreshView();
    }
    private RefreshListener refreshListener;

    public void setRefreshListener(RefreshListener refreshListener) {
        this.refreshListener = refreshListener;
    }
}
