package com.jaya.sales.util;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jaya.sales.R;
import com.jaya.sales.model.SalesOrderItem;

import java.util.List;

public class OrderItemListViewAdapter extends BaseAdapter {

    public List<SalesOrderItem> list;
    Activity activity;
    TextView txtItemCode;
    TextView txtItemName;
    TextView txtQty;

    public OrderItemListViewAdapter(Activity activity, List<SalesOrderItem> list) {
        super();
        this.activity = activity;
        this.list = list;
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

        LayoutInflater inflater = activity.getLayoutInflater();

        if (convertView == null) {

            convertView = inflater.inflate(R.layout.row_order_item, null);

            txtItemCode = (TextView) convertView.findViewById(R.id.txt_order_item_code);
            txtItemName = (TextView) convertView.findViewById(R.id.txt_order_item_name);
            txtQty = (TextView) convertView.findViewById(R.id.txt_order_item_qty);
        }

        SalesOrderItem salesOrderItem = list.get(position);

        txtItemCode.setText(salesOrderItem.getItem() != null ? salesOrderItem.getItem().getCode() : "");
        txtItemName.setText(salesOrderItem.getItem() != null ? salesOrderItem.getItem().getName() : "");
        txtQty.setText(salesOrderItem.getQty() + "");

        return convertView;
    }

}