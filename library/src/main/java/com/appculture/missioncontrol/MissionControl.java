package com.appculture.missioncontrol;

/**
 * Created by abozic on 6/29/16.
 */
public final class MissionControl {

    //model
    private String url; // URL of remote config
    private static MissionControl instance;

    private MissionControl(String remoteURL) {
        this.url = remoteURL;
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
