package com.yneed.yneed;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by 咸味 on 2016/2/27.
 */
public class MyLvAdapter extends BaseAdapter {
    private List<ItemBean>mList;
    private LayoutInflater mInflater;

    public MyLvAdapter(Context context, List<ItemBean>list) {
        mList = list;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = mInflater.inflate(R.layout.item,null);
        ImageView imageView = (ImageView) view.findViewById(R.id.iv_image);
        TextView title = (TextView) view.findViewById(R.id.tv_title);
        TextView content = (TextView) view.findViewById(R.id.tv_content);
        ItemBean bean = mList.get(position);
        imageView.setImageResource(mList.get(position).ItemImageResid);
        title.setText(bean.ItemTitle);
        content.setText(bean.ItemContent);
        return view;
    }
}
