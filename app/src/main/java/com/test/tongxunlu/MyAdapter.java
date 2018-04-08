package com.test.tongxunlu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by zhengyanda on 2018/4/4.
 */

public class MyAdapter extends BaseAdapter {


    private List<Person> beans;

    private Context context;

    public MyAdapter(Context context, List<Person> beans) {
        this.beans = beans;
        this.context = context;
    }

    @Override
    public int getCount() {
        return beans.size();
    }

    @Override
    public Object getItem(int i) {
        return beans.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v;
        ViewHolder viewHolder;
        if (view == null){
            v = LayoutInflater.from(context).inflate(R.layout.item,null);
            viewHolder = new ViewHolder();
            viewHolder.name = v.findViewById(R.id.tv);
            v.setTag(viewHolder);
        }else{
            v = view;
            viewHolder = (ViewHolder) v.getTag();
        }
        viewHolder.name.setText(beans.get(i).getName());
        return v;
    }


    class ViewHolder{
        TextView name;
    }
}
