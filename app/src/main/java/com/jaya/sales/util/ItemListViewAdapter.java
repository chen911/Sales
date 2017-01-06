package com.jaya.sales.util;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jaya.sales.R;
import com.jaya.sales.model.Item;

import java.util.List;

public class ItemListViewAdapter extends BaseAdapter{

    public List<Item> list;

    Activity activity;
    TextView txtItemCode;
    TextView txtItemName;

    public ItemListViewAdapter(Activity activity, List<Item> list){
        super();
        this.activity=activity;
        this.list=list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater=activity.getLayoutInflater();

        if(convertView == null){

            convertView=inflater.inflate(R.layout.row_item_search, null);

            txtItemCode =(TextView) convertView.findViewById(R.id.txt_item_code);
            txtItemName =(TextView) convertView.findViewById(R.id.txt_item_name);

        }

        Item Item = list.get(position);

        txtItemCode.setText(Item.getCode());
        txtItemName.setText(Item.getName());

        return convertView;
    }

}