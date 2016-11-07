package com.example.asus.news.live.view;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.asus.news.R;
import com.example.asus.news.live.model.FutureBean;

import java.util.List;

/**
 * Created by ASUS on 2016/11/3.
 */

public class TextVpAdapter extends PagerAdapter {
    private List<FutureBean> list;
    private Context context;

    public TextVpAdapter(List<FutureBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return object==view;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        TextView textView = new TextView(context);
        textView.setText(list.get(position%list.size()).getRoomName());
        textView.setTextSize(15);
        textView.setGravity(Gravity.CENTER);
        textView.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        textView.setFocusable(true);
        textView.setFocusableInTouchMode(true);
        textView.setSingleLine(true);
        container.addView(textView);
        return textView;
    }
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
