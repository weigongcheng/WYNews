package com.example.asus.news.live;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asus.news.BaseActivity;
import com.example.asus.news.R;
import com.example.asus.news.live.model.LiveDetailsBean;
import com.example.asus.news.live.presenter.LiveDetailsPresenter;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.widget.MediaController;
import io.vov.vitamio.widget.VideoView;

public class LiveDetailsActivity extends BaseActivity implements IShowLiveDetailsView {


    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.bar)
    RelativeLayout bar;
    @BindView(R.id.close_web)
    ImageView closeWeb;
    @BindView(R.id.sendType)
    ImageView sendType;
    @BindView(R.id.shareOrSend)
    ImageView shareOrSend;
    @BindView(R.id.edit_keywords)
    EditText editKeywords;
    @BindView(R.id.botton)
    RelativeLayout botton;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.activity_live_details)
    LinearLayout activityLiveDetails;
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.vv)
    VideoView vv;
    @BindView(R.id.current_percent)
    TextView currentPercent;
    @BindView(R.id.net_speed)
    TextView netSpeed;
    @BindView(R.id.video_layout)
    RelativeLayout videoLayout;
    private LiveDetailsPresenter liveDetailsPresenter = new LiveDetailsPresenter(this);
    private List<String> titles = new ArrayList<>();
    private List<String> imgs = new ArrayList<>();
    private boolean isOpen = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_details);
        ButterKnife.bind(this);
        //  String url="http://data.live.126.net/liveAll/102093.json";
        String id = getIntent().getStringExtra("id");
        Log.d("zanZQ", "onCreate: " + id);
        liveDetailsPresenter.getLiveDetailsData(id);

    }

    private void initData(LiveDetailsBean liveDetailsBean) {
        titles.add("直播");
        titles.add("聊天室");
        titles.add("相关");
        imgs.add(liveDetailsBean.getBanner().getUrl());
        imgs.add(liveDetailsBean.getBanner().getLiveUrl());
        imgs.add(liveDetailsBean.getBanner().getUrl());
        LiveImgAdapter adapter = new LiveImgAdapter(imgs, titles, this);
        viewpager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewpager);
    }

    private void initView(final String url) {
        vv.setVideoURI(Uri.parse(url));
        vv.start();
        MediaController controller = new MediaController(this);
        vv.setMediaController(controller);
        //  vv.setHorizontalScrollBarEnabled(true);
        vv.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() {
            @Override
            public void onBufferingUpdate(MediaPlayer mp, int percent) {
                currentPercent.setText("已缓冲:" + percent + "%");
            }
        });
        vv.setOnInfoListener(new MediaPlayer.OnInfoListener() {
            @Override
            public boolean onInfo(MediaPlayer mp, int what, int extra) {
                switch (what) {
                    //开始缓冲
                    case MediaPlayer.MEDIA_INFO_BUFFERING_START:
                        mp.pause();
                        currentPercent.setVisibility(View.VISIBLE);
                        netSpeed.setVisibility(View.VISIBLE);
                        break;
                    //缓冲结束
                    case MediaPlayer.MEDIA_INFO_BUFFERING_END:
                        mp.start();
                        currentPercent.setVisibility(View.GONE);
                        netSpeed.setVisibility(View.GONE);
                        break;
                    //下载网速发生变化时会进入到这个分支中
                    case MediaPlayer.MEDIA_INFO_DOWNLOAD_RATE_CHANGED:
                        netSpeed.setText("当前网速:" + extra + "kb/s");
                        break;
                }
                return false;
            }
        });
    }

    @Override
    public void getLiveDetailsView(LiveDetailsBean liveDetailsBean) {


        if (liveDetailsBean != null && liveDetailsBean.getLiveVideoUrl() != null) {
            String liveVideoUrl = liveDetailsBean.getLiveVideoUrl();
            Log.d("zanZQ", "getLiveDetailsView: " + liveVideoUrl);
            initView(liveVideoUrl);
            initData(liveDetailsBean);
            title.setText(liveDetailsBean.getRoomName());
        } else {
            Toast.makeText(LiveDetailsActivity.this, "视频播放错误", Toast.LENGTH_SHORT).show();
            this.finish();
        }
    }

    @OnClick({R.id.back, R.id.close_web})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                this.finish();
                break;
            case R.id.close_web:
                if (isOpen) {
                    videoLayout.setVisibility(View.GONE);
                    vv.pause();
                    closeWeb.setImageResource(R.drawable.down);
                } else {
                    vv.start();
                    videoLayout.setVisibility(View.VISIBLE);
                    closeWeb.setImageResource(R.drawable.up);
                }
                isOpen = !isOpen;

                break;
        }
    }
}
