package com.mtnsat.ir.devices;


import akka.http.javadsl.server.*;
import akka.http.javadsl.server.values.Parameters;

import java.util.Collection;
import java.util.Collections;

import static akka.http.javadsl.marshallers.jackson.Jackson.*;

public class V1DeviceControllerAPI extends AllDirectives {
    public Route createRoute() {
        return route(
            pathPrefix("v1.1").route(
                path("facility").route(
                    get(
                        complete("not implemented")
                    )
                ),
                path("alerts").route(
                    get(
                        complete("not implemented")
                    )
                ),
                path("getcurrentroute").route(
                    get(
                        complete("not implemented")
                    )
                )
            )
        );
    }
}