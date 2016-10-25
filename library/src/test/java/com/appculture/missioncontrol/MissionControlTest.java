package com.appculture.missioncontrol;

import com.appculture.missioncontrol.configurations.Config;
import com.appculture.missioncontrol.configurations.ErrorType;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;

import static org.junit.Assert.assertEquals;

/**
 * Created by abozic on 6/29/16.
 */
public class MissionControlTest {

    /**
     * Countdown latch
     */
    private CountDownLatch lock = new CountDownLatch(1);

    @Before
    public void setUp() throws Exception {
        String sampleConfigURL = "http://private-83024-missioncontrol5.apiary-mock.com/mission-control/launch-config";
        MissionControl.launch(sampleConfigURL);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testLaunch() throws Exception {

    }

    @Test
    public void getRemoteConfigBlockingTest() {
        Config config = MissionControl.getInstance().getRemoteConfigBlocking();
        String response = config.getString("TopColor");
        assertEquals(true, response != null && !response.isEmpty());
    }

    //temporary variable for testing following method
    private String response;

    @Test
    public void getRemoteConfigAsyncTest() throws InterruptedException {
        //MissionControl.launch(sampleConfigURL);

        response = null;
        MissionControl.getInstance().getRemoteConfigAsync(new MissionControl.Callback() {
            @Override
            public void onSuccess(String responseObject) {
                response = responseObject;
                lock.countDown();
            }

            @Override
            public void onFail(ErrorType errorType) {

            }
        });
        lock.await();
        assertEquals(true, response != null && !response.isEmpty());
    }

}