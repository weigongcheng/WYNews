package com.example.asus.news;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.asus.news.news.model.TListBean;
import com.example.asus.news.news.model.TitleBean;
import com.example.asus.news.news.presenter.TitlePresenter;
import com.example.asus.news.util.DaoSession;
import com.example.asus.news.util.TListBeanDao;

import java.util.List;

public class WelcomeActivity extends BaseActivity implements ITitleView{

    private TListBeanDao cateDao;
     private TitlePresenter titlePresenter=new TitlePresenter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        cateDao =daoSession.getTListBeanDao();
        List<TListBean> list = cateDao.queryBuilder().list();
        if (list == null || list.size() == 0) {
            titlePresenter.start();
        }else{
            startActivity(new Intent(this, MainActivity.class));
            this.finish();
        }

    }



    @Override
    public void obtainTitleData(TitleBean titleBean) {
            if (titleBean!=null){
                List<TListBean> tList = titleBean.getTList();
                for (int i = 0; i < tList.size(); i++) {
                    TListBean tListBean = tList.get(i);
                    if (i<10){
                        tListBean.setSelected(true);
                    };
                    cateDao.insert(tListBean);
                }
            }else{
                Toast.makeText(WelcomeActivity.this, "初始化失败...", Toast.LENGTH_SHORT).show();
            }
        startActivity(new Intent(this, MainActivity.class));
        this.finish();
    }
}
