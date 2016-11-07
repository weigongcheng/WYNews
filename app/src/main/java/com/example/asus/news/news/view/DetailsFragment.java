package com.example.asus.news.news.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.asus.news.BaseFragment;
import com.example.asus.news.MyViewPager;
import com.example.asus.news.R;
import com.example.asus.news.live.view.HotFragment;
import com.example.asus.news.news.model.TListBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ASUS on 2016/10/31.
 */

public class DetailsFragment extends BaseFragment {
    @BindView(R.id.plus)
    ImageView plus;
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.viewpager)
    MyViewPager viewpager;
    private View view;
    private List<Fragment> fragmentList;
    private FgAdapter adapter;

    public void setList(List<TListBean> list) {
        if (this.list!=null){

            this.list = list;
            fragmentList.clear();
            for (int i = 0; i <list.size(); i++) {
                if (list.get(i).getTname().equals("直播")){
                    fragmentList.add(new HotFragment());
                }else  if (list.get(i).getTname().equals("视频")){
                    fragmentList.add(new BlankSpFragment());
                }
                else{
                    fragmentList.add(BlankFragment.getInstance(list.get(i)));
                }
            }
        //    Log.d("zanZQ", "setList: "+list.size()+","+fragmentList.size());
            adapter.notifyDataSetChanged();
         tabLayout.setScrollPosition(viewpager.getCurrentItem(),0,false);
        }else{
            this.list = list;
         //   Log.d("zanZQ", "setList: "+list.size());
        }


    }

    private List<TListBean> list;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_news_main, container, false);
            ButterKnife.bind(this, view);
            initView();
        }
        return view;
    }

    private void initView() {
        fragmentList=new ArrayList<>();
        for (int i = 0; i <list.size(); i++) {
            if (list.get(i).getTname().equals("直播")){
                fragmentList.add(new HotFragment());
            }else  if (list.get(i).getTname().equals("视频")){
                fragmentList.add(new BlankSpFragment());
            }else{
                fragmentList.add(BlankFragment.getInstance(list.get(i)));
            }

          //  Log.d("zanZQ", "initView: "+list.get(i).getTid());
        }
        adapter = new FgAdapter(getChildFragmentManager(), fragmentList, list);
        viewpager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewpager);

        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
           onClickDownMore.openMoreFragment();
            }
        });
    }
    private  OnClickDownMore onClickDownMore;

    public void setOnClickDownMore(OnClickDownMore onClickDownMore) {
        this.onClickDownMore = onClickDownMore;
    }

    public interface OnClickDownMore{
        void openMoreFragment();

    }
}
