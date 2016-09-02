package com.appculture.missioncontrol.util;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Uki on 8/5/16.
 */
public final class Preferences {

    public static final String MISSION_CONTROL_KEY = "com.appculture.missioncontrol.util.Preferences.KEY";

    private static Preferences instance;
    private static Context mContext;

    protected Preferences(){}

    public static Preferences getInstance(Context context){
        mContext = context;

        if (instance == null) instance = new Preferences();

        return instance;
    }

    public void storeString(String key, String value){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(MISSION_CONTROL_KEY, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPreferences.edit();

        edit.putString(key, value);

        edit.apply();
    }

    public void storeStrings(HashMap<String, String> map){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(MISSION_CONTROL_KEY, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPreferences.edit();

        for (Map.Entry<String, String> entry : map.entrySet()){
            String key = entry.getKey();
            String value = entry.getValue();

            edit.putString(key, value);
        }

        edit.apply();
    }
}
