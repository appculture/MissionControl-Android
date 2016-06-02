package com.appculture.missioncontrol.util;

import android.content.Context;
import android.test.InstrumentationTestCase;

import static org.junit.Assert.*;

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
    }

    @org.junit.Test
    public void testIsNetworkAvailable() throws Exception {

    }
}