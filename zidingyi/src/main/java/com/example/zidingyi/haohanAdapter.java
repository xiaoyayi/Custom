package com.example.zidingyi;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.zidingyi.bean.haohan;

import java.util.ArrayList;

/**
 * Created by 小亚 on 2017/8/15.
 * 完成效果使用的是,判断当前首字母和上一个条目的是否一致,不一致就显示全部界面,一致就隐藏第一个界面
 */

class haohanAdapter extends BaseAdapter {
    private ArrayList<haohan> persons;
    private Context context;
    public haohanAdapter(ArrayList<haohan> persons, Context context) {
        this.persons=persons;
        this.context=context;
    }

    @Override
    public int getCount() {
        return persons.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        View view;
        if (convertView==null){
            view=View.inflate(context, R.layout.activity_item,null);

        }else{
            view=convertView;
        }
        TextView tv_index=(TextView)view.findViewById(R.id.tv_index);
        TextView tv_name=(TextView)view.findViewById(R.id.tv_name);
        haohan haohan=persons.get(position);
        //当前首字母
        String currentstr=haohan.getPinyin().charAt(0)+"";
        String indexstr = null;
        //如果是第一个名字,直接显示
        if (position==0){
            indexstr=currentstr;
        }else{
            //拿到当前首字母,上面一个条目的首字母,判断当前首字母和上一条首字母是否一致,不一致显示完整item
            String laststr=persons.get(position-1).getPinyin().charAt(0)+"";
            //当前首字母和上面一个条目的首字母,判断两个参数是否一致,不一致就执行赋值逻辑
            if (!TextUtils.equals(laststr,currentstr)) {
                //不一致时赋值indexstr
                indexstr = currentstr;
            }
        }
        tv_index.setVisibility(indexstr !=null ?View.VISIBLE:View.GONE);
        tv_index.setText(currentstr);
        tv_name.setText(haohan.getName());

        return view;
    }
}
