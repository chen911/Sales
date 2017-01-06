package com.jaya.sales.model;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by chenith on 11/30/16.
 */

@DatabaseTable(tableName = SalesOrder.TABLE_NAME_SALES_ORDER)
public class SalesOrder implements Serializable{

    public static final String TABLE_NAME_SALES_ORDER       = "sales_order";

    public static final String FIELD_NAME_ID                = "_id";
    public static final String FIELD_NAME_ORDER_NUMBER      = "order_number";
    public static final String FIELD_NAME_ORDER_DATE        = "order_date";
    public static final String FIELD_NAME_REQUEST_DATE      = "request_date";
    public static final String FIELD_NAME_ORDER_STATUS      = "order_status";
    public static final String FIELD_NAME_NOTES             = "notes";
    public static final String FIELD_NAME_CUSTOMER          = "customer";
    public static final String FIELD_NAME_USER              = "user";
    public static final String FIELD_NAME_SALES_ORDER_ITEMS = "sales_order_items";

    // Primary key defined as an auto generated integer
    // If the database table column name differs than the Model class variable name, the way to map to use columnName

    @DatabaseField(columnName = FIELD_NAME_ID, generatedId = true)
    private int id;

    @DatabaseField(columnName = FIELD_NAME_ORDER_NUMBER)
    private String orderNumber;

    @DatabaseField(columnName = FIELD_NAME_ORDER_DATE)
    private Date orderDate;

    @DatabaseField(columnName = FIELD_NAME_REQUEST_DATE)
    private Date requestDate;

    @DatabaseField(columnName = FIELD_NAME_ORDER_STATUS)
    private String orderStatus;

    @DatabaseField(columnName = FIELD_NAME_NOTES)
    private String notes;

    // One-to-one
    @DatabaseField(columnName = FIELD_NAME_CUSTOMER, foreign = true, foreignAutoCreate = true,foreignAutoRefresh = true)
    private Customer customer;

    @DatabaseField(columnName = FIELD_NAME_USER, foreign = true, foreignAutoCreate = true,foreignAutoRefresh = true)
    private User user;

    // One-to-many
    @ForeignCollectionField(columnName = FIELD_NAME_SALES_ORDER_ITEMS, eager = true)
    private ForeignCollection<SalesOrderItem> salesOrderItems;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Date getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public ForeignCollection<SalesOrderItem> getSalesOrderItems() {
        return salesOrderItems;
    }

    public void setSalesOrderItems(ForeignCollection<SalesOrderItem> salesOrderItems) {
        this.salesOrderItems = salesOrderItems;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
