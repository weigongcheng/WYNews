package com.example.asus.news;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.asus.news.news.model.SearchBean;
import com.example.asus.news.news.presenter.SearchPresenter;
import com.example.asus.news.news.view.SearchRVAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SearchActivity extends BaseActivity implements IShowSearchView {

    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.code)
    ImageView code;
    @BindView(R.id.edit_keywords)
    EditText editKeywords;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.delete_words)
    ImageView deleteWords;
    @BindView(R.id.activity_search)
    RelativeLayout activitySearch;
    private SearchPresenter searchPresenter = new SearchPresenter(this);
    public SearchRVAdapter adapter;
    private List<SearchBean> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        editKeywords.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() > 0) {
                 deleteWords.setVisibility(View.VISIBLE);
                }else{
                    deleteWords.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        ;
    }

    public void search(View view) {
        String string = editKeywords.getText().toString().trim();
        if (string != null && string.length() > 0) {
            searchPresenter.SearchKeywords(string);
        } else {
            Toast.makeText(SearchActivity.this, "请检查你输入的内容是否为空", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void ShowSearchView(List<SearchBean> list) {
        this.list = list;
        if (list != null && list.size() > 0) {
            adapter = new SearchRVAdapter(list, this);
            recyclerView.setAdapter(adapter);
        //    Log.d("zanZQ", "ShowSearchView: " + adapter.getItemCount());
            for (int i = 0; i < list.size(); i++) {
           //     Log.d("zanZQ", "ShowSearchView: " + list.get(i).toString());
            }
        } else {
            Toast.makeText(SearchActivity.this, "查询失败！", Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick(R.id.back)
    public void onBackClick() {
        this.finish();
    }

    public void delete(View view) {
        editKeywords.setText("");
    }
}
