package com.example.asus.news;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.example.asus.news.news.model.TListBean;
import com.example.asus.news.news.view.DetailsFragment;
import com.example.asus.news.news.view.MoreFragment;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by ASUS on 2016/10/30.
 */

public class NewFragment extends BaseFragment {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.frame_layout)
    FrameLayout frameLayout;
    @BindView(R.id.iv_search)
    ImageView ivSearch;
    @BindView(R.id.live_iv)
    ImageView liveIv;
    private View view;
    private Fragment currentFragment;
    private DetailsFragment detailsFragment;
    private MoreFragment moreFragment;


    public void setList(List<TListBean> list) {
        Log.d("zanZQ", "setLista132a: " + list.size());

        this.list = list;
    }

    private List<TListBean> list;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_news, container, false);
            ButterKnife.bind(this, view);
            initView();
        }


        return view;

    }

    private void initView() {
        detailsFragment = new DetailsFragment();
        currentFragment = detailsFragment;
        detailsFragment.setList(list);
        detailsFragment.setOnClickDownMore(((MainActivity) getActivity()));
        showFragment(detailsFragment, false);
    }

    private void showFragment(Fragment fragment, boolean withAnimation) {
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();

        if (!fragment.isAdded()) {
            ft.add(R.id.frame_layout, fragment);
        }
        if (withAnimation == true) {
            if (fragment instanceof DetailsFragment) {

                ft.setCustomAnimations(0, R.anim.exit_upward);
            } else {
                ft.setCustomAnimations(R.anim.enter_downward, 0);
            }
        }
        ft.hide(currentFragment).show(fragment);
        ft.commit();
        currentFragment = fragment;
    }

    public void openMore() {
        if (moreFragment == null) {
            moreFragment = new MoreFragment();
            moreFragment.setMoreFragmentOnclick((MoreFragment.MoreFragmentOnclick) getActivity());
            //  currentFragment = moreFragment;
        }
        showFragment(moreFragment, true);
    }

    public void CloseMore(List<TListBean> list) {
        showFragment(detailsFragment, true);
        detailsFragment.setList(list);
        Log.d("zanZQ", "CloseMore: " + list.size());
    }

    @OnClick(R.id.iv_search)
    public void onSearchClick() {
        Intent intent = new Intent(getContext(), SearchActivity.class);
        getContext().startActivity(intent);
    }

    @OnClick(R.id.live_iv)
    public void onClick() {
        onNewsClick.OnLive_ivClick();
    }
    public  interface OnNewsClick{
        void OnLive_ivClick();
    }
    private OnNewsClick onNewsClick;

    public void setOnNewsClick(OnNewsClick onNewsClick) {
        this.onNewsClick = onNewsClick;
    }
}
