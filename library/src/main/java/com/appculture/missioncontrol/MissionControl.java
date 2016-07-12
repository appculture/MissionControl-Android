package com.appculture.missioncontrol;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by abozic on 6/29/16.
 */
public final class MissionControl {

    //model
    private String url; // URL of remote config
    private static MissionControl instance;

    //controller
    private OkHttpClient httpClient;

    private MissionControl(String remoteURL) {
        this.httpClient = new OkHttpClient();
        this.url = remoteURL;
    }

    /**
     * MissionControl calls callback.
     */
    public interface Callback {

        void onSuccess(String responseBodyString);

        void onFail();
    }

    public void getRemoteConfigAsync(final Callback callback) {
        if (url == null || url.isEmpty()) return;

        httpClient.newCall(generateRequest()).enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                if (callback != null) callback.onFail();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (callback != null) callback.onSuccess(processResponseAndCloseIt(response));
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

    private void setUrl(String remoteConfigURL) {
        this.url = remoteConfigURL;
    }

    private static void init() {
        if (instance == null) {
            instance = new MissionControl(null);
        }
    }

    public static void launch() {
        init();
    }

    public static void launch(String remoteConfigURL) {
        init();
        instance.setUrl(remoteConfigURL);
//        instance.getRemoteConfigBlocking(remoteConfigURL, new Callback() {
//            @Override
//            public void onSuccess(String responseBodyString) {
//
//            }
//
//            @Override
//            public void onFail() {
//
//            }
//        });
    }

    public static MissionControl getInstance() {
        init();
        return instance;
    }
}
