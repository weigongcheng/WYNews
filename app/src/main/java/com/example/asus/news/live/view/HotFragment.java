package com.example.asus.news.live.view;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asus.news.BaseActivity;
import com.example.asus.news.BaseFragment;
import com.example.asus.news.MyListView;
import com.example.asus.news.R;
import com.example.asus.news.live.ForeshowActivity;
import com.example.asus.news.live.LiveDetailsActivity;
import com.example.asus.news.live.model.FLMainBean;
import com.example.asus.news.live.model.FutureBean;
import com.example.asus.news.live.model.HotMainFirstBean;
import com.example.asus.news.live.model.LiveReviewBean;
import com.example.asus.news.live.presenter.ClassifyPresenter;
import com.example.asus.news.util.FutureBeanDao;
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

public class HotFragment extends BaseFragment implements IShowHotDataView {
    public View view;
    View head;
    @BindView(R.id.lv)
    PullToRefreshListView lv;

    private ViewPager viewPager;
    private TextView titles;
    private ImageView iv_type;
    private RelativeLayout play_right;
    private TextView count;
    private ViewPager titleViewpager;
    private List<String> Urllist = new ArrayList<>();
    private List<HotMainFirstBean.TopBean> top;
    private FlMainAdapter mainAdapter;
    private List<LiveReviewBean> live_review=new ArrayList<>();
    private int nextPage = 1;
    private  int page=1;
    private ClassifyPresenter classifyPresenter = new ClassifyPresenter(this);
    private FutureBeanDao cateDao;
    private boolean isRunning=true;
    private List<FutureBean> FutureBeanList=new ArrayList<>();
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
      if (isRunning){
          if (msg.what == 0) {
              titleViewpager.setCurrentItem(titleViewpager.getCurrentItem() + 1);
              handler.sendEmptyMessageDelayed(0, 6000);
          }
          if (msg.what == 1) {
              viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
              handler.sendEmptyMessageDelayed(1, 5000);
          }
      }
        }
    };
    LinearLayout dot_layout;
    private int prePosition = 0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_live_fl, container, false);
            ButterKnife.bind(this, view);
            head = inflater.inflate(R.layout.fragment_live_fl_head, null);

            initView();
            classifyPresenter.getHotMainData(nextPage + "");
            cateDao = ((BaseActivity) getActivity()).daoSession.getFutureBeanDao();
            FutureBeanList = cateDao.queryBuilder().list();
            initLvView();
        }
        return view;

    }

    private void initLvView() {
        lv.setMode(PullToRefreshBase.Mode.BOTH);
        lv.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                classifyPresenter.getHotMainData(nextPage + "");
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                classifyPresenter.getHotMainData(nextPage + "");
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
                if (i == live_review.size() - 3 && nextPage == page) {
                    page += 1;
                    classifyPresenter.getHotMainData(nextPage + "");
                }
            }
        });
    }

    private void initView() {
        viewPager = ((ViewPager) head.findViewById(R.id.viewpager));
        titles = ((TextView) head.findViewById(R.id.titles));
        iv_type = ((ImageView) head.findViewById(R.id.iv_type));
        play_right = ((RelativeLayout) head.findViewById(R.id.play_right));
        count = ((TextView) head.findViewById(R.id.count));
        titleViewpager = ((ViewPager) head.findViewById(R.id.titleViewpager));
        dot_layout = ((LinearLayout) head.findViewById(R.id.dot_layout));
        lv.getRefreshableView().addHeaderView(head);
        play_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().startActivity(new Intent(getActivity(), ForeshowActivity.class));
            }
        });
    }


    @Override
    public void getDataView(Object object) {
        if (object != null) {
            if (object instanceof HotMainFirstBean) {
                initFirstDataView((HotMainFirstBean) object);
            } else {
                FLMainBean mainBean = (FLMainBean) object;
                List<LiveReviewBean> review = mainBean.getLive_review();
                this.live_review.addAll(review);
                mainAdapter.notifyDataSetChanged();
               page= nextPage = mainBean.getNextPage();
                lv.onRefreshComplete();
            }
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Intent intent = new Intent(getActivity(), LiveDetailsActivity.class);
                    LiveReviewBean liveReviewBean = live_review.get(i - 2);
                    Log.d("zanZQ", "onItemClick: " + liveReviewBean.getRoomName()+i);
                    intent.putExtra("id", liveReviewBean.getRoomId() + "");
                    getActivity().startActivity(intent);
                }
            });
        } else {
            page = nextPage;
            lv.onRefreshComplete();
            if (mainAdapter!=null){
                Toast.makeText(getActivity(), "已经到达最后一页", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(getActivity(), "获取数据错误!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void initFirstDataView(HotMainFirstBean object) {
        HotMainFirstBean firstBean = object;
        top = firstBean.getTop();
        List<Integer> tt=new ArrayList<>();
        for (int i = 0; i < top.size(); i++) {
            Urllist.add(top.get(i).getImage());
            tt.add(top.get(i).getRoomId());
        }
        VpImgAdapter adapter = new VpImgAdapter(Urllist, tt, getActivity(),null);
        HotMainFirstBean.TopBean data = top.get(0);
        if (data.getLiveStatus() == 0) {
            iv_type.setImageResource(R.drawable.night_a5v);
        } else if (data.getLiveStatus() == 1) {
            iv_type.setImageResource(R.drawable.night_a5y);
        } else {
            iv_type.setImageResource(R.drawable.night_a61);
        }
        titles.setText(data.getRoomName());
        viewPager.setAdapter(adapter);
        initData();
        viewPager.setCurrentItem((Integer.MAX_VALUE / 2) - ((Integer.MAX_VALUE / 2) % Urllist.size()));
        handler.sendEmptyMessage(1);
        FutureBeanList = firstBean.getFuture();
        count.setText(FutureBeanList.size() + "场");
        TextVpAdapter textVpAdapter = new TextVpAdapter(FutureBeanList, getActivity());
        titleViewpager.setAdapter(textVpAdapter);
        handler.sendEmptyMessage(0);
        cateDao.deleteAll();
        for (int i = 0; i < FutureBeanList.size(); i++) {
            cateDao.insert(FutureBeanList.get(i));
        }
        live_review = firstBean.getLive_review();
        mainAdapter = new FlMainAdapter(getActivity(), live_review);
        lv.setAdapter(mainAdapter);
        nextPage = firstBean.getNextPage();
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                position = position % Urllist.size();
                HotMainFirstBean.TopBean data = top.get(position);
                if (data.getLiveStatus() == 0) {
                    iv_type.setImageResource(R.drawable.night_a5v);
                } else if (data.getLiveStatus() == 1) {
                    iv_type.setImageResource(R.drawable.night_a5y);
                } else {
                    iv_type.setImageResource(R.drawable.night_a61);
                }
                titles.setText(data.getRoomName());
                dot_layout.getChildAt(prePosition).setEnabled(false);
                dot_layout.getChildAt(position).setEnabled(true);
                prePosition = position;

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initData() {

        for (int i = 0; i < Urllist.size(); i++) {
            //创建一个新的ImageView
            View view = new View(getActivity());
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                    (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 5, getResources().getDisplayMetrics()),
                    (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 5, getResources().getDisplayMetrics()));
            lp.leftMargin = 10;
            //设置view的宽高左边距等参数
            view.setLayoutParams(lp);
            //默认情况下所有设置View的所有属性为false
            view.setEnabled(false);
            //给view设置背景选择器，enable属性为true时背景为红色，否则为白色
            view.setBackgroundResource(R.drawable.dot_bg);
            //将View添加到容器中
            dot_layout.addView(view);
        }
        //设置第一个View的enable为true，则该View  背景为红色
        dot_layout.getChildAt(0).setEnabled(true);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        isRunning=false;
    }
}
