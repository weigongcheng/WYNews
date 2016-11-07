package com.example.asus.news;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by ASUS on 2016/10/30.
 */

public class MineFragment extends BaseFragment {
    @BindView(R.id.tv)
    TextView tv;
    @BindView(R.id.iv_right)
    ImageView ivRight;
    @BindView(R.id.login)
    LinearLayout login;
    @BindView(R.id.head_layout)
    LinearLayout headLayout;
    @BindView(R.id.mine_layout)
    CardView mineLayout;
    @BindView(R.id.mine_like)
    CardView mineLike;
    @BindView(R.id.msg_iv)
    ImageView msgIv;
    @BindView(R.id.msg_iv2)
    ImageView msgIv2;
    @BindView(R.id.msg_iv3)
    ImageView msgIv3;
    @BindView(R.id.msg_iv4)
    ImageView msgIv4;
    @BindView(R.id.msg_iv5)
    ImageView msgIv5;
    @BindView(R.id.msg_iv6)
    ImageView msgIv6;
    @BindView(R.id.msg_iv7)
    ImageView msgIv7;
    @BindView(R.id.msg_iv8)
    ImageView msgIv8;
    @BindView(R.id.msg)
    LinearLayout msg;
    @BindView(R.id.login_btn)
    Button loginBtn;
    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.textView)
    TextView textView;
    @BindView(R.id.wx_login)
    LinearLayout wxLogin;
    @BindView(R.id.wb_layout)
    LinearLayout wbLayout;
    @BindView(R.id.qq_login)
    LinearLayout qqLogin;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mine, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick({R.id.login_btn, R.id.wx_login, R.id.wb_layout, R.id.qq_login})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login_btn:
               getActivity().startActivity( new Intent(getActivity(),LoginActivity.class));
                break;
            case R.id.wx_login:
                break;
            case R.id.wb_layout:
                break;
            case R.id.qq_login:
                break;
        }
    }
}
