package com.huawei.android.appdovui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class AdapterTopic extends BaseAdapter {

    private Context context;
    private int layout;
    private List<Topic> danhsach;

    public AdapterTopic(Context context, int layout, List<Topic> danhsach) {
        this.context = context;
        this.layout = layout;
        this.danhsach = danhsach;
    }

    @Override
    public int getCount() {
        return danhsach.size();
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
    public View getView(int i, View view, ViewGroup viewGroup) {

        LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view=inflater.inflate(layout, null);
        Topic topic=danhsach.get(i);

        ImageView imageView=view.findViewById(R.id.anh);
        TextView textView=view.findViewById(R.id.ten);

        textView.setText(topic.getTenchude());
        imageView.setImageResource(topic.getHinhanh());
        return view;
    }
}
