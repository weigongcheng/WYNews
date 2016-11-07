package com.example.asus.news;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asus.news.news.model.ISearchDataImpl;
import com.example.asus.news.news.presenter.WebViewPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.widget.VideoView;

public class DetailActivity extends BaseActivity implements IShowUrlView {
    @BindView(R.id.vv)
    VideoView vv;
    @BindView(R.id.current_percent)
    TextView currentPercent;
    @BindView(R.id.net_speed)
    TextView netSpeed;
    @BindView(R.id.video_layout)
    RelativeLayout videoLayout;
    @BindView(R.id.activity_detail)
    RelativeLayout activityDetail;
    private String live = " http://c.3g.163.com/nc/video/detail/%s.html "; //搜索视频VC3RQ9L86
    private String text = " http://c.3g.163.com/nc/article/%s/full.html";// 搜索C4MR3CTP041601D4
    private String img = "http://c.m.163.com/photo/api/set/%s/%s.json";  //图片0001 2208738

    @BindView(R.id.webview)
    WebView webview;
    public String url;
    private WebViewPresenter webviewpresenter = new WebViewPresenter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        int flag = intent.getIntExtra("flag", 0);
        if (flag == 0) {
            url = intent.getStringExtra("url");
            initView(url);
        } else if (flag == 1) {
            String type = intent.getStringExtra("type");
            switch (type) {
                case "doc":
                    String skipType1 = intent.getStringExtra("url");
                    url = String.format(text, skipType1);
                    webviewpresenter.getUrl(url, ISearchDataImpl.doc, skipType1);
                    break;
                case "video":
                    String skipType2 = intent.getStringExtra("url");
                    url = String.format(live, skipType2);
                    webviewpresenter.getUrl(url, ISearchDataImpl.video, skipType2);
                    break;
                case "photoset":
                    String skipType3 = intent.getStringExtra("url");
                    String a = skipType3.substring(skipType3.lastIndexOf("|") - 4, skipType3.lastIndexOf("|"));
                    String b = skipType3.substring(skipType3.lastIndexOf("|") + 1, skipType3.length());
                    url = String.format(img, a, b);
                    webviewpresenter.getUrl(url, ISearchDataImpl.photoset, skipType3);
                    break;
                case "photo2":
                    url = intent.getStringExtra("url");
                    webviewpresenter.getUrl(url, ISearchDataImpl.photoset, null);
                    break;
            }
        }
        Log.d("zanZQ", "onCreate: " + url);
        if (url != null && url.trim().length() > 0) {

        } else {
            this.finish();
        }

    }

    private void initView(final String url) {
        webview.setVisibility(View.VISIBLE);
        videoLayout.setVisibility(View.GONE);
        WebSettings settings = webview.getSettings();
        settings.setJavaScriptEnabled(true);
        webview.loadUrl(url);

        webview.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                view.loadUrl(url);
                return true;
            }

        });
        webview.setWebChromeClient(new WebChromeClient() {
            //获取网页标题
            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                Log.d("zan", "onReceivedTitle: " + title);
                ;
            }

        });
    }

    @Override
    public void getShowUrlWebView(String url,int urlType) {
        if (url != null) {
    //  url="http://112.253.22.157/17/z/z/y/u/zzyuasjwufnqerzvyxgkuigrkcatxr/hc.yinyuetai.com/D046015255134077DDB3ACA0D7E68D45.flv";
         if (urlType==ISearchDataImpl.video){
          initVideoView(url);
             Log.d("zanZQ", "getShowUrlWebView11111: "+url);
         }else{
             initView(url);
             Log.d("zanZQ", "getShowUrlWebView2222: "+url);
         }
        } else {
            Toast.makeText(DetailActivity.this, "无法访问！", Toast.LENGTH_SHORT).show();
            SystemClock.sleep(1000);
            this.finish();
        }
    }

    private void initVideoView(String url) {
        webview.setVisibility(View.GONE);
        videoLayout.setVisibility(View.VISIBLE);
        vv.setVideoURI(Uri.parse(url));
        vv.start();
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
}
