package com.appculture.missioncontrol.configurations;

import android.content.Context;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Uki on 8/16/16.
 */
public class LocalConfig extends Config implements Config.Callback{
    private static final String FILE_NAME = "launch-config.json";

    private Context context;

    public LocalConfig(Context context) {
        super(context);
        this.context = context;
        getLocalConfig();
    }

    public void getLocalConfig(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                String jsonString = null;
                try {
                    InputStream is = context.getAssets().open(FILE_NAME);
                    int size = is.available();
                    byte[] buffer = new byte[size];
                    is.read(buffer);
                    is.close();
                    jsonString = new String(buffer, "UTF-8");
                } catch (IOException ex) {
                    ex.printStackTrace();
                }finally {
                    onSuccess(jsonString);
                }

            }
        }).start();
    }

    private void readLocalConfig(){
        // TODO read local config into jsonObject
    }

    @Override
    public void onSuccess(String responseBodyString) {
        try {
            setJsonObject(new JSONObject(responseBodyString));
        } catch (JSONException e) {
            onFail(ErrorType.INVALID_DATA);
        }
    }

    @Override
    public void onFail(ErrorType errorType) {

    }
}
