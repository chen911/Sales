package com.jaya.sales.util;
import java.util.List;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jaya.sales.R;
import com.jaya.sales.model.Customer;
import com.jaya.sales.model.SalesOrder;

public class ListViewAdapter extends BaseAdapter{

    public List<Customer> list;

    Activity activity;
    TextView txtCustomerCode;
    TextView txtCustomerName;
    TextView txtCustomerAddress;

    public ListViewAdapter(Activity activity, List<Customer> list){
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

            convertView=inflater.inflate(R.layout.row_customer_search, null);

            txtCustomerCode     =(TextView) convertView.findViewById(R.id.txt_customer_code_tv);
            txtCustomerName     =(TextView) convertView.findViewById(R.id.txt_customer_name_tv);
            txtCustomerAddress  =(TextView) convertView.findViewById(R.id.txt_customer_address_tv);

        }

        Customer customer = list.get(position);

        txtCustomerCode.setText(customer.getCode());
        txtCustomerName.setText(customer.getName());
        txtCustomerAddress.setText(customer.getAddress());

        return convertView;
    }

}