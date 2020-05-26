package com.oyak.baseapp.util;


import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.oyak.baseapp.constant.SystemConstants;

public class SharedUtil {
    public static void setDefaults(String key, String value, Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public static void setDefaults(String key, Object object, Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(object);
        editor.putString(key, json);
        editor.commit();
    }

    public static String getDefaults(String key, Context context) {
        String returnValue;
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        returnValue = preferences.getString(key, null);
        return returnValue;

    }

    public static <T> T getDefaults(String key, Context context,Class<T> classType){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        Gson gson = new Gson();
        String json = preferences.getString(key, null);
        return gson.fromJson(json, classType);
    }

    public static void clear(Context context){
        setDefaults(SystemConstants.SHARED_YETKI, null,context);
        setDefaults(SystemConstants.SHARED_USER, null,context);
    }


}
