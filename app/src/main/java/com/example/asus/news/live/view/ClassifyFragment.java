package com.example.asus.news.live.view;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asus.news.BaseActivity;
import com.example.asus.news.BaseFragment;
import com.example.asus.news.MyListView;
import com.example.asus.news.R;
import com.example.asus.news.live.LiveDetailsActivity;
import com.example.asus.news.live.model.FLHeadBean;
import com.example.asus.news.live.model.FLMainBean;
import com.example.asus.news.live.model.LiveReviewBean;
import com.example.asus.news.live.presenter.ClassifyPresenter;
import com.example.asus.news.util.FLHeadBeanDao;
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

public class ClassifyFragment extends BaseFragment implements IShowLiveFlView {
    public View view;
    @BindView(R.id.lv)
    PullToRefreshListView lv;

    private ClassifyPresenter classifyPresenter = new ClassifyPresenter(this);
    private FLHeadBeanDao cateDao;
    private FlexboxLayout head_layout;
    private List<FLHeadBean> list;
    private FlMainAdapter adapter;
    private View head;
    private FLMainBean flMainBean;
    List<LiveReviewBean> live_review=new ArrayList<>();
    private int page=1;
    private int nextPage =1;
    private int id=1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_live_fl, container, false);
            ButterKnife.bind(this, view);
            head = inflater.inflate(R.layout.head, null);
            head_layout = ((FlexboxLayout) head.findViewById(R.id.head_layout));
            lv.getRefreshableView().addHeaderView(head);
            initLv();
        }
        cateDao = ((BaseActivity) getActivity()).daoSession.getFLHeadBeanDao();
        list = cateDao.queryBuilder().where(FLHeadBeanDao.Properties.Visible.eq(true)).list();
        if (list == null || list.size() == 0) {
            classifyPresenter.getHeadData();
        } else {
            head_layout.removeAllViews();
            for (int i = 0; i < list.size(); i++) {
                FLHeadBean flHeadBean = list.get(i);
                final TextView tv1 = initTv(flHeadBean);
                head_layout.addView(tv1);
            }
            if (adapter != null) {
                adapter.notifyDataSetChanged();
            }
        }

        ButterKnife.bind(this, view);
        return view;


    }

    private void initLv() {
        lv.setMode(PullToRefreshBase.Mode.BOTH);
        lv.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                classifyPresenter.getMainData(id + "", page + "");
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                page += 1;
                classifyPresenter.getMainData(id + "", page + "");
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
                    classifyPresenter.getMainData(id + "", page + "");
                }
            }
        });
    }

    @Override
    public void getHeadView(List<FLHeadBean> list) {
        this.list = list;
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                FLHeadBean bean = list.get(i);
                cateDao.insert(bean);
                if (bean.getVisible()) {
                    final TextView tv1 = initTv(bean);
                    head_layout.addView(tv1);
                }
            }
            if (adapter != null) {
                adapter.notifyDataSetChanged();
            }
        } else {
            Toast.makeText(getActivity(), "请检查网络", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void getMainView(FLMainBean Bean) {
        if (Bean != null) {
            nextPage=page;
            lv.onRefreshComplete();
            flMainBean = Bean;
            live_review.addAll(flMainBean.getLive_review());
            if (adapter == null) {
                adapter = new FlMainAdapter(getActivity(), live_review);
                lv.setAdapter(adapter);
                //  Log.d("zanZQ", "getMainView: 1111111");
            } else {
              adapter.notifyDataSetChanged();
            }
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Intent intent = new Intent(getActivity(), LiveDetailsActivity.class);
                    LiveReviewBean liveReviewBean = live_review.get(i - 1);
                    Log.d("zanZQ", "onItemClick: " + liveReviewBean.getRoomName());
                    intent.putExtra("id", liveReviewBean.getRoomId() + "");
                    getActivity().startActivity(intent);
                }
            });

        } else {
            page = nextPage;
            lv.onRefreshComplete();
            if (adapter!=null){
                Toast.makeText(getActivity(), "已经到达最后一页", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(getActivity(), "获取数据错误!", Toast.LENGTH_SHORT).show();
            }
        }

    }

    private TextView initTv(final FLHeadBean bean) {
        final TextView tv = new TextView(getActivity());
        FlexboxLayout.LayoutParams lp = new FlexboxLayout.LayoutParams(getResources().getDisplayMetrics().widthPixels / 6, getResources().getDisplayMetrics().widthPixels / 12);
        lp.topMargin = lp.bottomMargin = lp.leftMargin = lp.rightMargin = getResources().getDisplayMetrics().widthPixels / 8 / 7 / 2;
        // lp.leftMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 5, getResources().getDisplayMetrics());
        // lp.rightMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 5, getResources().getDisplayMetrics());
        //  lp.bottomMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, getResources().getDisplayMetrics());
        tv.setLayoutParams(lp);
        tv.setPadding(
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, getResources().getDisplayMetrics()),
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4, getResources().getDisplayMetrics()),
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, getResources().getDisplayMetrics()),
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4, getResources().getDisplayMetrics())
        );
        tv.setGravity(Gravity.CENTER);
        String text = bean.getName();
        tv.setText(text);
        if (text.equals("TOP100")) {
            tv.setBackgroundResource(R.drawable.fg_tv_bg3);
            tv.setTextColor(Color.WHITE);
            id=bean.getId();
            classifyPresenter.getMainData(id + "", page + "");
        } else {
            tv.setTextColor(Color.BLACK);
            tv.setBackgroundResource(R.drawable.fg_tv_bg2);
        }


        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String string = tv.getText().toString();
                for (int i = 0; i < list.size(); i++) {
                    TextView childAt = (TextView) head_layout.getChildAt(i);
                    if (childAt.getText().toString().equals(string)) {
                        childAt.setBackgroundResource(R.drawable.fg_tv_bg3);
                        childAt.setTextColor(Color.WHITE);
                        live_review.clear();
                         id = list.get(i).getId();
                          page=1;
                        classifyPresenter.getMainData(id + "", page + "");
                    } else {
                        childAt.setTextColor(Color.BLACK);
                        childAt.setBackgroundResource(R.drawable.fg_tv_bg2);
                    }
                }
            }
        });
        return tv;
    }
}
