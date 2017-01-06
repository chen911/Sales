package com.jaya.sales.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * Created by chenith on 11/30/16.
 */
@DatabaseTable(tableName = Customer.TABLE_NAME_CUSTOMER)
public class Customer implements Serializable {

    // Fields
    public static final String TABLE_NAME_CUSTOMER = "customer";

    public static final String FIELD_NAME_ID = "_id";
    public static final String FIELD_NAME_CODE = "code";
    public static final String FIELD_NAME_NAME = "name";
    public static final String FIELD_NAME_ADDRESS_LINE_1 = "address_line_1";
    public static final String FIELD_NAME_ADDRESS_LINE_2 = "address_line_2";
    public static final String FIELD_NAME_ADDRESS_LINE_3 = "address_line_3";
    public static final String FIELD_NAME_ADDRESS_LINE_4 = "address_line_4";

    // Primary key defined as an auto generated integer
    // If the database table column name differs than the Model class variable name, the way to map to use columnName

    @DatabaseField(columnName = FIELD_NAME_ID, generatedId = true)
    private int id;

    @DatabaseField(columnName = FIELD_NAME_CODE)
    private String code;

    @DatabaseField(columnName = FIELD_NAME_NAME)
    private String name;

    @DatabaseField(columnName = FIELD_NAME_ADDRESS_LINE_1)
    private String addressLine1;

    @DatabaseField(columnName = FIELD_NAME_ADDRESS_LINE_2)
    private String addressLine2;

    @DatabaseField(columnName = FIELD_NAME_ADDRESS_LINE_3)
    private String addressLine3;

    @DatabaseField(columnName = FIELD_NAME_ADDRESS_LINE_4)
    private String addressLine4;


    // Getter and Setter method of fields

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

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getAddressLine3() {
        return addressLine3;
    }

    public void setAddressLine3(String addressLine3) {
        this.addressLine3 = addressLine3;
    }

    public String getAddressLine4() {
        return addressLine4;
    }

    public void setAddressLine4(String addressLine4) {
        this.addressLine4 = addressLine4;
    }

    public String getAddress() {
        StringBuffer stringBuffer = new StringBuffer();

        stringBuffer.append(getAddressLine1() != null ? getAddressLine1() + " " : "");
        stringBuffer.append(getAddressLine2() != null ? "\n" + getAddressLine2() : "");
        stringBuffer.append(getAddressLine3() != null ? "\n" + getAddressLine3() + " " : "");
        stringBuffer.append(getAddressLine4() != null ? "\n" + getAddressLine4() + " " : "");

        return stringBuffer.toString();
    }

    @Override
    public String toString() {

        String name = "";

        if(getCode()!=null) {
            name = getCode() + " - ";
        }

        if(getName()!=null) {
            name += getName();
        }

        return name;
    }
}