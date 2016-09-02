package com.appculture.missioncontrol.configurations;

import android.content.Context;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Uki on 8/16/16.
 */
public abstract class Config {

    protected JSONObject jsonObject;
    protected Context context;

    public Config(Context context) {
        this.context = context;
    }

    public JSONObject getJsonObject() {
        return jsonObject;
    }

    public boolean contains(String key) {
        return getJsonObject().has(key);
    }

    public int getConfigInt(String key) {
        try {
            return jsonObject.getInt(key);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return -1;
    }
}
