package com.appculture.missioncontrol.configurations;

import android.content.Context;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Config represent Local or Remote configuration available to the library to read data from.
 * <p>
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

    public void setJsonObject(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
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

    /**
     * Callback used when loading Config asynchronously.
     */
    public interface Callback {

        void onSuccess(String responseBodyString);

        void onFail(ErrorType errorType);
    }

}
