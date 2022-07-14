package com.moesif.sdk.sample.test.wsclient;

import com.moesif.sdk.sample.wsclient.CreateEvent;
import org.junit.Test;

import static org.junit.Assert.assertFalse;

public class MoesifEventGenTest {
    /**
     * Tests that an event can be generated
     */
    @Test
    public void testGenerateEvent() {
        String targetUri = "http://localhost:5000/todo/api/v1.0/tasks";
        String verb = "POST";
        String host = "localhost:5000";
        String userAgent = "insomnia/7.1.1";
        String contentType = "application/json";
        String ipAddress = "127.0.0.1";

        CreateEvent m = new CreateEvent();
        String body = m.createBody(targetUri, verb, host, userAgent, contentType, ipAddress);
        assertFalse(body.isBlank());
    }
}

