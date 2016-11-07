package com.example.asus.news.live.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asus.news.R;
import com.example.asus.news.live.model.FutureBean;
import com.example.asus.news.live.model.TimeFutureBean;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.BindView;

/**
 * Created by ASUS on 2016/11/3.
 */

public class ElvAdapter extends BaseExpandableListAdapter {
    @BindView(R.id.time)
    TextView time;
    private List<TimeFutureBean> list;
    private Context context;
    private LayoutInflater inflater;
   private SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public ElvAdapter(List<TimeFutureBean> list, Context context) {
        this.list = list;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getGroupCount() {
        return list.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return list.get(i).getList().size();
    }

    @Override
    public Object getGroup(int i) {
        return list.get(i).getDay();
    }

    @Override
    public Object getChild(int i, int i1) {
        return list.get(i).getList().get(i1);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
       GroupViewHolder holder;
        if (view == null) {
            view = inflater.inflate(R.layout.elv_group, viewGroup, false);
            holder=new GroupViewHolder();
            holder.time= ((TextView) view.findViewById(R.id.time));
            view.setTag(holder);
        }else{
        holder= (GroupViewHolder) view.getTag();
        }
        String day = list.get(i).getDay();
        holder.time.setText(day);
        return view;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
    final ChildViewHolder holder;
        if (view==null){
            view=  inflater.inflate(R.layout.elv_child,viewGroup,false);
         holder=new ChildViewHolder();
            holder.child_time= (TextView) view.findViewById(R.id.child_time);
            holder.title= (TextView) view.findViewById(R.id.title);
            holder.type= (ImageView) view.findViewById(R.id.type);
            view.setTag(holder);
        }else{
            holder= (ChildViewHolder) view.getTag();
        }
        FutureBean futureBean = list.get(i).getList().get(i1);
        holder.title.setText(futureBean.getRoomName());
        String day = futureBean.getStartTime();
        holder.child_time.setText(day.substring(day.lastIndexOf(":")-5,day.lastIndexOf(":")));

            holder.type.setImageResource(R.drawable.time);
        holder.type.setTag(true);
            holder.type.setBackground(context.getResources().getDrawable(R.drawable.time_bg));
            holder.type.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (((boolean) holder.type.getTag())){
                        holder.type.setImageResource(R.drawable.time_gray);
                        holder.type.setBackground(context.getResources().getDrawable(R.drawable.time_bg_gray));
                        Toast.makeText(context, "已添加提醒", Toast.LENGTH_SHORT).show();
                        holder.type.setTag(false);
                    }else{
                        holder.type.setImageResource(R.drawable.time);
                        holder.type.setBackground(context.getResources().getDrawable(R.drawable.time_bg));
                        Toast.makeText(context, "已取消提醒", Toast.LENGTH_SHORT).show();
                        holder.type.setTag(true);
                    }

                }
            });



        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }
    class GroupViewHolder{
        TextView time;
    }class ChildViewHolder{
        ImageView type;
        TextView child_time,title;
    }
}