package com.appculture.missioncontrol;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Headers;
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

        void onSuccess();

        void onFail();
    }

    private void getRemoteConfigBlocking(String url, Callback callback) {
        if (url == null || url.isEmpty()) return;

        Request request = new Request.Builder()
                .url(url)
                .build();
        httpClient.newCall(request).enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try (ResponseBody responseBody = response.body()) {
                    if (!response.isSuccessful()) {
                        throw new IOException("Unexpected code " + response);
                    }

                    //temporary code block
                    Headers responseHeaders = response.headers();
                    for (int i = 0, size = responseHeaders.size(); i < size; i++) {
                        System.out.println(responseHeaders.name(i) + ": " + responseHeaders.value(i));
                    }

                    System.out.println(responseBody.string());
                }
            }
        });
    }


    private void setUrl(String remoteConfigURL) {

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
        instance.getRemoteConfigBlocking(remoteConfigURL, null);
    }
}
