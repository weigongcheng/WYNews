package com.example.asus.news.news.view;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.ViewGroup;

import com.example.asus.news.live.view.HotFragment;
import com.example.asus.news.news.model.TListBean;

import java.util.List;

/**
 * Created by ASUS on 2016/10/31.
 */

public class FgAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragments;
    private List<TListBean> list;

    public FgAdapter(FragmentManager fm, List<Fragment> fragments, List<TListBean> list) {
        super(fm);
        this.fragments = fragments;
        this.list = list;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        if (list.get(position).getTname().equals("直播")){
            HotFragment hotFragment = (HotFragment) super.instantiateItem(container, position);
            return hotFragment;
        }else  if (list.get(position).getTname().equals("视频")){
            BlankSpFragment blankSpFragment = (BlankSpFragment) super.instantiateItem(container, position);
            return blankSpFragment;
        }else{
            BlankFragment fragment = (BlankFragment) super.instantiateItem(container, position);
            return fragment;
        }

      //  TListBean bean = list.get(position);
      //  bean.setHasAD(0);
      //  fragment.initData(bean);

    }

 /*   @Override
    public Object instantiateItem(ViewGroup container, int position) {
        BlankFragment fragment = (BlankFragment) fragments.get(position);
         fragment.initData();
        return fragment;
    }*/

    @Override
    public CharSequence getPageTitle(int position) {
        return list.get(position).getTname();
    }

    @Override
    public int getItemPosition(Object object) {
        return PagerAdapter.POSITION_NONE;
    }
}
