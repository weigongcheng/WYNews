package com.example.asus.news;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.asus.news.live.view.LiveFgAdapter;
import com.example.asus.news.topic.view.GzFragment;
import com.example.asus.news.topic.view.HtFragment;
import com.example.asus.news.topic.view.WbFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by ASUS on 2016/10/30.
 */

public class TopicFragment extends BaseFragment {
    View view;
    @BindView(R.id.user)
    ImageView user;
    @BindView(R.id.search_iv)
    ImageView searchIv;
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.viewpager)
    MyViewPager viewpager;
    private List<String> titles = new ArrayList<>();
    private List<Fragment> fragments = new ArrayList<>();
    WbFragment wbFg;
    HtFragment htFg;
    GzFragment gzFg;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {

            view = inflater.inflate(R.layout.fragment_topic, container, false);
            ButterKnife.bind(this, view);
            initView();
        }


        return view;


    }

    private void initView() {
        titles.add("问吧");
        titles.add("话题");
        titles.add("关注");
        wbFg = new WbFragment();
        fragments.add(wbFg);
        htFg = new HtFragment();
        fragments.add(htFg);
        gzFg = new GzFragment();
        fragments.add(gzFg);
        LiveFgAdapter adapter = new LiveFgAdapter(getChildFragmentManager(), titles, fragments);
        viewpager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewpager);
    }

    @OnClick(R.id.search_iv)
    public void onClick() {
        Intent intent = new Intent(getContext(), SearchActivity.class);
        getContext().startActivity(intent);
    }
}
