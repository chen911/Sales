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

@DatabaseTable(tableName = SalesOrderItem.TABLE_NAME_SALES_ORDER_ITEM)
public class SalesOrderItem implements Serializable {

    public static final String TABLE_NAME_SALES_ORDER_ITEM   = "sales_order_Item";

    public static final String FIELD_NAME_ID            = "_id";
    public static final String FIELD_NAME_SALES_ORDER   = "sales_order";
    public static final String FIELD_NAME_ITEM          = "item";
    public static final String FIELD_NAME_QTY           = "qty";

    // Primary key defined as an auto generated integer
    // If the database table column name differs than the Model class variable name, the way to map to use columnName

    @DatabaseField(columnName = FIELD_NAME_ID, generatedId = true)
    private int id;

    @DatabaseField(columnName = FIELD_NAME_SALES_ORDER, foreign = true, foreignAutoCreate = true,foreignAutoRefresh = true)
    private SalesOrder salesOrder;

    @DatabaseField(columnName = FIELD_NAME_ITEM, foreign = true, foreignAutoCreate = true,foreignAutoRefresh = true)
    private Item item;

    @DatabaseField(columnName = FIELD_NAME_QTY)
    private Double qty;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public SalesOrder getSalesOrder() {
        return salesOrder;
    }

    public void setSalesOrder(SalesOrder salesOrder) {
        this.salesOrder = salesOrder;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Double getQty() {
        return qty;
    }

    public void setQty(Double qty) {
        this.qty = qty;
    }
}
