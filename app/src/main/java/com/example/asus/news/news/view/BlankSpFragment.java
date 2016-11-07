package com.example.asus.news.news.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import com.example.asus.news.BaseFragment;
import com.example.asus.news.DetailActivity;
import com.example.asus.news.R;
import com.example.asus.news.news.model.GVAdapter;
import com.example.asus.news.news.model.SearchBean;
import com.example.asus.news.news.model.SpBean;
import com.example.asus.news.news.presenter.SpPresenter;
import com.handmark.pulltorefresh.library.PullToRefreshGridView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ASUS on 2016/11/5.
 */

public class BlankSpFragment extends BaseFragment implements IShowSpDataView {
    View view;
    @BindView(R.id.grid_view)
    PullToRefreshGridView gridView;
    private SpPresenter spPresenter=new SpPresenter(this);
    List<SpBean.视频Bean> been;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (view == null) {
            view = inflater.inflate(R.layout.blank_fg_layout_sp, container, false);
            ButterKnife.bind(this, view);
         spPresenter.getData();
            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Intent intent = new Intent(getActivity(), DetailActivity.class);
                    intent.putExtra("flag",1);
                    SpBean.视频Bean bean = been.get(i);
                    intent.putExtra("type","video");
                    intent.putExtra("url", bean.getVid());
                    getActivity().startActivity(intent);
                }
            });
        }

        return view;
    }

    @Override
    public void getShowSpDataView(SpBean spBean) {
     if (spBean!=null){
         been = spBean.get视频();
         GVAdapter gvAdapter = new GVAdapter(getActivity(), been);
         gridView.setAdapter(gvAdapter);
     }else{
         Toast.makeText(getActivity(), "视频加载失败", Toast.LENGTH_SHORT).show();
     }
    }
}
