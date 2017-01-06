package com.jaya.sales.util;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jaya.sales.R;
import com.jaya.sales.model.SalesOrder;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class OrderListViewAdapter extends BaseAdapter {

    public List<SalesOrder> list;

    Activity activity;
    TextView txtOderId;
    TextView txtOrderDate;
    TextView txtCustomerCode;
    TextView txtCustomerName;

    public OrderListViewAdapter(Activity activity, List<SalesOrder> list) {
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

            convertView = inflater.inflate(R.layout.row_order_search, null);

            txtOderId = (TextView) convertView.findViewById(R.id.txt_order_id);
            txtOrderDate = (TextView) convertView.findViewById(R.id.txt_order_date);
            txtCustomerCode = (TextView) convertView.findViewById(R.id.txt_customer_code);
            txtCustomerName = (TextView) convertView.findViewById(R.id.txt_customer_name);
        }

        SalesOrder salesOrder = list.get(position);

        txtOderId.setText(salesOrder.getUser() != null ? salesOrder.getUser().getName() : "");
        txtOrderDate.setText(getDateString(salesOrder.getOrderDate()));
        txtCustomerCode.setText(salesOrder.getCustomer() != null ? salesOrder.getCustomer().getCode() : "");
        txtCustomerCode.setText(salesOrder.getCustomer() != null ? salesOrder.getCustomer().getName() : "");

        return convertView;
    }

    private String getDateString(Date date) {
        if (date == null) {
            return "";
        }

        return new SimpleDateFormat("dd/MMM/yyyy").format(date);
    }
}