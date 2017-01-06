package com.jaya.sales.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import com.jaya.sales.R;
import com.jaya.sales.SalesApp;
import com.jaya.sales.model.Customer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by akshay on 1/2/15.
 */
public class CustomerAdapter extends ArrayAdapter<Customer> {

    Context context;
    int resource, textViewResourceId;
    List<Customer> items, tempItems, suggestions;

    public CustomerAdapter(Context context, int resource, int textViewResourceId, List<Customer> items) {
        super(context, resource, textViewResourceId, items);
        this.context = context;
        this.resource = resource;
        this.textViewResourceId = textViewResourceId;
        this.items = items;
        tempItems = new ArrayList<Customer>(items); // this makes the difference.
        suggestions = new ArrayList<Customer>();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.row_customer, parent, false);
        }
        Customer customer = items.get(position);
        if (customer != null) {
            TextView lblName = (TextView) view.findViewById(R.id.lbl_name);
            if (lblName != null)
                lblName.setText(customer.toString());
            TextView lblAddress= (TextView) view.findViewById(R.id.lbl_CustomerAddress);
            if (lblAddress != null)
                lblAddress.setText(customer.getAddressLine1());

            //updating sales order
            //SalesApp.currentSalesOrder.setCustomer(customer);
        }

        return view;
    }

    @Override
    public Filter getFilter() {
        return nameFilter;
    }

    /**
     * Custom Filter implementation for custom suggestions we provide.
     */
    Filter nameFilter = new Filter() {
        @Override
        public CharSequence convertResultToString(Object resultValue) {
            String str = ((Customer) resultValue).getName();
            return str;
        }

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            if (constraint != null) {
                suggestions.clear();
                for (Customer customer : tempItems) {
                    if (customer.getName().toLowerCase().contains(constraint.toString().toLowerCase())
                            || customer.getCode().toLowerCase().contains(constraint.toString().toLowerCase())) {
                        suggestions.add(customer);
                    }
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = suggestions;
                filterResults.count = suggestions.size();
                return filterResults;
            } else {
                return new FilterResults();
            }
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            List<Customer> filterList = (ArrayList<Customer>) results.values;
            if (results != null && results.count > 0) {
                clear();
                for (Customer Customer : filterList) {
                    add(Customer);
                    notifyDataSetChanged();
                }
            }
        }
    };
}