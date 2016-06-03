package com.appculture.missioncontrol.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Small helper for determine network connection type and status.
 * <p/>
 * Created by abozic on 6/2/16.
 */
public class NetworkUtil {

    public enum NetworkStatus {
        TYPE_NO_CONNECTION, TYPE_WIFI, TYPE_MOBILE;
    }

    public static NetworkStatus checkNetworkConnection(Context context) {
        ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeInfo = connMgr.getActiveNetworkInfo();
        if (activeInfo != null && activeInfo.isConnected()) {
            if (activeInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                return NetworkStatus.TYPE_WIFI;
            } else if (activeInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
                return NetworkStatus.TYPE_MOBILE;
            }
        }
        return NetworkStatus.TYPE_NO_CONNECTION;
    }

    public static boolean isNetworkAvailable(Context context) {
        return checkNetworkConnection(context) != NetworkStatus.TYPE_NO_CONNECTION;
    }

    public static boolean isConnectedOverWiFi(Context context) {
        return checkNetworkConnection(context) == NetworkStatus.TYPE_WIFI;
    }

    public static boolean isConnectedOverMobile(Context context) {
        return checkNetworkConnection(context) == NetworkStatus.TYPE_MOBILE;
    }
}
