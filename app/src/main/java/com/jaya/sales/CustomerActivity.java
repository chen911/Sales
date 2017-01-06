package com.jaya.sales;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.j256.ormlite.dao.Dao;
import com.jaya.sales.model.Customer;
import com.jaya.sales.model.SalesOrder;
import com.jaya.sales.model.User;

import java.sql.SQLException;

/**
 * Created by chenith on 11/30/16.
 */

public class CustomerActivity extends Activity {

    static EditText txtCustomerCode;
    static EditText txtCustomerName;
    static EditText txtAddress1;
    static EditText txtAddress2;
    static EditText txtAddress3;
    static EditText txtAddress4;
    static Button btnCancelCustomer;
    static Button btnSaveCustomer;

    DatabaseHelper  dbHelper    = new  DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_customer);

        findViewsById();

        final Customer customer = new Customer();

//        final SalesOrder salesOrder  = SalesApp.currentSalesOrder;

        btnCancelCustomer.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                clearFields();

                Intent  intent  = new Intent();

                setResult(1,intent);

                finish();
            }
        });

        btnSaveCustomer.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {

                try {
                    Dao<Customer, Integer> customerDao = dbHelper.getCustomerDao();

                    customer.setCode(txtCustomerCode.getText().toString());
                    customer.setName(txtCustomerName.getText().toString());
                    customer.setAddressLine1(txtAddress1.getText().toString());
                    customer.setAddressLine2(txtAddress2.getText().toString());
                    customer.setAddressLine3(txtAddress3.getText().toString());
                    customer.setAddressLine4(txtAddress4.getText().toString());

//                    if(salesOrder!=null)
//                    {
//                        salesOrder.setCustomer(customer);
//                    }

                    customerDao.createOrUpdate(customer);
                    Toast.makeText(getApplicationContext(), "Customer record updated!", Toast.LENGTH_SHORT).show();

                } catch (SQLException e) {
                    e.printStackTrace();
                }

                Intent  intent  = new Intent();

                intent.putExtra("Customer", customer);

                setResult(1,intent);

                finish();//finishing activity

            }
        });
    }


    private void findViewsById() {
        txtCustomerCode     = (EditText) findViewById(R.id.txtCustomerCode);
        txtCustomerName     = (EditText) findViewById(R.id.txtCustomerName);
        txtAddress1         = (EditText) findViewById(R.id.txtAddress1);
        txtAddress2         = (EditText) findViewById(R.id.txtAddress2);
        txtAddress3         = (EditText) findViewById(R.id.txtAddress3);
        txtAddress4         = (EditText) findViewById(R.id.txtAddress4);
        btnCancelCustomer   = (Button) findViewById(R.id.btnCancelCustomer);
        btnSaveCustomer     = (Button) findViewById(R.id.btnAddCustomer);
    }


    private void clearFields() {
        txtCustomerCode     = null;
        txtCustomerName     = null;
        txtAddress1         = null;
        txtAddress2         = null;
        txtAddress3         = null;
        txtAddress4         = null;
    }
}
