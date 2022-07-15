package com.moesif.sdk.sample.wsclient;

import java.util.ArrayList;

import static com.moesif.sdk.sample.wsclient.MoesifWsClientExample.buildSampleMoesifEvent;

/**
 * This is the WS Client Java example to send a sample batch of events to Moesif
 */
public class MoesifWsClientBatchExample {

    /**
     * This is the main function
     * @param args Number of events, Moesif Application ID, (optional URL)
     */
    public static void main(String[] args){
        if (!isValidArgs(args))
            return;

        int numOfEvents = Integer.parseInt(args[0]);
        String moesifApplicationID = args[1];

        //To override URL, add alternate URL in args
        String urlVar = "https://api.moesif.net/v1/events/batch";
        if (args.length == 3)
            urlVar = args[2];

        String eventsStr = buildSampleMoesifEvents(numOfEvents);

        MoesifClient newClient = new MoesifClient();
        MoesifResponse resp = newClient.sendToMoesif(urlVar, eventsStr, moesifApplicationID);

        System.out.println("Response status code: " + resp.statusCode);
        if (resp.body.length() > 1)
            System.out.println("Response body: " + resp.body);
        if (resp.statusCode >= 400)
            System.out.println("There was an error in sending the event to Moesif");
        else
            System.out.println("Event successfully sent to Moesif");
    }

    public static Boolean isValidArgs(String[] args) {
        if (args.length == 0) {
            System.out.println("The number of events is missing");
            System.out.println("Your Moesif Application ID is missing");
            return false;
        }
        if (args.length == 1) {
            System.out.println("Either the number of events or your Moesif Application ID is missing");
            return false;
        }
        if (args[0].equals("<Number-of-events>")) {
            System.out.println("The number of events is missing");
            return false;
        }
        if (args[1].equals("<Your-Moesif-Application-ID>")) {
            System.out.println("Your Moesif Application ID is missing");
            return false;
        }
        return true;
    }

    /**
     * Builds array containing Moesif events
     * @param numOfEvents Number of events
     * @return String of json array of events
     */
    public static String buildSampleMoesifEvents (int numOfEvents) {
        ArrayList<String> events = new ArrayList<>();
        for (int i = 1; i <= numOfEvents; i++){
            String event = buildSampleMoesifEvent();
            events.add(event);
        }
        String eventsStr = "[" + String.join(",", events) + "]";
        return eventsStr;
    }
}