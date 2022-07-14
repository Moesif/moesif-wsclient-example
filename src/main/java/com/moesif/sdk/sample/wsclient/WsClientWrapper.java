package com.moesif.sdk.sample.wsclient;

import akka.actor.ActorSystem;
import akka.stream.Materializer;
import akka.stream.SystemMaterializer;
import com.typesafe.config.ConfigFactory;
import play.libs.ws.DefaultBodyReadables;
import play.libs.ws.ahc.AhcWSClientConfigFactory;
import play.libs.ws.ahc.StandaloneAhcWSClient;

import java.io.IOException;

// Refer https://github.com/playframework/play-ws#java-1

/**
 * Wrapper for WS Client
 */
public class WsClientWrapper implements DefaultBodyReadables {
    private final StandaloneAhcWSClient client;
    private final ActorSystem system;

    WsClientWrapper(ActorSystem system, StandaloneAhcWSClient client) {
        this.system = system;
        this.client = client;
    }

    /**
     * Build and return the WS Client
     * @return WS Client Wrapper
     */
    public static WsClientWrapper buildClient(){
        final String name = "wsclient";
        ActorSystem system = ActorSystem.create(name);
        //system.registerOnTermination(() -> System.exit(0));
        Materializer materializer = SystemMaterializer.get(system).materializer();

        // Create the WS client from the `application.conf` file, the current classloader and materializer.
        StandaloneAhcWSClient ws = StandaloneAhcWSClient
                .create(
                        AhcWSClientConfigFactory.forConfig(
                                ConfigFactory.load(),
                                system.getClass().getClassLoader()
                        ),
                        materializer
                );

        return new WsClientWrapper(system, ws);
    }

    public StandaloneAhcWSClient getWsClient() {
        return this.client;
    }

    /**
     * This function closes the WS Client
     */
    public void terminateClient(){
        try {
            this.client.close();
            this.system.terminate();
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
