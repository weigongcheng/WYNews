package com.example.asus.news.topic.view;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.example.asus.news.App;
import com.example.asus.news.R;
import com.example.asus.news.topic.model.ExpertBean;
import com.example.asus.news.topic.model.TalkDataBean;
import com.example.asus.news.topic.model.UserTalkBean;
import com.example.asus.news.util.BitmapCache;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ASUS on 2016/11/3.
 */

public class TalkAdapter extends BaseAdapter {
    private final ImageLoader loader;
    private List<TalkDataBean> list;
    private Context context;
    private LayoutInflater inflater;

    public TalkAdapter(List<TalkDataBean> list, Context context) {
        this.list = list;
        this.context = context;
        inflater = LayoutInflater.from(context);
        loader = new ImageLoader(((App) ((Activity) context).getApplication()).getRequestQueue(), new BitmapCache(context));

    }

    @Override
    public int getItemViewType(int position) {

        return list.get(position).getType();
    }

    @Override
    public int getViewTypeCount() {
        return 3;
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
        Img3ViewHolder img3ViewHolder;
        TjViewHolder tjViewHolder;
        UserViewHolder userViewHolder;
        TalkDataBean talkDataBean = list.get(i);
        int type = talkDataBean.getType();
        switch (type) {
            case 0:
                if (view == null) {
                    view = inflater.inflate(R.layout.topic_ht_lv_item_tj, viewGroup, false);
                    tjViewHolder = new TjViewHolder(view);
                    view.setTag(tjViewHolder);
                } else {
                    tjViewHolder = (TjViewHolder) view.getTag();
                }
                List<ExpertBean> expertList = talkDataBean.getExpertList();
                ExpertBean expertBean1 = expertList.get(0);
                tjViewHolder.count1.setText(expertBean1.getConcernCount() + "关注");
                tjViewHolder.name1.setText(expertBean1.getName());
              //  ImageLoader.ImageListener listener1 = ImageLoader.getImageListener(tjViewHolder.face1, R.drawable.cover, R.drawable.cover);
             //   loader.get(expertBean1.getHeadpicurl(), listener1, tjViewHolder.face1.getWidth(), tjViewHolder.face1.getHeight());
              tjViewHolder.face1.setImageURI(expertBean1.getHeadpicurl());
                ExpertBean expertBean2 = expertList.get(1);
                tjViewHolder.count2.setText(expertBean2.getConcernCount() + "关注");
                tjViewHolder.name2.setText(expertBean2.getName());
              //  ImageLoader.ImageListener listener2 = ImageLoader.getImageListener(tjViewHolder.face2, R.drawable.cover, R.drawable.cover);
              //  loader.get(expertBean2.getHeadpicurl(), listener2, tjViewHolder.face2.getWidth(), tjViewHolder.face2.getHeight());
                 tjViewHolder.face2.setImageURI(expertBean2.getHeadpicurl());
                ExpertBean expertBean3 = expertList.get(2);
                tjViewHolder.count3.setText(expertBean3.getConcernCount() + "关注");
                tjViewHolder.name3.setText(expertBean3.getName());
             //   ImageLoader.ImageListener listener3 = ImageLoader.getImageListener(tjViewHolder.face3, R.drawable.cover, R.drawable.cover);
          //      loader.get(expertBean3.getHeadpicurl(), listener3, tjViewHolder.face3.getWidth(), tjViewHolder.face3.getHeight());
                 tjViewHolder.face3.setImageURI(expertBean3.getHeadpicurl());
                ExpertBean expertBean4 = expertList.get(3);
                tjViewHolder.count4.setText(expertBean4.getConcernCount() + "关注");
                tjViewHolder.name4.setText(expertBean4.getName());
              //  ImageLoader.ImageListener listener4 = ImageLoader.getImageListener(tjViewHolder.face4, R.drawable.cover, R.drawable.cover);
              //  loader.get(expertBean4.getHeadpicurl(), listener4, tjViewHolder.face4.getWidth(), tjViewHolder.face4.getHeight());
              tjViewHolder.face4.setImageURI(expertBean4.getHeadpicurl());

                tjViewHolder.title.setText("精选问吧推荐");

                break;
            case 1:
                if (view == null) {
                    view = inflater.inflate(R.layout.topic_ht_lv_item, viewGroup, false);
                    userViewHolder = new UserViewHolder(view);
                    view.setTag(userViewHolder);
                } else {
                    userViewHolder = (UserViewHolder) view.getTag();
                }
                userViewHolder.title.setText("#" + talkDataBean.getName() + "#");
                userViewHolder.problemcount.setText(talkDataBean.getTalkCount() + "讨论");
                userViewHolder.count.setText(talkDataBean.getConcernCount() + "关注");
                userViewHolder.type.setText(talkDataBean.getClassification());
                UserTalkBean userTalkBean = talkDataBean.getUserTalkBeen().get(0);
                userViewHolder.usermsg1.setText(userTalkBean.getContent());
                UserTalkBean userTalkBean1 = talkDataBean.getUserTalkBeen().get(1);
                userViewHolder.usermsg2.setText(userTalkBean1.getContent());
                String userHeadPicUrl = userTalkBean.getUserHeadPicUrl();
                //     if (userHeadPicUrl !=null&& userHeadPicUrl.length()>10){
               // ImageLoader.ImageListener listener5 = ImageLoader.getImageListener(userViewHolder.face, R.drawable.cover, R.drawable.cover);
              //  loader.get(userHeadPicUrl, listener5, userViewHolder.face.getWidth(), userViewHolder.face.getHeight());
                userViewHolder.face.setImageURI(userHeadPicUrl);
                //   }
                String userHeadPicUrl1 = userTalkBean1.getUserHeadPicUrl();
             //   ImageLoader.ImageListener listener6 = ImageLoader.getImageListener(userViewHolder.face2, R.drawable.cover, R.drawable.cover);
           //     loader.get(userHeadPicUrl1, listener6, userViewHolder.face2.getWidth(), userViewHolder.face2.getHeight());
                userViewHolder.face2.setImageURI(userHeadPicUrl1);
                break;
            case 2:
                if (view == null) {
                    view = inflater.inflate(R.layout.topic_ht_lv_3img, viewGroup, false);
                    img3ViewHolder = new Img3ViewHolder(view);
                    view.setTag(img3ViewHolder);
                } else {
                    img3ViewHolder = (Img3ViewHolder) view.getTag();
                }
                img3ViewHolder.title.setText("#" + talkDataBean.getName() + "#");
                img3ViewHolder.problemcount.setText(talkDataBean.getTalkCount() + "讨论");
                img3ViewHolder.count.setText(talkDataBean.getConcernCount() + "关注");
                img3ViewHolder.type.setText(talkDataBean.getClassification());
                List<String> talkPicture = talkDataBean.getTalkPicture();
                String a = talkPicture.get(0);
                String b = talkPicture.get(1);
                String c = talkPicture.get(2);
                ImageLoader.ImageListener listener7 = ImageLoader.getImageListener(img3ViewHolder.img1, R.drawable.cover, R.drawable.cover);
                loader.get(a, listener7, img3ViewHolder.img1.getWidth(), img3ViewHolder.img1.getHeight());
                ImageLoader.ImageListener listener8 = ImageLoader.getImageListener(img3ViewHolder.img2, R.drawable.cover, R.drawable.cover);
                loader.get(b, listener8, img3ViewHolder.img2.getWidth(), img3ViewHolder.img2.getHeight());
                ImageLoader.ImageListener listener9 = ImageLoader.getImageListener(img3ViewHolder.img3, R.drawable.cover, R.drawable.cover);
                loader.get(c, listener9, img3ViewHolder.img3.getWidth(), img3ViewHolder.img3.getHeight());
                break;
        }

        return view;
    }


    static class UserViewHolder {
        @BindView(R.id.title)
        TextView title;
        @BindView(R.id.face)
        SimpleDraweeView face;
        @BindView(R.id.textleft)
        TextView textleft;
        @BindView(R.id.textright)
        TextView textright;
        @BindView(R.id.usermsg1)
        TextView usermsg1;
        @BindView(R.id.msg1)
        RelativeLayout msg1;
        @BindView(R.id.face2)
        SimpleDraweeView face2;
        @BindView(R.id.textleft2)
        TextView textleft2;
        @BindView(R.id.textright2)
        TextView textright2;
        @BindView(R.id.usermsg2)
        TextView usermsg2;
        @BindView(R.id.msg2)
        RelativeLayout msg2;
        @BindView(R.id.type)
        TextView type;
        @BindView(R.id.count)
        TextView count;
        @BindView(R.id.problemcount)
        TextView problemcount;
        @BindView(R.id.addFlow)
        TextView addFlow;

        UserViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    static class TjViewHolder {
        @BindView(R.id.title)
        TextView title;
        @BindView(R.id.face1)
        SimpleDraweeView face1;
        @BindView(R.id.name1)
        TextView name1;
        @BindView(R.id.count1)
        TextView count1;
        @BindView(R.id.face2)
        SimpleDraweeView face2;
        @BindView(R.id.name2)
        TextView name2;
        @BindView(R.id.count2)
        TextView count2;
        @BindView(R.id.face3)
        SimpleDraweeView face3;
        @BindView(R.id.name3)
        TextView name3;
        @BindView(R.id.count3)
        TextView count3;
        @BindView(R.id.face4)
        SimpleDraweeView face4;
        @BindView(R.id.name4)
        TextView name4;
        @BindView(R.id.count4)
        TextView count4;

        TjViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    static class Img3ViewHolder {
        @BindView(R.id.title)
        TextView title;
        @BindView(R.id.img1)
        ImageView img1;
        @BindView(R.id.img2)
        ImageView img2;
        @BindView(R.id.img3)
        ImageView img3;
        @BindView(R.id.msgs)
        LinearLayout msgs;
        @BindView(R.id.type)
        TextView type;
        @BindView(R.id.count)
        TextView count;
        @BindView(R.id.problemcount)
        TextView problemcount;
        @BindView(R.id.addFlow)
        TextView addFlow;

        Img3ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}

