package com.moesif.sdk.sample.test.wsclient;

import com.moesif.sdk.sample.wsclient.MoesifClient;
import com.moesif.sdk.sample.wsclient.MoesifResponse;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class WsClientTest{
    /**
     * Tests that a 401 error is occurs if an invalid Moesif Application ID is provided
     */
    @Test
    public void testInvalidMoesifAppId() {
        String urlVar = "https://api.moesif.net/v1/events";
        String moesifApplicationID = "Invalid key";

        String event = "{}";
        MoesifClient misterClient = new MoesifClient();
        MoesifResponse resp = misterClient.sendToMoesif(urlVar, event, moesifApplicationID);
        assertEquals(401, resp.statusCode);
    }
}