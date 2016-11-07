package com.example.asus.news.topic.view;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.ImageLoader;
import com.example.asus.news.App;
import com.example.asus.news.BaseActivity;
import com.example.asus.news.BaseFragment;
import com.example.asus.news.MyListView;
import com.example.asus.news.R;
import com.example.asus.news.topic.TopicDetailsActivity;
import com.example.asus.news.topic.model.DataBean;
import com.example.asus.news.topic.model.WbMainBean;
import com.example.asus.news.topic.model.WbTopBean;
import com.example.asus.news.topic.presenter.WbTopDataPresenter;
import com.example.asus.news.util.BitmapCache;
import com.example.asus.news.util.WbTopBeanDao;
import com.google.android.flexbox.FlexboxLayout;
import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ASUS on 2016/11/2.
 */

public class WbFragment extends BaseFragment implements IShowWbTopView {

    private View view;
    @BindView(R.id.lv)
    PullToRefreshListView lv;
    private boolean isopen = true;
    private WbTopBeanDao cateDao;
    private List<WbTopBean> list;
    private LayoutInflater inflater;
    private WbTopDataPresenter wbTopDataPresenter = new WbTopDataPresenter(this);
    private ImageLoader loader;
    private List<DataBean.ExpertListBean> expertListBeen = new ArrayList<>();
    private WbMainAdapter wbMainAdapter;
    private String SinglepageCount = "-10";
    private int page = 0;
    private int nextPage = 1;
    private View head;
    private FlexboxLayout fb1;
    private FlexboxLayout fb2;
    ImageView closeOropen;
    LinearLayout topicTop;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            this.inflater = inflater;
            view = inflater.inflate(R.layout.fragment_live_fl, container, false);
            head = inflater.inflate(R.layout.fragment_topic_wb, null);
            ButterKnife.bind(this, view);
            topicTop = ((LinearLayout) head.findViewById(R.id.topic_layout_top));
            fb1 = ((FlexboxLayout) head.findViewById(R.id.fb1));
            fb2 = ((FlexboxLayout) head.findViewById(R.id.fb2));
            closeOropen = ((ImageView) head.findViewById(R.id.closeOropen));
            closeOropen.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    if (isopen) {
                        fb2.setVisibility(View.GONE);
                        closeOropen.setImageResource(R.drawable.down);
                    } else {
                        fb2.setVisibility(View.VISIBLE);
                        closeOropen.setImageResource(R.drawable.up);
                    }
                    isopen = !isopen;

                }
            });
            loader = new ImageLoader(((App) (getActivity()).getApplication()).getRequestQueue(), new BitmapCache(getContext()));
            lv.setMode(PullToRefreshBase.Mode.BOTH);
            lv.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
                @Override
                public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                    wbTopDataPresenter.getWbMainData(null, page + SinglepageCount);
                }

                @Override
                public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                    page += 1;
                    wbTopDataPresenter.getWbMainData(null, page + SinglepageCount);
                }
            });
            ILoadingLayout layoutProxy = lv.getLoadingLayoutProxy();
            layoutProxy.setLoadingDrawable(new BitmapDrawable(getResources(), BitmapFactory.decodeResource(getResources(), R.drawable.qv)));
            layoutProxy.setPullLabel("下拉刷新");
            layoutProxy.setRefreshingLabel("刷新中...");
            layoutProxy.setReleaseLabel("松开刷新");
            lv.setOnScrollListener(new AbsListView.OnScrollListener() {
                @Override
                public void onScrollStateChanged(AbsListView absListView, int i) {

                }

                @Override
                public void onScroll(AbsListView absListView, int i, int i1, int i2) {
                    if (i == expertListBeen.size() - 3 && nextPage == page) {
                        page += 1;
                        wbTopDataPresenter.getWbMainData(null, page + SinglepageCount);
                    }
                }
            });
            lv.getRefreshableView().addHeaderView(head);

        }

        wbTopDataPresenter.getWbMainData(null, page + SinglepageCount);
        cateDao = ((BaseActivity) getActivity()).daoSession.getWbTopBeanDao();
        list = cateDao.queryBuilder().list();
        if (list == null || list.size() == 0) {
            wbTopDataPresenter.getTopData();
        } else {
            initTopView();
        }


        ButterKnife.bind(this, view);
        return view;
    }

    private void initTopView() {
        fb2.removeAllViews();
        fb1.removeAllViews();
        for (int i = 0; i < list.size(); i++) {
            WbTopBean wbTopBean = list.get(i);
            View view = inityTv(wbTopBean);
            if (i < 5) {
                fb1.addView(view);
            } else {
                fb2.addView(view);
            }
        }
    }

    @Override
    public void getTopDataView(List<WbTopBean> list) {
        if (list != null && list.size() > 0) {
            this.list = list;
            initTopView();
            for (int i = 0; i < list.size(); i++) {
                cateDao.insert(list.get(i));
            }
        } else {
            Toast.makeText(getActivity(), "获取数据错误!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void getMainDataView(WbMainBean wbMainBean) {
        if (wbMainBean != null) {
            List<DataBean.ExpertListBean> expertList = wbMainBean.getData().getExpertList();
            expertListBeen.addAll(expertList);
            nextPage = page;
            if (wbMainAdapter == null) {
                wbMainAdapter = new WbMainAdapter(expertListBeen, getContext());
                lv.setAdapter(wbMainAdapter);

            } else {
                lv.onRefreshComplete();
                wbMainAdapter.notifyDataSetChanged();
            }
        } else {
            page = nextPage;
            lv.onRefreshComplete();
            if (wbMainAdapter!=null){
                Toast.makeText(getActivity(), "已经到达最后一页", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(getActivity(), "获取数据错误!", Toast.LENGTH_SHORT).show();
            }


        }
    }

    private View inityTv(final WbTopBean bean) {
        View layout = inflater.inflate(R.layout.fragment_topic_layout, null);
        FlexboxLayout.LayoutParams lp = new FlexboxLayout.LayoutParams(getResources().getDisplayMetrics().widthPixels / 7, getResources().getDisplayMetrics().widthPixels / 5);
      /*  lp.leftMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 6, getResources().getDisplayMetrics());
        lp.rightMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 6, getResources().getDisplayMetrics());
        lp.bottomMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, getResources().getDisplayMetrics());
      */
        lp.topMargin = lp.bottomMargin = lp.leftMargin = lp.rightMargin = getResources().getDisplayMetrics().widthPixels / 7 / 5 / 2;
        layout.setLayoutParams(lp);
        layout.setPadding(
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, getResources().getDisplayMetrics()),
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4, getResources().getDisplayMetrics()),
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, getResources().getDisplayMetrics()),
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4, getResources().getDisplayMetrics())
        );
        TextView tv = (TextView) layout.findViewById(R.id.name);
        ImageView img = (ImageView) layout.findViewById(R.id.img);
        ImageLoader.ImageListener listener = ImageLoader.getImageListener(img, R.drawable.cover, R.drawable.cover);
        loader.get(bean.getIcon(), listener, img.getWidth(), img.getHeight());
        tv.setGravity(Gravity.CENTER);
        String text = bean.getName();
        tv.setText(text);
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), TopicDetailsActivity.class);
                intent.putExtra("id", bean.getId());
                intent.putExtra("name", bean.getName());
                getActivity().startActivity(intent);
            }
        });
        return layout;
    }
}
