package com.moesif.sdk.sample.wsclient;

/**
 * Response object containing HTTP status code and body
 */
public class MoesifResponse {
    public int statusCode;
    public String body;

    public MoesifResponse(int statCode, String reqBody) {
        this.statusCode = statCode;
        this.body = reqBody;
    }
}
