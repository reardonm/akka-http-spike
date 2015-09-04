package com.mtnsat.ir.devices;

import akka.http.javadsl.model.HttpRequest;
import akka.http.javadsl.model.MediaTypes;
import akka.http.javadsl.model.StatusCodes;
import akka.http.javadsl.testkit.JUnitRouteTest;
import akka.http.javadsl.testkit.TestRoute;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Collections;
import java.util.Map;

import static akka.http.javadsl.marshallers.jackson.Jackson.*;

@RunWith(JUnit4.class)
public class V1DeviceControllerAPITest extends JUnitRouteTest {

    TestRoute appRoute = testRoute(new V1DeviceControllerAPI().createRoute());

    @Test
    public void facilityGET() {
        appRoute.run(HttpRequest.GET("/v1.1/facility"))
                .fail("implement me");
    }

    @Test
    public void alertsGET() {
        appRoute.run(HttpRequest.GET("/v1.1/alerts"))
                .fail("implement me");
    }

    @Test
    public void getcurrentrouteGET() {
        appRoute.run(HttpRequest.GET("/v1.1/getcurrentroute"))
                .fail("implement me");
    }
}