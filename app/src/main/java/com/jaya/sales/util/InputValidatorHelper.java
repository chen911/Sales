package com.jaya.sales.util;

import android.text.TextUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by chenith on 12/4/16.
 */

public class InputValidatorHelper {
    public boolean isValidEmail(String string) {
        final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(string);
        return matcher.matches();
    }

    public boolean isValidPassword(String string, boolean allowSpecialChars) {
        String PATTERN;
        if (allowSpecialChars) {
            //PATTERN = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})";
            PATTERN = "^[a-zA-Z@#$%]\\w{5,19}$";
        } else {
            //PATTERN = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{6,20})";
            PATTERN = "^[a-zA-Z]\\w{5,19}$";
        }


        Pattern pattern = Pattern.compile(PATTERN);
        Matcher matcher = pattern.matcher(string);
        return matcher.matches();
    }

    public boolean isNullOrEmpty(String string) {
        return TextUtils.isEmpty(string);
    }

    public boolean isNumeric(String string) {
        return TextUtils.isDigitsOnly(string);
    }

    //Add more validators here if necessary

//    InputValidatorHelper inputValidatorHelper = new InputValidatorHelper();
//    StringBuilder errMsg = new StringBuilder("Unable to save. Please fix the following errors and try again.\n");
//    //Validate and Save
//    boolean allowSave = true;
//    if (user.getEmail() == null && !inputValidatorHelper.isValidEmail(user_email)) {
//        errMsg.append("- Invalid email address.\n");
//        allowSave = false;
//    }
//
//    if (inputValidatorHelper.isNullOrEmpty(user_first_name)) {
//        errMsg.append("- First name should not be empty.\n");
//        allowSave = false;
//    }
//
//    if(allowSave){
//        //Proceed with your save logic here
//    }
}