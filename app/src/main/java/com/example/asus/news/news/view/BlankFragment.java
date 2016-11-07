package com.example.asus.news.news.view;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.asus.news.BaseFragment;
import com.example.asus.news.DetailActivity;
import com.example.asus.news.R;
import com.example.asus.news.live.LiveDetailsActivity;
import com.example.asus.news.live.model.LiveReviewBean;
import com.example.asus.news.news.model.BodyBean;
import com.example.asus.news.news.model.LvAdapter;
import com.example.asus.news.news.model.PicAdapter;
import com.example.asus.news.news.model.PicBean;
import com.example.asus.news.news.model.SearchBean;
import com.example.asus.news.news.model.TListBean;
import com.example.asus.news.news.presenter.BodyPresenter;
import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.internal.framed.Variant;

/**
 * Created by ASUS on 2016/10/31.
 */

public class BlankFragment extends BaseFragment implements IShowBlankView, RvAdapter.RefreshListener {
    @BindView(R.id.lv)
    PullToRefreshListView lv;
    /*    @BindView(R.id.recycler_view)
        RecyclerView recyclerView;
        @BindView(R.id.refreshLayout)
        SwipeRefreshLayout refreshLayout;*/
    private View view;
    private TListBean bean=new TListBean();
    private BodyPresenter bodyPresenter = new BodyPresenter(this);
    private int page;
    private int nextPage = 0;
    private LvAdapter rvAdapter;
    private List<BodyBean> bodyBeanList = new ArrayList<>();
    private List<PicBean> picBeen=new ArrayList<>();
    String tid;
    PicAdapter picAdapter;

    public static BlankFragment getInstance(TListBean tListBean) {
        BlankFragment fragment = new BlankFragment();
        Bundle args = new Bundle();
        args.putString("tid", tListBean.getTid());
        args.putInt("page",0);
        args.putString("Topicid",tListBean.getTopicid());
        args.putString("name",tListBean.getTname());
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getArguments();
        tid = bundle.getString("tid");
         page= bundle.getInt("page",0);
        String name = bundle.getString("name");
        String topicid = bundle.getString("Topicid");
        bean.setTname(name);
        bean.setTid(tid);
        bean.setTopicid(topicid);
        bean.setHasAD(page);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.blank_fg_layout, container, false);
            ButterKnife.bind(this, view);
            //   recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
            initRF();
            bodyBeanList.clear();
            initData();
          lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
              @Override
              public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                 if (bodyBeanList!=null&&bodyBeanList.size()>0){
                     BodyBean bodyBean = bodyBeanList.get(i-1);
                     Log.d("zanZQ", "onItemClick:type "+bodyBean.getType());
                     if (bodyBean.getType()!=0){
                        if (bodyBean.getType()==3){
                             Intent intent = new Intent(getActivity(), LiveDetailsActivity.class);
                             intent.putExtra("id", bodyBean.getSkipID() + "");
                             getActivity().startActivity(intent);
                         }else  if (bodyBean.getType()==7){
                             Intent intent = new Intent(getActivity(), DetailActivity.class);
                             intent.putExtra("flag",1);
                             intent.putExtra("type","video");
                             intent.putExtra("url", bodyBean.getSkipID());
                             getActivity().startActivity(intent);
                         }else if (bodyBean.getType()==4||bodyBean.getType()==5||bodyBean.getType()==6){
                            Intent intent = new Intent(getActivity(), DetailActivity.class);
                            intent.putExtra("flag",1);
                            intent.putExtra("type","photo2");
                            intent.putExtra("url", bodyBean.getUrl());
                            getActivity().startActivity(intent);
                        }else{
                             Intent intent = new Intent(getActivity(), DetailActivity.class);
                             intent.putExtra("flag",0);
                            String url = bodyBean.getUrl();
                            if (url.contains("http")){
                                intent.putExtra("flag",0);
                            }else{
                                intent.putExtra("type","doc");
                                intent.putExtra("flag",1);
                            }

                            intent.putExtra("url", url);
                             getActivity().startActivity(intent);
                         }

                   }
                 }else {
                     PicBean picBean = picBeen.get(i-1);
                     Intent intent = new Intent(getActivity(), DetailActivity.class);
                     intent.putExtra("flag",1);
                     intent.putExtra("type","doc");
                     intent.putExtra("url", picBean.getReplyid());
                     getActivity().startActivity(intent);
                 }
              }
          });
        }


        return view;
    }

    public void initData() {

        bodyPresenter.start(bean);
    }

    private void initRF() {
      /*  refreshLayout.setColorSchemeColors(Color.RED, Color.GREEN, Color.BLUE);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                bodyPresenter.start(bean);
            }
        });*/
        lv.setMode(PullToRefreshBase.Mode.BOTH);
        lv.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {

                bodyPresenter.start(bean);
            /* if (bodyBeanList!=null&&bodyBeanList.size()>0){
                 if (bodyBeanList.get(0).getType()==0){
                     bodyBeanList.remove(0);
                 }
             }*/
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                page += 20;
                bean.setHasAD(page);
                bodyPresenter.start(bean);
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
                if (i == bodyBeanList.size() - 5 && nextPage == page) {
                    page += 20;
                    bean.setHasAD(page);
                    bodyPresenter.start(bean);
                }
            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void ShowBlankView(List<BodyBean> list) {
        if (list != null && list.size() > 0) {
            nextPage = page;
            lv.onRefreshComplete();
           // refreshLayout.setRefreshing(false);

            if (rvAdapter == null) {
                bodyBeanList.clear();
                bodyBeanList.addAll(list);
                if (getActivity()!=null){
                    rvAdapter = new LvAdapter(getActivity(),bodyBeanList);
                    lv.setAdapter(rvAdapter);
                }

               // rvAdapter.setRefreshListener(this);

            } else {
                bodyBeanList.addAll(list);
                rvAdapter.notifyDataSetChanged();
            }
        } else {
            page = nextPage;
           lv.onRefreshComplete();
         //   refreshLayout.setRefreshing(false);
            if (rvAdapter != null) {
                Toast.makeText(getActivity(), "已经到达最后一页", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getActivity(), "获取数据错误!", Toast.LENGTH_SHORT).show();
            }

        }

    }

    @Override
    public void ShowBlankPicView(List<PicBean> picBeanList) {
        if (picBeanList != null && picBeanList.size() > 0) {
            nextPage = page;
            lv.onRefreshComplete();
            // refreshLayout.setRefreshing(false);

            if (rvAdapter == null) {
                picBeen.clear();
                picBeen.addAll(picBeanList);
                picAdapter = new PicAdapter(getActivity(),picBeen);
                // rvAdapter.setRefreshListener(this);
                lv.setAdapter(picAdapter);
            } else {
                picBeen.addAll(picBeanList);
                picAdapter.notifyDataSetChanged();
            }
        } else {
            page = nextPage;
            lv.onRefreshComplete();
            //   refreshLayout.setRefreshing(false);
            if (picAdapter != null) {
                Toast.makeText(getActivity(), "已经到达最后一页", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getActivity(), "获取数据错误!", Toast.LENGTH_SHORT).show();
            }

        }
    }

    @Override
    public void refreshView() {
        page += 20;
        bean.setHasAD(page);
        bodyPresenter.start(bean);
        Log.d("zanZQ", "refreshView: 111111");
    }
}
