package com.appculture.missioncontrol;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by abozic on 6/29/16.
 */
public class MissionControlTest {

    private String sampleConfigURL = "http://private-83024-missioncontrol5.apiary-mock.com/mission-control/launch-config";

    @Before
    public void setUp() throws Exception {
        MissionControl.launch();
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testLaunch() throws Exception {

    }

    @Test
    public void getRemoteConfigBlockingTest() {
        MissionControl.launch(sampleConfigURL);
    }
}