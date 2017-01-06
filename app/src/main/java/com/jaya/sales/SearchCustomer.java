package com.jaya.sales;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.j256.ormlite.dao.Dao;
import com.jaya.sales.model.Customer;
import com.jaya.sales.util.ListViewAdapter;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by chenith on 12/4/16.
 */


public class SearchCustomer extends Activity {

    DatabaseHelper dbHelper = new DatabaseHelper(this);
    private ListView customerListview;
    private List<Customer> customerList;
    private Button btnCancel;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_customer);

        btnCancel = (Button) findViewById(R.id.btnCancelSearchCustomer);
        customerListview = (ListView) findViewById(R.id.search_customer_list_view);

        Dao<Customer, Integer> customerDao = dbHelper.getCustomerDao();

        try {
            customerList = customerDao.queryForAll();

            ListViewAdapter adapter = new ListViewAdapter(this, customerList);
            customerListview.setAdapter(adapter);

            customerListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
                    int pos = position + 1;
                    Toast.makeText(SearchCustomer.this, Integer.toString(pos) + " Clicked", Toast.LENGTH_SHORT).show();
                }

            });
        } catch (SQLException e) {
            e.printStackTrace();
        }

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent("com.jaya.sales.DASHBOARD"));
            }
        });


    }
}
