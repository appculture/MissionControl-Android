package com.appculture.missioncontrol.util;

import android.content.Context;
import android.test.InstrumentationTestCase;

/**
 * Created by abozic on 6/2/16.
 */
public class NetworkUtilTest extends InstrumentationTestCase {

    private Context context;

    @org.junit.Before
    public void setUp() throws Exception {
        context = getInstrumentation().getContext();
    }

    @org.junit.Test
    public void testCheckNetworkConnection() throws Exception {
        NetworkUtil.checkNetworkConnection(context);
        //just check if method pass
        assert true;
    }

    @org.junit.Test
    public void testIsNetworkAvailable() throws Exception {

        boolean isConnectionAvailable = NetworkUtil.isNetworkAvailable(context);
        assertEquals(true, isConnectionAvailable);
    }

    @org.junit.Test
    public void testNoNetworkAvailable() throws Exception {

        boolean isConnectionAvailable = NetworkUtil.isNetworkAvailable(context);
        assertEquals(false, isConnectionAvailable);
    }

    @org.junit.Test
    public void testIsNetworkAvailableOverWiFi() throws Exception {

        boolean isWifi = NetworkUtil.isConnectedOverWiFi(context);
        assertEquals(true, isWifi);
    }

    @org.junit.Test
    public void testIsNetworkAvailableOverMobile() throws Exception {

        boolean isMobile = NetworkUtil.isConnectedOverMobile(context);
        assertEquals(true, isMobile);
    }

}