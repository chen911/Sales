package com.jaya.sales;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.j256.ormlite.dao.Dao;
import com.jaya.sales.model.Customer;
import com.jaya.sales.model.Item;
import com.jaya.sales.model.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    static EditText txtUserName;
    static EditText txtPassword;
    static Button btnLogin;

    DatabaseHelper dbHelper = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtUserName = (EditText) findViewById(R.id.txtUserName);
        txtPassword = (EditText) findViewById(R.id.txtPassword);
        btnLogin = (Button) findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                User user = null;

                try {

                    //creating sample date // testing only
                    createSampleDate();

                    Dao<User, Integer> userDao = dbHelper.getUserDao();

                    user = userDao.queryBuilder()
                            .where()
                            .eq(User.FIELD_NAME_NAME, txtUserName.getText().toString())
                            .and()
                            .gt(User.FIELD_NAME_EXPIRE_DATE, new java.util.Date()).queryForFirst();

                    if (user == null) {
                        //go to online for user data

                        User authorizedUser = null;

                        if (authorizedUser == null) {
                            //Toast.makeText(getApplicationContext(),"User not authorized!",Toast.LENGTH_SHORT).show();
                        } else {
                            //save local user data
                            //set expiry date
                            authorizedUser.setExpireDate(getNewExpiryDate());
                            userDao.update(user);
                        }

                        //save local user data // update when webservice implemented
                        user = new User();
                        user.setName(txtUserName.getText().toString());
                        user.setExpireDate(getNewExpiryDate());
                        userDao.create(user);

                        startActivity(new Intent("com.jaya.sales.DASHBOARD"));
                        Toast.makeText(getApplicationContext(), "New User Authorized!", Toast.LENGTH_SHORT).show();
                    } else {
                        //get local user data
                        startActivity(new Intent("com.jaya.sales.DASHBOARD"));
                        Toast.makeText(getApplicationContext(), "Login Success!!!", Toast.LENGTH_SHORT).show();
                    }

                    SalesApp.loggedUser = user;

                } catch (SQLException e) {

                    e.printStackTrace();
                }
            }
        });
    }

    //to get user authorization expiry date
    private Date getNewExpiryDate() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.MONTH, 1);

        return cal.getTime();
    }


    public void createSampleDate() {
        Dao<Item, Integer> itemDao = dbHelper.getItemDao();

        List<Item> itemsList = null;
        try {
            itemsList = itemDao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if(itemsList.size()<=4) {
            createItemData(itemDao);
        }

        Dao<Customer, Integer> customerDao = dbHelper.getCustomerDao();
        List<Customer> customerList = null;
        try {
            customerList = customerDao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if(customerList.size()<=3) {
            createCustomerData(customerDao);
        }
    }

    public void createCustomerData(Dao<Customer, Integer> customerDao){
        List<String[]> list    = new ArrayList<>();

        list.add(new String[]{"17","Chamidu stores - Nainamadama"});
        list.add(new String[]{"129","Welekade - Weyangoda"});
        list.add(new String[]{"A001","Zikra Distributers - Hemmathagama"});
        list.add(new String[]{"A002","Anesly Grocery - Avissawella"});
        list.add(new String[]{"A003","Akurana Stores - Akurana"});
        list.add(new String[]{"A004","Athukorala and Sons - No 01, Tire compeny junction"});
        list.add(new String[]{"A005","Apsara Traders - Panadura"});
        list.add(new String[]{"A006","Sudu Araliya - Anuradhapura"});
        list.add(new String[]{"A007","New Millewa Trade Center - Millewa"});
        list.add(new String[]{"A008","Araliya Traders - Mawanella"});
        list.add(new String[]{"C135","Cargills Foods Company (Pvt) Ltd - Colombo"});

        for(String[] custArr: list) {

            Customer customer = new Customer();
            customer.setCode(custArr[0]);
            customer.setName(custArr[1]);
            try {
                customerDao.create(customer);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void createItemData(Dao<Item, Integer> itemDao){
        List<String[]>  list    = new ArrayList<>();

        list.add(new String[]{"ALUMINIUM1","ALUMINIUM"});
        list.add(new String[]{"ANADU00001","5 KG ARALIYA NADU"});
        list.add(new String[]{"ANADU00002","10 KG ARALIYA NADU "});
        list.add(new String[]{"ANADU00003","25 KG ARALIYA NADU "});
        list.add(new String[]{"ANADU00004","50 KG ARALIYA NADU "});
        list.add(new String[]{"ARK0000001","5 KG ARALIYA RED RAW"});
        list.add(new String[]{"ARK0000002","10 KG ARALIYA RED RAW "});
        list.add(new String[]{"ARK0000003","25 KG ARALIYA RED RAW "});
        list.add(new String[]{"ARK0000004","50 KG ARALIYA RED RAW "});
        list.add(new String[]{"ARKORA0001","5 KG ARALIYA KORA"});
        list.add(new String[]{"ARKORA0002","10 KG ARALIYA KORA"});
        list.add(new String[]{"ARKORA0003","25 KG ARALIYA KORA"});
        list.add(new String[]{"ARKORA0004","50 KG ARALIYA KORA"});
        list.add(new String[]{"ARN0000001","5 KG ARALIYA RED NADU"});
        list.add(new String[]{"ARN0000002","10 KG ARALIYA RED NADU "});
        list.add(new String[]{"ARN0000003","25 KG ARALIYA RED NADU "});
        list.add(new String[]{"ARN0000004","50 KG ARALIYA RED NADU "});
        list.add(new String[]{"ARSAMBA001","5 KG ARALIYA RED SAMBA"});
        list.add(new String[]{"ARSAMBA002","10 KG ARALIYA RED SAMBA "});
        list.add(new String[]{"ARSAMBA003","25 KG ARALIYA RED SAMBA "});
        list.add(new String[]{"ARSAMBA004","50 KG ARALIYA RED SAMBA "});
        list.add(new String[]{"ARSK000001","5 KG ARALIYA SAMBA RED RAW"});
        list.add(new String[]{"ARSK000002","10 KG ARALIYA SAMBA RED RAW "});
        list.add(new String[]{"ARSK000003","25 KG ARALIYA SAMBA RED RAW "});
        list.add(new String[]{"ARSK000004","50 KG ARALIYA SAMBA RED RAW "});
        list.add(new String[]{"ARSTNADU01","5 KG ARALIYA STEAM NADU"});
        list.add(new String[]{"ARSTNADU02","10 KG ARALIYA STEAM NADU"});
        list.add(new String[]{"ARSTNADU03","25 KG ARALIYA STEAM NADU"});
        list.add(new String[]{"ARSTNADU04","50 KG ARALIYA STEAM NADU"});
        list.add(new String[]{"ASAMBA0001","5 KG ARALIYA SAMBA"});
        list.add(new String[]{"ASAMBA0002","10 KG ARALIYA SAMBA "});
        list.add(new String[]{"ASAMBA0003","25 KG ARALIYA SAMBA "});
        list.add(new String[]{"ASAMBA0004","50 KG ARALIYA SAMBA "});
        list.add(new String[]{"ASDK000001","5 KG ARALIYA WHITE RAW"});
        list.add(new String[]{"ASDK000002","10 KG ARALIYA WHITE RAW "});
        list.add(new String[]{"ASDK000003","25 KG ARALIYA WHITE RAW "});
        list.add(new String[]{"ASDK000004","50 KG ARALIYA WHITE RAW "});
        list.add(new String[]{"ASK0000001","5 KG ARALIYA RAW SAMBA"});
        list.add(new String[]{"ASK0000002","10 KG ARALIYA RAW SAMBA "});
        list.add(new String[]{"ASK0000003","25 KG ARALIYA RAW SAMBA "});
        list.add(new String[]{"ASK0000004","50 KG ARALIYA RAW SAMBA "});
        list.add(new String[]{"ASKS000001","5 KG ARALIYA SUPIRI KEERISAMBA"});
        list.add(new String[]{"ASKS000002","10 KG ARALIYA SUPIRIKEERISAMBA"});
        list.add(new String[]{"ASKS000003","25 KG ARALIYA SUPIRIKEERISAMBA"});
        list.add(new String[]{"ASKS000004","50 KG ARALIYA SUPIRIKEERISAMBA"});
        list.add(new String[]{"ASTSAMBA01","5 KG ARALIYA STEAM SAMBA"});
        list.add(new String[]{"ASTSAMBA02","10 KG ARALIYA STEAM SAMBA"});
        list.add(new String[]{"ASTSAMBA03","25 KG ARALIYA STEAM SAMBA"});
        list.add(new String[]{"ASTSAMBA04","50 KG ARALIYA STEAM SAMBA"});
        list.add(new String[]{"BATTERY0001","BATTERY"});
        list.add(new String[]{"BOL0000001","BOL"});
        list.add(new String[]{"BOLSUNUS01","BOL RICE"});
        list.add(new String[]{"BRASS000001","BRASS"});
        list.add(new String[]{"CHINACHA01","CHINACHCHATTI"});
        list.add(new String[]{"DAMAGEBAGS","DAMAGE BAGS"});
        list.add(new String[]{"DOUBLEBAGS","DOUBLE BAGS"});
        list.add(new String[]{"IRONBAR001","IRON"});
        list.add(new String[]{"IRONPLATE1","IRON PLATE"});
        list.add(new String[]{"KALUETA001","BLACK SEEDS"});
        list.add(new String[]{"LNADU00001","5 KG LAKBIMA NADU"});
        list.add(new String[]{"LNADU00002","10 KG LAKBIMA NADU"});
        list.add(new String[]{"LNADU00003","25 KG LAKBIMA NADU"});
        list.add(new String[]{"LNADU00004","50 KG LAKBIMA NADU"});
        list.add(new String[]{"LSAMBA0001","5 KG LAKBIMA SAMBA"});
        list.add(new String[]{"LSAMBA0002","10 KG LAKBIMA SAMBA"});
        list.add(new String[]{"LSAMBA0003","25 KG LAKBIMA SAMBA"});
        list.add(new String[]{"LSAMBA0004","50 KG LAKBIMA SAMBA"});
        list.add(new String[]{"LSKS000001","5 KG LAKBIMA SUPIRI KEERISAMBA"});
        list.add(new String[]{"LSKS000002","10 KG LAKBIMA SUPIRIKEERISAMBA"});
        list.add(new String[]{"LSKS000003","25 KG LAKBIMA SUPIRIKEERISAMBA"});
        list.add(new String[]{"LSKS000004","50 KG LAKBIMA SUPIRIKEERISAMBA"});
        list.add(new String[]{"NO-01SUNU1","NO 01 BROKEN RICE"});
        list.add(new String[]{"NORMALKD01","RICE BAND"});
        list.add(new String[]{"OTH0000001","OTHER ITEM"});
        list.add(new String[]{"PADDYHUSK1","PADDY HUSK"});
        list.add(new String[]{"PAHUPOLISH","PADDY HUSK POLISH"});
        list.add(new String[]{"REDNADUST1","5 KG ARALIYA ATTAKKARI"});
        list.add(new String[]{"REDNADUST2","10 KG ARALIYA ATTAKKARI"});
        list.add(new String[]{"REDNADUST3","25 KG ARALIYA ATTAKKARI"});
        list.add(new String[]{"REDNADUST4","50 KG ARALIYA ATTAKKARI"});
        list.add(new String[]{"RKEKUDU001","RED RICE POLISH"});
        list.add(new String[]{"RKEKUDU002","50 KG BROKEN RICE RED RAW"});
        list.add(new String[]{"RKEKUDU003","25 KG BROKEN RICE RED RAW"});
        list.add(new String[]{"RKSUNUS001","BROKEN RICE RED RAW"});
        list.add(new String[]{"RTHMKUDU01","RED BOIL POLISH"});
        list.add(new String[]{"SAMSUNUS01","BROKEN RICE SAMBA"});
        list.add(new String[]{"SAMSUNUS02","5 KG BROKEN RICE SAMBA "});
        list.add(new String[]{"SAMSUNUS05","50 KG BROKEN RICE SAMBA   "});
        list.add(new String[]{"SDKEKD0001","WHITE RICE POLISH"});
        list.add(new String[]{"SKSUNUS001","50 KG BROKEN RICE WHITE RAW"});
        list.add(new String[]{"SKSUNUS005","50 KG WHITE RAW BROKEN RICE"});
        list.add(new String[]{"SPR0000001","S.P.R."});
        list.add(new String[]{"SPR0000002","S.P.R. Keeri"});
        list.add(new String[]{"STAINSTE01","STAINLESS STEEL"});
        list.add(new String[]{"TUBE000001","TUBE"});
        list.add(new String[]{"TYRE000001","TYRE"});
        list.add(new String[]{"ZINC000001","ZINC"});

        for(String[] itemArr: list) {

            Item item = new Item();
            item.setCode(itemArr[0]);
            item.setName(itemArr[1]);

            try {
                itemDao.create(item);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
