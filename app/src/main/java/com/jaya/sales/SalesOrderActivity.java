package com.jaya.sales;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.j256.ormlite.dao.Dao;
import com.jaya.sales.model.Customer;
import com.jaya.sales.model.SalesOrder;
import com.jaya.sales.model.SalesOrderItem;
import com.jaya.sales.model.User;
import com.jaya.sales.util.CustomerAdapter;
import com.jaya.sales.util.OrderItemListViewAdapter;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by chenith on 11/30/16.
 */

public class SalesOrderActivity extends Activity implements   View.OnClickListener {

    private EditText txtOrderDate;
    private EditText txtReqDate;
    private TextView lblName;
    private TextView lblAddress;

    AutoCompleteTextView autoCompleteCustomer;

    static Button btnAddCustomer;
    static Button btnAddItem;
    static Button btnCancelSalesOrder;
    static Button btnSaveSalesOrder;

    private DatePickerDialog orderDatePickerDialog;
    private DatePickerDialog reqDatePickerDialog;

    private SimpleDateFormat dateFormatter;

    DatabaseHelper dbHelper = new DatabaseHelper(this);

    SalesOrder salesOrder;

    User    user    = SalesApp.loggedUser;

    List<Customer> mList;
    CustomerAdapter customerAdapter;
    OrderItemListViewAdapter orderItemAdapter;
    ListView orderItemsListView;
    List<SalesOrderItem> orderItems = new ArrayList<>();

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales_order);
        findViewsById();
        Dao<SalesOrder, Integer> salesOrderDao = dbHelper.getSalesOrderDao();
        dateFormatter = new SimpleDateFormat("dd-MMM-yyyy", Locale.US);
        setDateTimeField();

        // customer autocomplete
        autoCompleteCustomer.setThreshold(1);
        mList   =   getAllCustomers();
        customerAdapter = new CustomerAdapter(this, R.layout.activity_sales_order, R.id.lbl_name, mList);
        autoCompleteCustomer.setAdapter(customerAdapter);

        final int salesOrder_id = getIntent().getIntExtra("SalesOrder_ID",0);

        if(salesOrder_id != 0)
        {
            try {
                salesOrder    = salesOrderDao.queryForId(salesOrder_id);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if(salesOrder == null) {
            salesOrder  = new SalesOrder();
        }
        else {
            updateSalesOrderData();
        }

        //Order items data
        orderItemAdapter    = new OrderItemListViewAdapter(this, orderItems);
        orderItemsListView.setAdapter(orderItemAdapter);

        orderItemsListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id)
            {
                SalesOrderItem  orderItem = orderItems.get(position);
                Intent intent = new Intent(SalesOrderActivity.this, ItemActivity.class);
                intent.putExtra("Order_ID", orderItem.getId());
                setResult(Activity.RESULT_OK, intent);
                startActivityForResult(intent, 3);
            }

        });

        btnAddCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SalesOrderActivity.this, CustomerActivity.class);
                startActivityForResult(intent, 1);
            }
        });

        btnAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SalesOrderActivity.this, ItemActivity.class);
                startActivityForResult(intent, 2);
            }
        });

        btnCancelSalesOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                salesOrder  = null;
//                SalesApp.currentSalesOrder  = null;
                Toast.makeText(getApplicationContext(),"Sales Order cancelled!", Toast.LENGTH_SHORT).show();

                startActivity(new Intent("com.jaya.sales.DASHBOARD"));
            }
        });

        btnSaveSalesOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                prepareOrderToSave();

                try {
                    Dao<SalesOrder, Integer> salesOrderDao = dbHelper.getSalesOrderDao();
                    salesOrderDao.create(salesOrder);
                    Toast.makeText(getApplicationContext(),"Sales Order created!", Toast.LENGTH_SHORT).show();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                startActivity(new Intent("com.jaya.sales.DASHBOARD"));
            }
        });
    }

    private void updateSalesOrderData()   {
        if(salesOrder.getOrderDate()!=null) {
            txtOrderDate.setText(dateFormatter.format(salesOrder.getOrderDate()));
        }

        if(salesOrder.getRequestDate()!=null) {
            txtReqDate.setText(dateFormatter.format(salesOrder.getOrderDate()));
        }

        if(salesOrder.getCustomer()!=null)
        {

            if (lblName != null)
                lblName.setText(salesOrder.getCustomer().toString());

            if (lblAddress != null)
                lblAddress.setText(salesOrder.getCustomer().getAddressLine1());
        }

        orderItems  = salesOrder.getSalesOrderItems() != null
                            ? new ArrayList<>(salesOrder.getSalesOrderItems())
                            : new ArrayList<SalesOrderItem>();
    }

    private List<Customer> getAllCustomers() {
        try {
            return dbHelper.getCustomerDao().queryForAll();
        } catch (Exception e) {
            System.out.println(e);
        }

        return null;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {
            Customer    customer = (Customer) data.getSerializableExtra("Customer");

            if(customer!=null) {
                salesOrder.setCustomer(customer);
//                autoCompleteCustomer.setText(customer.getName());
                lblAddress.setText(customer.getAddress());
            }
        }
        else if (requestCode == 2 || requestCode == 3) {
            try {

                if (data.getSerializableExtra("OrderItem") != null) {

                    SalesOrderItem orderItem = (SalesOrderItem) data.getSerializableExtra("OrderItem");
                    orderItem.setSalesOrder(salesOrder);
                    if (!orderItems.contains(orderItem)) {
                        orderItems.add(orderItem);
                    }

                    orderItemAdapter.notifyDataSetChanged();
                    orderItemsListView.invalidate();
                }
            }
            catch (Exception ex)
            {
                //error
            }
        }
        else if(requestCode == 3) {
            orderItemAdapter.list = orderItems;
            orderItemAdapter.notifyDataSetChanged();
            orderItemsListView.invalidate();
        }
    }

    private void prepareOrderToSave(){
        salesOrder.setUser(user);
        salesOrder.setOrderDate(getDateFromString(txtOrderDate.getText().toString()));
        salesOrder.setRequestDate(getDateFromString(txtReqDate.getText().toString()));
//        salesOrder.setCustomer(autoCompleteCustomer.getAdapter().);
    }

    private void findViewsById() {

        lblName = (TextView) findViewById(R.id.lbl_name);
        lblAddress = (TextView) findViewById(R.id.lbl_CustomerAddress);

        btnAddCustomer = (Button) findViewById(R.id.btnAddCustomer);
        btnAddItem = (Button) findViewById(R.id.btnAddItem);
        btnCancelSalesOrder = (Button) findViewById(R.id.btnCancelSalesOrder);
        btnSaveSalesOrder = (Button) findViewById(R.id.btnSaveSalesOrder);

        txtOrderDate = (EditText) findViewById(R.id.txtOrderDate);
        txtOrderDate.setInputType(InputType.TYPE_NULL);
        txtOrderDate.requestFocus();

        txtReqDate = (EditText) findViewById(R.id.txtReqDate);
        txtReqDate.setInputType(InputType.TYPE_NULL);

        autoCompleteCustomer = (AutoCompleteTextView) findViewById(R.id.txtCustomer);

        orderItemsListView = (ListView) findViewById(R.id.search_order_items_list);
    }

    private void setDateTimeField() {
        txtOrderDate.setOnClickListener(this);
        txtReqDate.setOnClickListener(this);

        Calendar newCalendar = Calendar.getInstance();
        orderDatePickerDialog = new DatePickerDialog(this, new OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                txtOrderDate.setText(dateFormatter.format(newDate.getTime()));
            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        reqDatePickerDialog = new DatePickerDialog(this, new OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                txtReqDate.setText(dateFormatter.format(newDate.getTime()));
            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.layout.activity_sales_order, menu);
        return true;
    }

    @Override
    public void onClick(View view) {
        if (view == txtOrderDate) {
            orderDatePickerDialog.show();
        } else if (view == txtReqDate) {
            reqDatePickerDialog.show();
        }
    }

    /**
     *
     * @param datePicker
     * @return a java.util.Date
     */
    public Date getDateFromDatePicket(DatePicker datePicker){
        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth();
        int year =  datePicker.getYear();

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);

        return calendar.getTime();
    }

    public Date getDateFromString(String text){
        if(text==null) {
            return null;
        }

        Date startDate  = null;
        DateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        try {
            startDate = df.parse(text);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return startDate;
    }
}