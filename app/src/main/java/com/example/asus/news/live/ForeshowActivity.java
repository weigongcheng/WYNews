package com.example.asus.news.live;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageView;

import com.example.asus.news.BaseActivity;
import com.example.asus.news.R;
import com.example.asus.news.live.model.FutureBean;
import com.example.asus.news.live.model.TimeFutureBean;
import com.example.asus.news.live.view.ElvAdapter;
import com.example.asus.news.util.FutureBeanDao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ForeshowActivity extends BaseActivity {

    @BindView(R.id.close)
    ImageView close;
    @BindView(R.id.refresh)
    ImageView refresh;
    @BindView(R.id.elv)
    ExpandableListView elv;
    private FutureBeanDao cateDao;
    private List<FutureBean> futureBeanList;
    List<TimeFutureBean> timeFutureBeanList;
    ElvAdapter elvAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foreshow);
        ButterKnife.bind(this);
        initData();
        elvAdapter = new ElvAdapter(timeFutureBeanList, this);
        elv.setAdapter(elvAdapter);
        for (int i = 0; i < timeFutureBeanList.size(); i++) {
            elv.expandGroup(i);
        }
        elv.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {
                Intent intent = new Intent(ForeshowActivity.this, LiveDetailsActivity.class);
                FutureBean futureBean = timeFutureBeanList.get(i).getList().get(i1);
                Log.d("zanZQ", "onItemClick: " + futureBean.getRoomName());
                intent.putExtra("id", futureBean.getRoomId() + "");
                startActivity(intent);
                return true;
            }
        });

    }

    private void initData() {
        cateDao = daoSession.getFutureBeanDao();
        futureBeanList = cateDao.queryBuilder().list();
        HashMap<String, List<FutureBean>> map = new HashMap<>();
        for (int i = 0; i < futureBeanList.size(); i++) {
            String startTime = futureBeanList.get(i).getStartTime();
            String time = startTime.substring(0, 10);
            if (!map.containsKey(time)) {
                List<FutureBean> flist = new ArrayList<>();
                flist.add(futureBeanList.get(i));
                map.put(time, flist);
            } else {
                List<FutureBean> beanList = map.get(time);
                beanList.add(futureBeanList.get(i));
                map.put(time, beanList);
            }
        }
        timeFutureBeanList = new ArrayList<>();
        for (String a : map.keySet()) {
            TimeFutureBean bean = new TimeFutureBean(a, map.get(a));
            timeFutureBeanList.add(bean);
        }
    }

    @OnClick({R.id.close, R.id.refresh})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.close:
                this.finish();
                break;
            case R.id.refresh:
                initData();
                elvAdapter.notifyDataSetChanged();
                break;
        }
    }
}
