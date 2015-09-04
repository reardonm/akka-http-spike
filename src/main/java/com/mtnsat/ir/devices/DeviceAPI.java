package com.mtnsat.ir.devices;

import akka.http.javadsl.server.*;
import akka.http.javadsl.server.values.Parameters;

public class DeviceAPI extends HttpApp {
    @Override
    public Route createRoute() {
        return route(
            get(
                pathPrefix("status").route(
                    path("ping").route(
                        // return a simple `text/plain` response
                        complete("pong")
                    )
                )
            )
        );
    }
}