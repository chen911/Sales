package com.jaya.sales;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.j256.ormlite.dao.Dao;
import com.jaya.sales.model.Customer;
import com.jaya.sales.model.Item;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenith on 11/27/16.
 */

public class Dashboard extends Activity {

    static Button btnAddSalesOrder;
    static Button btnSearchOrders;
    static Button btnSearchCustomer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        btnAddSalesOrder = (Button) findViewById(R.id.btnAddSalesOrder);
        btnSearchOrders = (Button) findViewById(R.id.btnSearchSalesOrder);
        btnSearchCustomer = (Button) findViewById(R.id.btnSearchCustomer);

        btnAddSalesOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent("com.jaya.sales.SALESORDER"));
            }
        });

        btnSearchOrders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent("com.jaya.sales.SEARCHORDERS"));
            }
        });

        btnSearchCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent("com.jaya.sales.SEARCHCUSTOMER"));
            }
        });

        btnSearchCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent("com.jaya.sales.SEARCHCUSTOMER"));
            }
        });
    }
}
