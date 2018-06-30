package com.example.orangerabbit.fitness;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import java.util.HashMap;

class UserSessionManager {

    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private Context context;
    private static final String Prefer_name = "AndroidExamplePref";
    private static final String Is_User_Login = "IsUserLoggedIn";

    public static final String KEY_NAME = "name";
    public static final String KEY_NUMBER = "number";

    UserSessionManager(Context context) {
        this.context = context;
        int private_mode = 0;
        pref = context.getSharedPreferences(Prefer_name, private_mode);
        editor = pref.edit();
    }

    public void createUserLoginSession(String name, String number) {

        editor.putBoolean(Is_User_Login,true);
        editor.putString(KEY_NAME,name);
        editor.putString(KEY_NUMBER,number);
        editor.commit();
    }

    public boolean checkLogin() {

        if(!this.isUserLoggedIn()){
            Intent i = new Intent(context,LogIn.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);
            return true;
        }
        return false;
    }

    public HashMap<String,String> getuserDetails() {
        HashMap<String,String> user = new HashMap<>();
        user.put(KEY_NUMBER,pref.getString(KEY_NUMBER,null));
        user.put(KEY_NAME,pref.getString(KEY_NAME,null));
        return user;
    }

    boolean isUserLoggedIn(){
        return pref.getBoolean(Is_User_Login, false);
    };

    public void logoutUser(){
        editor.clear();
        editor.commit();
        Intent i = new Intent(context,MainActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);
    }
}
