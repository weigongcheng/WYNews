package com.example.asus.news;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.asus.news.live.view.ClassifyFragment;
import com.example.asus.news.live.view.HotFragment;
import com.example.asus.news.live.view.LiveFgAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ASUS on 2016/10/30.
 */

public class LiveFragment extends BaseFragment {
    public View view;
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.viewpager)
    MyViewPager viewpager;
 private List<String> titles=new ArrayList<>();
    private List<Fragment> fragments=new ArrayList<>();
    public ClassifyFragment classifyFg;
    public HotFragment hotFg;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_live, container, false);
            ButterKnife.bind(this, view);
            initView();
        }

        return view;
    }

    private void initView() {
        titles.add("热门");
        titles.add("分类");
        hotFg = new HotFragment();
        fragments.add(hotFg);
        classifyFg = new ClassifyFragment();
        fragments.add(classifyFg);

        LiveFgAdapter adapter = new LiveFgAdapter(getChildFragmentManager(), titles, fragments);
        viewpager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewpager);

    }
}
