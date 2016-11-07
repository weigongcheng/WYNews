package com.example.asus.news.news.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.asus.news.BaseActivity;
import com.example.asus.news.BaseFragment;
import com.example.asus.news.R;
import com.example.asus.news.news.model.TListBean;
import com.example.asus.news.util.TListBeanDao;
import com.google.android.flexbox.FlexboxLayout;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ASUS on 2016/10/31.
 */

public class MoreFragment extends BaseFragment {
    public View view;
    @BindView(R.id.close_more)
    ImageView closeMore;
    @BindView(R.id.already_exists_fb)
    FlexboxLayout alreadyExistsFb;
    @BindView(R.id.more_fb)
    FlexboxLayout moreFb;
    private TListBeanDao cateDao;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
          if (view == null) {
            view = inflater.inflate(R.layout.fragment_news_more, container, false);
            ButterKnife.bind(this, view);
        }
        initData();
        return view;
    }

    private void initData() {
        cateDao = ((BaseActivity) getActivity()).daoSession.getTListBeanDao();
        List<TListBean> alreadyList = cateDao.queryBuilder().where(TListBeanDao.Properties.IsSelected.eq(true)).list();
        for (int i = 0; i < alreadyList.size(); i++) {
            TListBean tListBean = alreadyList.get(i);
            final TextView tv1 = initTv(tListBean);
            alreadyExistsFb.addView(tv1);
        }
        List<TListBean> moreList = cateDao.queryBuilder().where(TListBeanDao.Properties.IsSelected.eq(false)).list();
        for (int i = 0; i < moreList.size(); i++) {
            final TextView tv1 = initTv(moreList.get(i));
            moreFb.addView(tv1);
        }
        closeMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                moreFragmentOnclick.closeMoreFg();
            }
        });
    }
    private TextView initTv(final TListBean tListBean) {
        final TextView tv = new TextView(getActivity());
        final String text = tListBean.getTname();
        FlexboxLayout.LayoutParams lp = new FlexboxLayout.LayoutParams(getResources().getDisplayMetrics().widthPixels/5,getResources().getDisplayMetrics().widthPixels/12);
        lp.topMargin=lp.bottomMargin=lp.leftMargin=lp.rightMargin = getResources().getDisplayMetrics().widthPixels/5/5/2;
   /*     lp.leftMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 9, getResources().getDisplayMetrics());
        lp.rightMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 9, getResources().getDisplayMetrics());
        lp.bottomMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, getResources().getDisplayMetrics());
   */     tv.setLayoutParams(lp);
        tv.setPadding(
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, getResources().getDisplayMetrics()),
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4, getResources().getDisplayMetrics()),
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, getResources().getDisplayMetrics()),
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4, getResources().getDisplayMetrics())
        );
        tv.setGravity(Gravity.CENTER);
        tv.setText(text);
        if (tListBean.isSelected()){
            tv.setBackgroundResource(R.drawable.fb_tv_bg_gray);
        }else{
            tv.setBackgroundResource(R.drawable.fb_tv_bg);
        }

        if (!"头条".equals(tv.getText().toString())) {
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    TListBean bean = cateDao.queryBuilder().where(TListBeanDao.Properties.Tname.eq(text)).unique();
                    TextView textView = initTv(bean);
                    if (bean.getIsSelected()) {
                        alreadyExistsFb .removeView(view);
                        textView.setBackgroundResource(R.drawable.fb_tv_bg);
                        moreFb.addView(textView);

                    } else {
                        moreFb.removeView(view);
                        textView.setBackgroundResource(R.drawable.fb_tv_bg_gray);
                        alreadyExistsFb.addView(textView);
                    }
                    bean.setIsSelected(!bean.getIsSelected());
                    cateDao.update(bean);
                }
            });
        }
        return tv;
    }
    public interface MoreFragmentOnclick{
        void closeMoreFg();
    }
    private MoreFragmentOnclick moreFragmentOnclick;

    public void setMoreFragmentOnclick(MoreFragmentOnclick moreFragmentOnclick) {
        this.moreFragmentOnclick = moreFragmentOnclick;
    }
}
