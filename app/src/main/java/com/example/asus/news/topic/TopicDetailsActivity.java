package com.example.asus.news.topic;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asus.news.BaseActivity;
import com.example.asus.news.R;
import com.example.asus.news.topic.model.DataBean;
import com.example.asus.news.topic.model.WbMainBean;
import com.example.asus.news.topic.model.WbTopBean;
import com.example.asus.news.topic.presenter.WbTopDataPresenter;
import com.example.asus.news.topic.view.IShowWbTopView;
import com.example.asus.news.topic.view.WbMainAdapter;
import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TopicDetailsActivity extends BaseActivity implements IShowWbTopView {


    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.tab_layout)
    RelativeLayout tabLayout;
    @BindView(R.id.lv)
    PullToRefreshListView lv;
    @BindView(R.id.activity_topic_details)
    RelativeLayout activityTopicDetails;
    private WbTopDataPresenter wbTopDataPresenter = new WbTopDataPresenter(this);
    List<DataBean.ExpertListBean> expertList=new ArrayList<>();
    WbMainAdapter adapter;
    String id;
    int page=0;
    int nextPage=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic_details);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        String name = intent.getStringExtra("name");
        title.setText(name);
        wbTopDataPresenter.getWbMainData("classification/" + id, page+"-10");
        lv.setMode(PullToRefreshBase.Mode.BOTH);
        lv.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                wbTopDataPresenter.getWbMainData("classification/" + id, page+"-10");
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                page+=1;
                wbTopDataPresenter.getWbMainData("classification/" + id, page+"-10");
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
                 if (i==expertList.size()-3&&page==nextPage){
                     page+=1;
                     wbTopDataPresenter.getWbMainData("classification/" + id, page+"-10");
                 }
            }
        });
    }

    @Override
    public void getTopDataView(List<WbTopBean> list) {

    }

    @Override
    public void getMainDataView(WbMainBean wbMainBean) {
        if (wbMainBean != null) {
            expertList.addAll(wbMainBean.getData().getExpertList());
            nextPage+=1;
           if (adapter==null){
               adapter = new WbMainAdapter(expertList, this);
               lv.setAdapter(adapter);
           }else{
               lv.onRefreshComplete();
               adapter.notifyDataSetChanged();
           }
        } else {
            if (adapter!=null){
                lv.onRefreshComplete();
            }
            page=nextPage;
            Toast.makeText(TopicDetailsActivity.this, "网页错误...", Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick(R.id.back)
    public void onClick() {
        this.finish();
    }
}
