package com.jaya.sales.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by chenith on 11/30/16.
 */
@DatabaseTable(tableName = User.TABLE_NAME_USER)
public class User implements Serializable {

    // Fields
    public static final String TABLE_NAME_USER = "user";

    public static final String FIELD_NAME_ID = "_id";
    public static final String FIELD_NAME_NAME = "name";
    public static final String FIELD_NAME_TOKEN = "token";
    public static final String FIELD_NAME_EXPIRE_DATE = "expire_date";

    // Primary key defined as an auto generated integer
    // If the database table column name differs than the Model class variable name, the way to map to use columnName

    @DatabaseField(columnName = FIELD_NAME_ID, generatedId = true)
    private int id;

    @DatabaseField(columnName = FIELD_NAME_NAME)
    private String name;

    @DatabaseField(columnName = FIELD_NAME_TOKEN)
    private String token;

    @DatabaseField(columnName = FIELD_NAME_EXPIRE_DATE)
    private Date expireDate;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }
}