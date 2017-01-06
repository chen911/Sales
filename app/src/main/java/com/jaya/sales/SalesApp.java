package com.jaya.sales;


import android.app.Application;

import com.jaya.sales.model.User;

public class SalesApp extends Application {

    //add this variable declaration:
    public static User loggedUser;
//    public static SalesOrder currentSalesOrder;

    private static SalesApp singleton;

    public static SalesApp getInstance() {
        return singleton;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        singleton = this;
    }
}