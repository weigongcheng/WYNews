package com.example.asus.news.topic.view;

import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.asus.news.BaseFragment;
import com.example.asus.news.MyListView;
import com.example.asus.news.R;
import com.example.asus.news.topic.model.TalkDataBean;
import com.example.asus.news.topic.model.TalkHeadBean;
import com.example.asus.news.topic.presenter.TalkDataPresenter;
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

public class HtFragment extends BaseFragment implements IShowTalkView {

    @BindView(R.id.lv)
    PullToRefreshListView lv;

    private View view;
    View head;
    private RecyclerView recycleView;
    private List<TalkDataBean> list = new ArrayList<>();
    private TalkDataPresenter talkDataPresenter = new TalkDataPresenter(this);
    private int page = 0;
    private int nextPage=0;
    TalkAdapter talkAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_live_fl, container, false);
            head = inflater.inflate(R.layout.fragment_topic_ht, null);
            ButterKnife.bind(this, view);
            recycleView = ((RecyclerView) head.findViewById(R.id.recycler_view));
            recycleView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
            lv.getRefreshableView().addHeaderView(head);
            talkDataPresenter.getTalkMainData(page + "");
            talkDataPresenter.getTalkHeadData();
            initLv();
        }


        return view;
    }

    private void initLv() {
        lv.setMode(PullToRefreshBase.Mode.BOTH);
        lv.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                talkDataPresenter.getTalkMainData(page + "");
                talkDataPresenter.getTalkHeadData();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                page += 1;
                talkDataPresenter.getTalkMainData(page + "");
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
                if (i == list.size() - 3 && nextPage == page) {
                    page += 1;
                    talkDataPresenter.getTalkMainData(page + "");
                }
            }
        });
    }

    @Override
    public void getTalkData(List<TalkDataBean> talkDataBeanList) {

        if (talkDataBeanList != null && talkDataBeanList.size() > 0) {
            lv.onRefreshComplete();
            page=nextPage;
            list.addAll(talkDataBeanList);
            if (talkAdapter == null) {
                talkAdapter = new TalkAdapter(list, getActivity());
                lv.setAdapter(talkAdapter);
            } else {
                talkAdapter.notifyDataSetChanged();
            }
        } else {
            lv.onRefreshComplete();
            page = nextPage;
            lv.onRefreshComplete();
            if (talkAdapter!=null){
                Toast.makeText(getActivity(), "已经到达最后一页", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(getActivity(), "获取数据错误!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void getTalkHeadData(TalkHeadBean talkHeadBean) {
        if (talkHeadBean != null) {
            List<TalkHeadBean.话题Bean> talkHeadBeanList = talkHeadBean.get话题();
            TalkRVAdapter rvAdapter = new TalkRVAdapter(talkHeadBeanList, getActivity());
            recycleView.setAdapter(rvAdapter);
        } else {
            Toast.makeText(getActivity(), "TalkHead刷新失败", Toast.LENGTH_SHORT).show();
        }
    }
}
