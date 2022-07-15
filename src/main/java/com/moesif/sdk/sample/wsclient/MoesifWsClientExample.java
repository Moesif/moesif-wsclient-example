package com.moesif.sdk.sample.wsclient;

/**
 * This is the WS Client Java example to send a sample event to Moesif
 */
public class MoesifWsClientExample {

    /**
     * This is the main function
     * @param args Moesif Application ID, (optional URL)
     */
    public static void main(String[] args){
        if (args.length == 0 || args[0].equals("<Your-Moesif-Application-ID>")) {
            System.out.println("Your Moesif Application ID is missing");
            return;
        }
        String moesifApplicationID = args[0];

        //To override URL, add alternate URL in args
        String urlVar = "https://api.moesif.net/v1/events";
        if (args.length == 2)
            urlVar = args[1];

        String event = buildSampleMoesifEvent();
        MoesifClient newClient = new MoesifClient();
        MoesifResponse resp = newClient.sendToMoesif(urlVar, event, moesifApplicationID);

        System.out.println("Response status code: " + resp.statusCode);
        if (resp.body.length() > 1)
            System.out.println("Response body: " + resp.body);
        if (resp.statusCode >= 400)
            System.out.println("There was an error in sending the event to Moesif");
        else
            System.out.println("Event successfully sent to Moesif");
    }

    /**
     * Builds the sample Moesif event using CreateEvent class
     * @return Sample event body
     */
    public static String buildSampleMoesifEvent() {
        String targetUri = "http://localhost:5000/todo/api/v1.0/tasks";
        String verb = "POST";
        String host = "localhost:5000";
        String userAgent = "insomnia/7.1.1";
        String contentType = "application/json";
        String ipAddress = "127.0.0.1";

        CreateEvent m = new CreateEvent();
        String body = m.createBody(targetUri, verb, host, userAgent, contentType, ipAddress);
        return body;
    }
}