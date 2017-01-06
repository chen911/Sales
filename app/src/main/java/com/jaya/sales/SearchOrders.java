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
import com.jaya.sales.model.SalesOrder;
import com.jaya.sales.util.OrderListViewAdapter;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by chenith on 11/30/16.
 */

public class SearchOrders extends Activity {
    private ListView listview;
    private List<SalesOrder> salesOrderList;
    private Button btnCancel;

    DatabaseHelper dbHelper = new DatabaseHelper(this);

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_sales_order);

        btnCancel   = (Button) findViewById(R.id.btnCancelSearchOrder);
        listview = (ListView) findViewById(R.id.search_sales_order_list_view);

        Dao<SalesOrder, Integer> salesOrderDao = dbHelper.getSalesOrderDao();

        try {
            salesOrderList = salesOrderDao.queryForAll();

            OrderListViewAdapter adapter=new OrderListViewAdapter(this, salesOrderList);
            listview.setAdapter(adapter);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id)
            {
                int pos=position+1;

                SalesOrder  salesOrder = salesOrderList.get(position);
                Intent intent = new Intent(SearchOrders.this, SalesOrderActivity.class);
                intent.putExtra("SalesOrder_ID", salesOrder.getId());
                setResult(Activity.RESULT_OK, intent);
                startActivityForResult(intent, 3);
            }

        });


        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent("com.jaya.sales.DASHBOARD"));
            }
        });

//        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Intent intent = null;
//                if(sText.equals("Game"))
//                    intent = new Intent(getBaseContext(), SalesOrderActivity.class);
//                //else if(sText.equals("Help")) ..........
//
//                if(intent != null)
//                    startActivity(intent);
//            }
//        });




    }

}
