package com.moesif.sdk.sample.wsclient;

import akka.util.ByteString;
import play.libs.ws.BodyWritable;
import play.libs.ws.StandaloneWSResponse;
import play.libs.ws.WSBodyReadables;
import play.libs.ws.WSBodyWritables;
import play.libs.ws.ahc.StandaloneAhcWSClient;

import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

/**
 * Sends event as POST to Moesif
 */
public class MoesifClient implements WSBodyReadables, WSBodyWritables {
    /**
     * Makes POST request
     * @param client WS Client
     * @param moesifUrl Moesif URL to Post event to
     * @param sampleBody Sample event body to send to Moesif
     * @param appId Moesif Application ID
     * @return POST request
     */
    public CompletionStage<? extends StandaloneWSResponse> doPostHttp(StandaloneAhcWSClient client,
                                                                      String moesifUrl,
                                                                      String sampleBody,
                                                                      String appId) {
        BodyWritable<ByteString> b = body(sampleBody);

        return client
                .url(moesifUrl)
                .addHeader(
                        "Content-Type", "application/json"
                )
                .addHeader(
                        "x-moesif-application-id", appId
                )
                .setRequestTimeout(Duration.ofSeconds(60))
                .post(b);
    }

    /**
     * Sends event to Moesif
     * @param urlVar Moesif URL to send event to
     * @param body Event body to send to Moesif
     * @param appId Moesif Application ID
     * @return Response status code and body
     */
    public MoesifResponse sendToMoesif(String urlVar, String body, String appId){
        WsClientWrapper jc = WsClientWrapper.buildClient();
        CompletionStage<? extends StandaloneWSResponse> xyz = doPostHttp(jc.getWsClient(), urlVar, body, appId);

        String responseBody = "";
        int responseStatusCode = 0;

        try {
            StandaloneWSResponse s = (StandaloneWSResponse)((CompletableFuture) xyz).join();
            responseBody = s.getBody();
            responseStatusCode = s.getStatus();
        } finally {
            jc.terminateClient();
        }
        return new MoesifResponse(responseStatusCode, responseBody);
    }
}

