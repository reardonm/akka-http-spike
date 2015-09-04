package com.mtnsat.ir.devices;

import akka.http.javadsl.server.*;
import akka.http.javadsl.server.values.Parameters;

import java.util.Collection;
import java.util.Collections;

import static akka.http.javadsl.marshallers.jackson.Jackson.*;

public class StatusAPI extends AllDirectives {

    public Route createRoute() {
        return route(
                pathPrefix("status").route(
                    path("ping").route(
                        get(
                            // return a simple `text/plain` response
                            complete("pong")
                        )
                    ),
                    path("manifest").route(
                        get(
                            completeAs(json(), Collections.singletonMap("foo","bar"))
                        )
                    )
                )
        );
    }
}