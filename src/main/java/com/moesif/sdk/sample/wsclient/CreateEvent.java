package com.moesif.sdk.sample.wsclient;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Random;

/**
 * A helper class for creating a sample Moesif event
 */
public class CreateEvent {
    /**
     * Create a sample event whose request time is the current time and the response time is 1-500 milliseconds later
     * @param targetUri Request URI; ex. http://www.example.com
     * @param verb Event Verb; ex. POST
     * @param host Host; ex. www.example.com
     * @param userAgent User agent; ex. Mozilla Firefox
     * @param contentType Content type; ex. application/json
     * @param ipAddress IP address; ex. 111.111.111.111
     * @return A json string of Moesif event
     */
    public String createBody(String targetUri,
                             String verb,
                             String host,
                             String userAgent,
                             String contentType,
                             String ipAddress) {
        Instant currentTime = Instant.now();
        Instant requestTime = buildRequestTime(currentTime);
        String requestTimeStr = requestTime.toString();
        String responseTimeStr = buildResponseTime(requestTime).toString();

        String requestHeaders = "{" +
                "       \"Host\": \"" + host + "\"," +
                "       \"User-Agent\": \"" + userAgent + "\"," +
                "       \"Content-Type\": \"" + contentType + "\"" +
                "     }";

        String requestBody = "{" +
                "       \"title\": \"My First POST\"," +
                "       \"description\": \"First POST request\"" +
                "     }";

        String request = "{" +
                "       \"time\": \"" + requestTimeStr + "\"," +
                "       \"uri\": \"" + targetUri + "\"," +
                "       \"verb\": \"" + verb + "\"," +
                "       \"headers\": " + requestHeaders + "," +
                "       \"api_version\": null," +
                "       \"ip_address\": \"" + ipAddress + "\"," +
                "       \"body\": " + requestBody + "," +
                "       \"transfer_encoding\": \"json\"" +
                "     }";

        String responseHeaders = "{" +
                "       \"Content-Type\": \"application/json\"," +
                "       \"Content-Length\": \"125\"" +
                "     }";

        String responseBody = "{" +
                "       \"task\": {" +
                "         \"description\": \"First POST request\"," +
                "         \"done\": false," +
                "         \"id\": 3," +
                "         \"title\": \"My First POST\"" +
                "       }" +
                "     }";
        String response = "{" +
                "       \"time\": \"" + responseTimeStr + "\"," +
                "       \"status\": 201," +
                "       \"headers\": " + responseHeaders + "," +
                "       \"body\": " + responseBody + "," +
                "       \"ip_address\": null," +
                "       \"transfer_encoding\": \"json\"" +
                "     }";

        String metadata = "{" +
                "       \"genre\": \"horror\"," +
                "       \"ISBN\": \"0123456789\"" +
                "     }";

        String sampleEvent = "{" +
                "       \"request\": " + request + "," +
                "       \"response\": " + response + "," +
                "       \"session_token\": \"XXXXXXXXXXXXXX\"," +
                "       \"tags\": null," +
                "       \"user_id\": \"sep_user_id\"," +
                "       \"company_id\": \"sep_company_id\"," +
                "       \"metadata\": " + metadata + "," +
                "       \"direction\": \"Outgoing\"," +
                "       \"weight\": 10" +
                "     }";

        return sampleEvent;
    }

    /**
     * Takes current time and makes request time between 1-2500 milliseconds later
     * @param currentTime Current time
     * @return Request time
     */
    private Instant buildRequestTime(Instant currentTime) {
        Random r = new Random();
        int randomMillis = r.nextInt(2500);
        return currentTime.minus(randomMillis, ChronoUnit.MILLIS);
    }

    /**
     * Takes request time and makes the response time between 1-500 milliseconds later
     * @param requestTime Request time (current time)
     * @return Response time
     */
    private Instant buildResponseTime(Instant requestTime) {
        Random r = new Random();
        int randomMillis = r.nextInt(501);
        return requestTime.plus(randomMillis, ChronoUnit.MILLIS);
    }
}