package com.appculture.missioncontrol.configurations;

import android.content.Context;

import com.appculture.missioncontrol.MissionControl;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Remote config represent settings which are obtained from URL.
 * <p/>
 * Created by Uki on 8/16/16.
 */
public class RemoteConfig extends Config {

    private String url;
    private OkHttpClient httpClient;

    public RemoteConfig(Context context, String url) {
        super(context);
        this.url = url;
        initHttpClient();
        readRemoteConfig();
    }

    private void initHttpClient() {
        this.httpClient = new OkHttpClient();
        getRemoteConfigAsync(null);
    }

    private void readRemoteConfig() {
        // TODO read remote cached config

    }

    public void getRemoteConfigAsync(final MissionControl.Callback callback) {
        if (url == null || url.isEmpty()) return;

        httpClient.newCall(generateRequest()).enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                if (callback != null) callback.onFail(ErrorType.CONNECTION_ERROR);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //TODO pass all parsing to upper class
                if (callback != null) callback.onSuccess(RemoteConfig.this);
            }
        });
    }

    public String getRemoteConfigBlocking() {
        if (url == null || url.isEmpty()) return null;

        try {
            Response response = httpClient.newCall(generateRequest()).execute();
            return processResponseAndCloseIt(response);
        } catch (Exception e) {
            return null;
        }
    }

    private Request generateRequest() {
        return new Request.Builder()
                .url(url)
                .build();
    }

    private String processResponseAndCloseIt(Response response) {
        if (response == null) return null;
        try {
            ResponseBody responseBody = response.body();
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }

            return responseBody.string();
        } catch (Exception e) {
            return null;
        } finally {
            response.close();
        }
    }
}
