package com.example.asus.news.news.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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
import com.example.asus.news.news.model.SearchBean;
import com.example.asus.news.util.BitmapCache;

import org.greenrobot.greendao.annotation.Id;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by ASUS on 2016/11/1.
 */

public class SearchRVAdapter extends RecyclerView.Adapter {
   private List<SearchBean >list ;
    private Context context;
    private  ImageLoader loader;
    private LayoutInflater inflater;
    private SimpleDateFormat dateFormat =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public SearchRVAdapter(List<SearchBean> list, Context context) {
        this.list = list;
        this.context = context;
        inflater=LayoutInflater.from(context);
      loader = new ImageLoader(((App) ((Activity) context).getApplication()).getRequestQueue(), new BitmapCache(context));
    }

    @Override
    public int getItemViewType(int position) {
        int type = list.get(position).getType();
        return type;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType==0){
            View view = inflater.inflate(R.layout.search_item_wb, parent, false);
            return new WbHolder(view);
        }else if (viewType==2){
            View view = inflater.inflate(R.layout.search_item_baike, parent, false);
            return new BkHolder(view);
        }else if (viewType==1){
            View view = inflater.inflate(R.layout.search_item_wb, parent, false);
            return new DyHolder(view);
        }else{
            View view = inflater.inflate(R.layout.serch_item_doc, parent, false);
            return new DocHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        SearchBean searchBean = list.get(position);
        if (holder instanceof WbHolder){
                     WbHolder wbHolder = (WbHolder) holder;
                     wbHolder.title.setText(searchBean.getTitle());
                     wbHolder.time.setText(dateFormat.format(new Date(Long.valueOf(searchBean.getPtime()))));
                     wbHolder.type.setText("问吧");
                 }else  if (holder instanceof BkHolder){
                     BkHolder bkHolder = (BkHolder) holder;
                     bkHolder.digest.setText(searchBean.getDigest());
                    bkHolder.keyword.setText(searchBean.getTitle());
                      bkHolder.type.setText("有道百科");
                 }else if (holder instanceof DocHolder){
            DocHolder docHolder = (DocHolder) holder;
            docHolder.title.setText(searchBean.getTitle());
            docHolder.time.setText(searchBean.getPtime());
            String imgurl = searchBean.getImgurl();
            if (imgurl!=null&&!imgurl.trim().equals("")){
                docHolder.icon.setVisibility(View.VISIBLE);
                ImageLoader.ImageListener listener3=ImageLoader.getImageListener(docHolder.icon, R.drawable.cover, R.drawable.cover);
                loader.get(imgurl, listener3,docHolder.icon.getWidth(),docHolder.icon.getHeight());
            }else{
                docHolder.icon.setVisibility(View.GONE);
            }
            if (searchBean.getSkipType().equals("video")){
                docHolder.iv_type.setVisibility(View.VISIBLE);
                docHolder.iv_type.setImageResource(R.drawable.night_a7b);
            }else if (searchBean.getSkipType().equals("photoset")){
                docHolder.iv_type.setVisibility(View.VISIBLE);
                docHolder.iv_type.setImageResource(R.drawable.night_a78);
            }else{
                docHolder.iv_type.setVisibility(View.GONE);
            }

        }else  if (holder instanceof DyHolder){
            DyHolder dyHolder = (DyHolder) holder;
            dyHolder.title.setText(searchBean.getTitle());
            dyHolder.time.setText(searchBean.getPtime());
            dyHolder.type.setText("订阅号");
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    private class WbHolder extends RecyclerView.ViewHolder {
        public final TextView title;
        public final TextView time;
        public final TextView type;

        public WbHolder(View view) {
            super(view);
            title = ((TextView) view.findViewById(R.id.title));
            time = ((TextView) view.findViewById(R.id.time));
            type = ((TextView) view.findViewById(R.id.type));
        }
    }

    private class BkHolder extends RecyclerView.ViewHolder {
        public final TextView keyword;
        public final TextView digest;
        public final TextView type;

        public BkHolder(View view) {
            super(view);
            keyword = ((TextView) view.findViewById(R.id.keywords));
            digest = ((TextView) view.findViewById(R.id.digest));
            type = ((TextView) view.findViewById(R.id.type));
        }
    }

    private class DocHolder extends RecyclerView.ViewHolder {
        public final TextView title;
        public final ImageView iv_type;
        public final TextView time;
        public final ImageView icon;

        public DocHolder(View view) {
            super(view);
            title = ((TextView) view.findViewById(R.id.title));
            iv_type = ((ImageView) view.findViewById(R.id.iv_type));
            time = ((TextView) view.findViewById(R.id.time));
            icon = ((ImageView) view.findViewById(R.id.icon));
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, DetailActivity.class);
                    intent.putExtra("flag",1);
                    SearchBean searchBean = list.get(getLayoutPosition());
                    intent.putExtra("type",searchBean.getSkipType());
                    intent.putExtra("url", searchBean.getId());
                    context.startActivity(intent);
                }
            });
        }
    }

    private class DyHolder extends RecyclerView.ViewHolder {
        public final TextView title;
        public final TextView time;
        public final TextView type;

        public DyHolder(View view) {
            super(view);
            title = ((TextView) view.findViewById(R.id.title));
            time = ((TextView) view.findViewById(R.id.time));
            type = ((TextView) view.findViewById(R.id.type));

        }
    }
}
