package com.example.asus.news.news.model;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.util.TypedValue;
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
import com.example.asus.news.DetailActivity;
import com.example.asus.news.R;
import com.example.asus.news.news.view.VpAdapter;
import com.example.asus.news.util.BitmapCache;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ASUS on 2016/11/4.
 */

public class LvAdapter extends BaseAdapter {
    private final ImageLoader loader;
    private Context context;
    private LayoutInflater inflater;
    private List<BodyBean> list;

    public LvAdapter(Context context, List<BodyBean> list) {
        this.context = context;
        this.list = list;
        inflater = LayoutInflater.from(context);
        loader = new ImageLoader(((App) ((Activity) context).getApplication()).getRequestQueue(), new BitmapCache(context));

    }

    @Override
    public int getViewTypeCount() {
        return 8;
    }

    @Override
    public int getItemViewType(int position) {
        BodyBean bodyBean = list.get(position);
        int type = bodyBean.getType();
        return type;
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
    public View getView(final int i, View view, ViewGroup viewGroup) {
        final VpHolder vpHolder;
        PsHolder psHolder;
        MyViewHolder myViewHolder;
        Photo1Holder photo1Holder;
        Photo2Holder photo2Holder;
        VideoViewHolder videoViewHolder;
        Photo3ViewHolder photo3ViewHolder;
        LiveOrSpecialHolder liveOrSpecialHolder;
        final BodyBean bodyBean = list.get(i);
        int viewType = bodyBean.getType();

        if (viewType == 0) {
            if (view == null) {
                view = inflater.inflate(R.layout.rv_item_vpg, viewGroup, false);
                vpHolder = new VpHolder(view);
                view.setTag(vpHolder);
            } else {
                vpHolder = (VpHolder) view.getTag();
            }
            vpHolder.dotLayout.removeAllViews();
            final List<ADS> bodyBeanList = bodyBean.getList();
            if (bodyBeanList != null && bodyBeanList.size() > 1) {
                vpHolder.viewpager.setVisibility(View.VISIBLE);
                vpHolder.dotLayout.setVisibility(View.VISIBLE);
                vpHolder.img.setVisibility(View.GONE);
                for (int f = 0; f < bodyBeanList.size(); f++) {
                    //创建一个新的ImageView
                    View dot = new View(context);
                    LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                            (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 5, context.getResources().getDisplayMetrics()),
                            (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 5, context.getResources().getDisplayMetrics()));
                    lp.leftMargin = 10;
                    //设置view的宽高左边距等参数
                    dot.setLayoutParams(lp);
                    //默认情况下所有设置View的所有属性为false
                    dot.setEnabled(false);
                    //给view设置背景选择器，enable属性为true时背景为红色，否则为白色
                    dot.setBackgroundResource(R.drawable.dot_bg);
                    //将View添加到容器中
                    vpHolder.dotLayout.addView(dot);
                }
                //设置第一个View的enable为true，则该View  背景为红色
                vpHolder.dotLayout.getChildAt(0).setEnabled(true);
                VpAdapter vpAdapter = new VpAdapter(bodyBeanList, context);
                vpHolder.viewpager.setAdapter(vpAdapter);
                vpHolder.title.setText(bodyBean.getTitle());
                //  vpHolder.viewpager.setCurrentItem((Integer.MAX_VALUE / 2) - ((Integer.MAX_VALUE / 2) % bodyBeanList.size()));
                vpHolder.viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                    @Override
                    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                    }

                    @Override
                    public void onPageSelected(int position) {
                        vpHolder.title.setText(bodyBeanList.get(position % bodyBeanList.size()).getTitle());
                        for (int j = 0; j < bodyBeanList.size(); j++) {
                            if (j == position % bodyBeanList.size()) {
                                vpHolder.dotLayout.getChildAt(j).setEnabled(true);
                            } else {
                                vpHolder.dotLayout.getChildAt(j).setEnabled(false);
                            }
                        }
                    }

                    @Override
                    public void onPageScrollStateChanged(int state) {

                    }
                });

            } else if (bodyBeanList != null) {
                vpHolder.viewpager.setVisibility(View.GONE);
                vpHolder.dotLayout.setVisibility(View.GONE);
                vpHolder.img.setVisibility(View.VISIBLE);
                vpHolder.title.setText(bodyBean.getTitle());
                ImageLoader.ImageListener listener1 = ImageLoader.getImageListener(vpHolder.img, R.drawable.cover, R.drawable.cover);
                loader.get(bodyBeanList.get(0).getImgsrc(), listener1, vpHolder.img.getWidth(), vpHolder.img.getHeight());
             vpHolder.img.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View view) {
                     Intent intent = new Intent(context, DetailActivity.class);

                     String url = bodyBeanList.get(0).getUrl();
                     if (url.contains("http")){
                         intent.putExtra("flag",0);
                     }else{
                         intent.putExtra("type","doc");
                         intent.putExtra("flag",1);
                     }

                     intent.putExtra("url", url);
                     context.startActivity(intent);
                     Log.d("zanZQ", "onClick: "+url);
                 }
             });
            }


        } else if (viewType == 1) {
            if (view == null) {
                view = inflater.inflate(R.layout.rv_item_imgs, viewGroup, false);
                psHolder = new PsHolder(view);
                view.setTag(psHolder);
            } else {
                psHolder = (PsHolder) view.getTag();
            }
            List<String> imgextra = bodyBean.getImgextra();
            if (imgextra != null && imgextra.size() > 0) {
                ImageLoader.ImageListener listener1 = ImageLoader.getImageListener(psHolder.img1, R.drawable.cover, R.drawable.cover);
                loader.get(imgextra.get(0), listener1, psHolder.img1.getWidth(), psHolder.img1.getHeight());
                ImageLoader.ImageListener listener2 = ImageLoader.getImageListener(psHolder.img2, R.drawable.cover, R.drawable.cover);
                loader.get(imgextra.get(1), listener2, psHolder.img2.getWidth(), psHolder.img2.getHeight());
                ImageLoader.ImageListener listener3 = ImageLoader.getImageListener(psHolder.img3, R.drawable.cover, R.drawable.cover);
                loader.get(bodyBean.getImgsrc(), listener3, psHolder.img3.getWidth(), psHolder.img3.getHeight());
            }
            psHolder.beFrom.setText(bodyBean.getSource());
            psHolder.title.setText(bodyBean.getTitle());

        } else if (viewType == 2) {
            if (view == null) {
                view = inflater.inflate(R.layout.rv_item, viewGroup, false);
                myViewHolder = new MyViewHolder(view);
                view.setTag(myViewHolder);
            } else {
                myViewHolder = (MyViewHolder) view.getTag();
            }
            myViewHolder.comments.setText(bodyBean.getPtime());
            myViewHolder.title.setText(bodyBean.getTitle());
            myViewHolder.beFrom.setText(bodyBean.getSource());
            ImageLoader.ImageListener listener = ImageLoader.getImageListener(myViewHolder.icon, R.drawable.cover, R.drawable.cover);
            loader.get(bodyBean.getImgsrc(), listener, myViewHolder.icon.getWidth(), myViewHolder.icon.getHeight());

        } else if (viewType == 3) {
            if (view == null) {
                view = inflater.inflate(R.layout.rv_item_liveor_zt, viewGroup, false);
                liveOrSpecialHolder = new LiveOrSpecialHolder(view);
                view.setTag(liveOrSpecialHolder);
            } else {
                liveOrSpecialHolder = (LiveOrSpecialHolder) view.getTag();
            }
            if (bodyBean.getTag() != null) {
                liveOrSpecialHolder.count.setVisibility(View.VISIBLE);
                liveOrSpecialHolder.typeTV.setVisibility(View.VISIBLE);
                liveOrSpecialHolder.count.setText(bodyBean.getUser_count() + "参与");
                liveOrSpecialHolder.typeTV.setText(bodyBean.getTag());
                liveOrSpecialHolder.type.setImageResource(R.drawable.zb);
            } else {
                liveOrSpecialHolder.type.setImageResource(R.drawable.zt);
                liveOrSpecialHolder.count.setVisibility(View.GONE);
                liveOrSpecialHolder.typeTV.setVisibility(View.GONE);
            }
            ImageLoader.ImageListener listener = ImageLoader.getImageListener(liveOrSpecialHolder.img, R.drawable.cover, R.drawable.cover);
            loader.get(bodyBean.getImgsrc(), listener, liveOrSpecialHolder.img.getWidth(), liveOrSpecialHolder.img.getHeight());
            liveOrSpecialHolder.title.setText(bodyBean.getTitle());
            liveOrSpecialHolder.beFrom.setText(bodyBean.getSource());
        } else if (viewType == 4) {

            List<String> strings = bodyBean.getImgextra();
            if (view == null) {
                view = inflater.inflate(R.layout.item_photos, viewGroup, false);
                photo1Holder = new Photo1Holder(view);
                view.setTag(photo1Holder);
            } else {
                photo1Holder = (Photo1Holder) view.getTag();
            }
            photo1Holder.title.setText(bodyBean.getTitle());
            photo1Holder.count.setText(bodyBean.getSkipID() + "pics");
            photo1Holder.comments.setText(bodyBean.getUser_count() + "跟帖");
            ImageLoader.ImageListener listener = ImageLoader.getImageListener(photo1Holder.img, R.drawable.cover, R.drawable.cover);
            loader.get(strings.get(0), listener, photo1Holder.img.getWidth(), photo1Holder.img.getHeight());


        } else if (viewType == 5) {
            List<String> strings = bodyBean.getImgextra();
            if (view == null) {
                view = inflater.inflate(R.layout.item_photo2, viewGroup, false);
                photo2Holder = new Photo2Holder(view);
                view.setTag(photo2Holder);
            } else {
                photo2Holder = (Photo2Holder) view.getTag();
            }
            photo2Holder.title.setText(bodyBean.getTitle());
            photo2Holder.count.setText(bodyBean.getSkipID() + "pics");
            photo2Holder.comments.setText(bodyBean.getUser_count() + "跟帖");
            ImageLoader.ImageListener listener1 = ImageLoader.getImageListener(photo2Holder.img1, R.drawable.cover, R.drawable.cover);
            loader.get(strings.get(0), listener1, photo2Holder.img1.getWidth(), photo2Holder.img1.getHeight());
            ImageLoader.ImageListener listener2 = ImageLoader.getImageListener(photo2Holder.img2, R.drawable.cover, R.drawable.cover);
            loader.get(strings.get(1), listener2, photo2Holder.img2.getWidth(), photo2Holder.img2.getHeight());
            ImageLoader.ImageListener listener3 = ImageLoader.getImageListener(photo2Holder.img3, R.drawable.cover, R.drawable.cover);
            loader.get(strings.get(2), listener3, photo2Holder.img3.getWidth(), photo2Holder.img3.getHeight());
        } else if (viewType == 6) {
            List<String> strings = bodyBean.getImgextra();
            if (view == null) {
                view = inflater.inflate(R.layout.item_photo3, viewGroup, false);
                photo3ViewHolder = new Photo3ViewHolder(view);
                view.setTag(photo3ViewHolder);
            } else {
                photo3ViewHolder = (Photo3ViewHolder) view.getTag();
            }
            photo3ViewHolder.title.setText(bodyBean.getTitle());
            photo3ViewHolder.count.setText(bodyBean.getSkipID() + "pics");
            photo3ViewHolder.comments.setText(bodyBean.getUser_count() + "跟帖");
            ImageLoader.ImageListener listener1 = ImageLoader.getImageListener(photo3ViewHolder.img4, R.drawable.cover, R.drawable.cover);
            loader.get(strings.get(0), listener1, photo3ViewHolder.img4.getWidth(), photo3ViewHolder.img4.getHeight());
            ImageLoader.ImageListener listener2 = ImageLoader.getImageListener(photo3ViewHolder.img5, R.drawable.cover, R.drawable.cover);
            loader.get(strings.get(1), listener2, photo3ViewHolder.img5.getWidth(), photo3ViewHolder.img5.getHeight());
            ImageLoader.ImageListener listener3 = ImageLoader.getImageListener(photo3ViewHolder.img6, R.drawable.cover, R.drawable.cover);
            loader.get(strings.get(2), listener3, photo3ViewHolder.img6.getWidth(), photo3ViewHolder.img6.getHeight());
        } else if (viewType == 7) {
            if (view == null) {
                view = inflater.inflate(R.layout.rv_item_video, viewGroup, false);
                videoViewHolder=new VideoViewHolder(view);
                view.setTag(videoViewHolder);
            }else{
                videoViewHolder= (VideoViewHolder) view.getTag();
            }
            videoViewHolder.beFrom.setText(bodyBean.getSource());
            videoViewHolder.title.setText(bodyBean.getTitle());
            ImageLoader.ImageListener listener3 = ImageLoader.getImageListener(videoViewHolder.icon, R.drawable.cover, R.drawable.cover);
            loader.get(bodyBean.getImgsrc(), listener3, videoViewHolder.icon.getWidth(), videoViewHolder.icon.getHeight());

        }
        return view;
    }


    static class PsHolder {
        @BindView(R.id.title)
        TextView title;
        @BindView(R.id.beFrom)
        TextView beFrom;
        @BindView(R.id.img1)
        ImageView img1;
        @BindView(R.id.img2)
        ImageView img2;
        @BindView(R.id.img3)
        ImageView img3;

        PsHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    static class MyViewHolder {
        @BindView(R.id.icon)
        ImageView icon;
        @BindView(R.id.title)
        TextView title;
        @BindView(R.id.comments)
        TextView comments;
        @BindView(R.id.beFrom)
        TextView beFrom;

        MyViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    class VpHolder {
        @BindView(R.id.viewpager)
        ViewPager viewpager;
        @BindView(R.id.title)
        TextView title;
        @BindView(R.id.img)
        ImageView img;
        @BindView(R.id.dot_layout)
        LinearLayout dotLayout;

        VpHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    static class LiveOrSpecialHolder {
        @BindView(R.id.title)
        TextView title;
        @BindView(R.id.img)
        ImageView img;
        @BindView(R.id.beFrom)
        TextView beFrom;
        @BindView(R.id.type)
        ImageView type;
        @BindView(R.id.typeTV)
        TextView typeTV;
        @BindView(R.id.count)
        TextView count;

        LiveOrSpecialHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }


    static class Photo1Holder {
        @BindView(R.id.img)
        ImageView img;
        @BindView(R.id.count)
        TextView count;
        @BindView(R.id.head_layout)
        RelativeLayout headLayout;
        @BindView(R.id.title)
        TextView title;
        @BindView(R.id.comments)
        TextView comments;

        Photo1Holder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    static class Photo2Holder {
        @BindView(R.id.img1)
        ImageView img1;
        @BindView(R.id.img2)
        ImageView img2;
        @BindView(R.id.img3)
        ImageView img3;
        @BindView(R.id.type2)
        LinearLayout type2;
        @BindView(R.id.count)
        TextView count;
        @BindView(R.id.head_layout)
        RelativeLayout headLayout;
        @BindView(R.id.title)
        TextView title;
        @BindView(R.id.comments)
        TextView comments;

        Photo2Holder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    static class Photo3ViewHolder {
        @BindView(R.id.img4)
        ImageView img4;
        @BindView(R.id.img5)
        ImageView img5;
        @BindView(R.id.img6)
        ImageView img6;
        @BindView(R.id.type3)
        LinearLayout type3;
        @BindView(R.id.count)
        TextView count;
        @BindView(R.id.head_layout)
        RelativeLayout headLayout;
        @BindView(R.id.title)
        TextView title;
        @BindView(R.id.comments)
        TextView comments;

        Photo3ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    static class VideoViewHolder {
        @BindView(R.id.icon)
        ImageView icon;
        @BindView(R.id.play)
        ImageView play;
        @BindView(R.id.la)
        RelativeLayout la;
        @BindView(R.id.title)
        TextView title;
        @BindView(R.id.type)
        ImageView type;
        @BindView(R.id.beFrom)
        TextView beFrom;

        VideoViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
