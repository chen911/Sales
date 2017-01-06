package com.jaya.sales.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * Created by chenith on 11/30/16.
 */

@DatabaseTable(tableName = Item.TABLE_NAME_ITEM)
public class Item implements Serializable {

    public static final String TABLE_NAME_ITEM  = "item";

    public static final String FIELD_NAME_ID    = "_id";
    public static final String FIELD_NAME_CODE  = "code";
    public static final String FIELD_NAME_NAME  = "name";
    public static final String FIELD_NAME_QIH   = "qih";


    @DatabaseField(columnName = FIELD_NAME_ID, generatedId = true)
    private int id;

    @DatabaseField(columnName = FIELD_NAME_CODE)
    private String code;

    @DatabaseField(columnName = FIELD_NAME_NAME)
    private String name;

    @DatabaseField(columnName = FIELD_NAME_QIH)
    private String qih;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQih() {
        return qih;
    }

    public void setQih(String qih) {
        this.qih = qih;
    }
}
