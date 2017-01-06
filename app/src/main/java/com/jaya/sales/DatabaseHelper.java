package com.jaya.sales;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.jaya.sales.model.Customer;
import com.jaya.sales.model.Item;
import com.jaya.sales.model.SalesOrder;
import com.jaya.sales.model.SalesOrderItem;
import com.jaya.sales.model.User;


import java.sql.SQLException;

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    private static final String DATABASE_NAME    = "sales.db";
    private static final int    DATABASE_VERSION = 16;

    private Dao<User, Integer>              userDao             = null;
    private Dao<Item, Integer>              itemDao             = null;
    private Dao<Customer, Integer>          customerDao         = null;
    private Dao<SalesOrder, Integer>        salesOrderDao       = null;
    private Dao<SalesOrderItem, Integer>    salesOrderItemDao   = null;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, User.class);
            TableUtils.createTable(connectionSource, Item.class);
            TableUtils.createTable(connectionSource, Customer.class);
            TableUtils.createTable(connectionSource, SalesOrder.class);
            TableUtils.createTable(connectionSource, SalesOrderItem.class);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            TableUtils.dropTable(connectionSource, User.class, true);
            TableUtils.dropTable(connectionSource, Item.class, true);
            TableUtils.dropTable(connectionSource, Customer.class, true);
            TableUtils.dropTable(connectionSource, SalesOrder.class, true);
            TableUtils.dropTable(connectionSource, SalesOrderItem.class, true);
            onCreate(db, connectionSource);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /* User */

    public Dao<User, Integer> getUserDao(){
        if (userDao == null) {
            try {
                userDao = getDao(User.class);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return userDao;
    }

    /* Item */

    public Dao<Item, Integer> getItemDao(){
        if (itemDao == null) {
            try {
                itemDao = getDao(Item.class);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return itemDao;
    }

    /* Customer */

    public Dao<Customer, Integer> getCustomerDao() {
        if (customerDao == null) {

            try {
                customerDao = getDao(Customer.class);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return customerDao;
    }

    /* SalesOrder */

    public Dao<SalesOrder, Integer> getSalesOrderDao(){
        if (salesOrderDao == null) {
            try {
                salesOrderDao = getDao(SalesOrder.class);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return salesOrderDao;
    }

    /* SalesOrderItem */

    public Dao<SalesOrderItem, Integer> getSalesOrderItemDao(){
        if (salesOrderItemDao == null) {
            try {
                salesOrderItemDao = getDao(SalesOrderItem.class);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return salesOrderItemDao;
    }


    @Override
    public void close() {
        userDao = null;
        itemDao = null;
        customerDao = null;
        salesOrderDao = null;

        super.close();
    }

    private static DatabaseHelper sDatabaseHelper;

//    public static DatabaseHelper getInstance(Context context) {
//        if (sDatabaseHelper == null) {
//            sDatabaseHelper = new DatabaseHelper(context.getApplicationContext());
//        }
//
//        return sDatabaseHelper;
//    }

    public static DatabaseHelper getInstance() {
        return sDatabaseHelper;
    }
}