package com.example.asus.news;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.asus.news.news.model.TListBean;
import com.example.asus.news.news.view.DetailsFragment;
import com.example.asus.news.news.view.MoreFragment;
import com.example.asus.news.util.TListBeanDao;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements MoreFragment.MoreFragmentOnclick
        , DetailsFragment.OnClickDownMore ,NewFragment.OnNewsClick{

    @BindView(R.id.rg_button)
    RadioGroup rgButton;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.news)
    RadioButton news;
    @BindView(R.id.live)
    RadioButton live;
    @BindView(R.id.topic)
    RadioButton topic;
    @BindView(R.id.mine)
    RadioButton mine;
    private NewFragment newFragment;
    private MineFragment mineFragment;
    private LiveFragment liveFragment;
    private TopicFragment topicFragment;
    private List<Fragment> fragments;
    private List<TListBean> list = new ArrayList<>();
    private TListBeanDao cateDao;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initView();
    }

    @Override
    protected void onResume() {
        super.onResume();
      setTabLayoutData();
    }

    private void setTabLayoutData() {
        list.clear();
        list.addAll(cateDao.queryBuilder().where(TListBeanDao.Properties.IsSelected.eq(true)).list());
        Log.d("zanZQ", "setTabLayoutData: "+list.size());
        newFragment.setList(list);
    }

    private void initView() {
        cateDao = daoSession.getTListBeanDao();
        fragments = new ArrayList<>();
        newFragment = new NewFragment();
        mineFragment = new MineFragment();
        liveFragment = new LiveFragment();
        topicFragment = new TopicFragment();
        fragments.add(newFragment);
        fragments.add(liveFragment);
        fragments.add(topicFragment);
        fragments.add(mineFragment);
        newFragment.setOnNewsClick(this);
        VpAdapter adapter = new VpAdapter(getSupportFragmentManager(), fragments);
        viewpager.setAdapter(adapter);
        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i < rgButton.getChildCount(); i++) {
                    RadioButton radioButton = (RadioButton) rgButton.getChildAt(i);
                    if (i == position) {
                        radioButton.setChecked(true);
                        radioButton.setTextAppearance(MainActivity.this, R.style.MainTab_Checked);
                    } else {
                        radioButton.setChecked(false);
                        radioButton.setTextAppearance(MainActivity.this, R.style.MainTab);
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        rgButton.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.news:
                        viewpager.setCurrentItem(0);
                        break;
                    case R.id.live:
                        viewpager.setCurrentItem(1);
                        break;
                    case R.id.topic:
                        viewpager.setCurrentItem(2);
                        break;
                    case R.id.mine:
                        viewpager.setCurrentItem(3);
                        break;
                }
            }
        });
    }

    @Override
    public void closeMoreFg() {

        rgButton.setVisibility(View.VISIBLE);
        TranslateAnimation translateAnimation = new TranslateAnimation(TranslateAnimation.RELATIVE_TO_SELF, 0,
                TranslateAnimation.RELATIVE_TO_SELF, 0, TranslateAnimation.RELATIVE_TO_SELF, 1, TranslateAnimation.RELATIVE_TO_SELF, 0);
        translateAnimation.setDuration(300);
        translateAnimation.setFillAfter(true);
        news.startAnimation(translateAnimation);
        live.startAnimation(translateAnimation);
        topic.startAnimation(translateAnimation);
        mine.startAnimation(translateAnimation);
        list.clear();
        list.addAll(cateDao.queryBuilder().where(TListBeanDao.Properties.IsSelected.eq(true)).list());
        newFragment.CloseMore(list);

    }

    @Override
    public void openMoreFragment() {
        rgButton.setVisibility(View.GONE);
        newFragment.openMore();
    }

    @Override
    public void OnLive_ivClick() {
        viewpager.setCurrentItem(1);
    }
}
