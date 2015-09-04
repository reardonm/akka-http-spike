package com.mtnsat.ir.devices;

import akka.http.javadsl.model.HttpRequest;
import akka.http.javadsl.model.StatusCodes;
import akka.http.javadsl.testkit.JUnitRouteTest;
import akka.http.javadsl.testkit.TestRoute;
import akka.http.scaladsl.testkit.RouteTest;
import org.junit.Test;

public class DeviceApiTest extends JUnitRouteTest {
    TestRoute appRoute = testRoute(new DeviceAPI().createRoute());

    @Test
    public void testCalculatorAdd() {
        // test happy path
        appRoute.run(HttpRequest.GET("/status/ping"))
                .assertStatusCode(200)
                .assertEntity("pong");

    }
}