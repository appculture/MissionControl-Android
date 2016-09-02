package com.appculture.missioncontrol.configurations;

import android.content.Context;

/**
 * Created by Uki on 8/16/16.
 */
public class RemoteConfig extends Config {

    public RemoteConfig(Context context) {
        super(context);
        readRemoteConfig();
    }

    private void readRemoteConfig(){
        // TODO read remote cached config
    }
}
