package com.jaya.sales;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.j256.ormlite.dao.Dao;
import com.jaya.sales.model.Item;
import com.jaya.sales.model.SalesOrder;
import com.jaya.sales.model.SalesOrderItem;
import com.jaya.sales.util.ItemListViewAdapter;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by chenith on 11/30/16.
 */

public class ItemActivity extends Activity {

    static TextView txtItemCode;
    static TextView txtItemName;
    static EditText txtQty;
    static Button btnCancelItem;
    static Button btnAddItem;
    static ListView itemListView;

    Item    selectedItem;
    SalesOrderItem  orderItem;
    List<Item> itemsList;

    DatabaseHelper dbHelper = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);

        txtItemCode     = (TextView) findViewById(R.id.txtItemCode);
        txtItemName     = (TextView) findViewById(R.id.txtItemName);
        txtQty          = (EditText) findViewById(R.id.txtQty);
        btnCancelItem   = (Button) findViewById(R.id.btnCancelItem);
        btnAddItem      = (Button) findViewById(R.id.btnAddItem);
        itemListView    = (ListView) findViewById(R.id.search_item_list_view);

        //final SalesOrder salesOrder  = SalesApp.currentSalesOrder;
        Dao<Item, Integer> itemDao = dbHelper.getItemDao();
        Dao<SalesOrderItem, Integer> orderItemDaoDao = dbHelper.getSalesOrderItemDao();

        final int order_id = getIntent().getIntExtra("Order_ID",0);

        if(order_id != 0)
        {
            try {
                orderItem    = orderItemDaoDao.queryForId(order_id);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if(orderItem == null)
        {
            orderItem   = new SalesOrderItem();
        }
        else {
            txtItemCode.setText(orderItem.getItem()!=null?orderItem.getItem().getCode():"");
            txtItemName.setText(orderItem.getItem()!=null?orderItem.getItem().getName():"");
            txtQty.setText(orderItem.getQty()!=null?orderItem.getQty()+"":"0");
        }


        try {
            itemsList = itemDao.queryForAll();

            //Testing only
//            if(itemsList.size()<=1) {
//                Item    item1    = new Item(); item1.setCode("A01"); item1.setName("Alcohol");
//                Item    item2    = new Item(); item2.setCode("B02"); item2.setName("Beer");
//                Item    item3    = new Item(); item3.setCode("C03"); item3.setName("CocaCola");
//                dbHelper.getItemDao().create(item1);
//                dbHelper.getItemDao().create(item2);
//                dbHelper.getItemDao().create(item3);
//
//                itemsList = itemDao.queryForAll();
//            }

            ItemListViewAdapter adapter=new ItemListViewAdapter(this, itemsList);
            itemListView.setAdapter(adapter);

            itemListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
                    int pos=position;

                    selectedItem    = itemsList.get(pos);

                    txtItemCode.setText(selectedItem.getCode());
                    txtItemName.setText(selectedItem.getName());
                }
            });
        } catch (SQLException e) {
            e.printStackTrace();
        }


        btnCancelItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnAddItem.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {

                if(selectedItem!=null) {
                    if(orderItem==null){
                        orderItem   = new SalesOrderItem();
                    }
                    orderItem.setItem(selectedItem);
                    //orderItem.setSalesOrder(salesOrder);
                }

                orderItem.setQty(Double.parseDouble(txtQty.getText().toString()));

                //Dao<SalesOrder, Integer>    salesOrderDao   = dbHelper.getSalesOrderDao();
                Dao<SalesOrderItem, Integer>    salesOrderItemDao   = dbHelper.getSalesOrderItemDao();

                try {
                    //salesOrderDao.createOrUpdate(salesOrder);
                    salesOrderItemDao.createOrUpdate(orderItem);
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                Intent  intent  = getIntent();
                intent.putExtra("OrderItem", orderItem);
                setResult(Activity.RESULT_OK, intent);

                if(order_id!=0) {
                    setResult(2, intent);
                }else {
                    setResult(3, intent);
                }

                finish();
            }
        });
    }
}
