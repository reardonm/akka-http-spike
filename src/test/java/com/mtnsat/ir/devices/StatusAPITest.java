package com.mtnsat.ir.devices;

import akka.http.javadsl.model.*;
import akka.http.javadsl.testkit.TestRoute;
import com.mtnsat.ir.test_util.JsonAPITest;
import org.junit.Test;

import java.util.Collections;
import java.util.Map;


public class StatusAPITest extends JsonAPITest {

    TestRoute appRoute = testRoute(new StatusAPI().createRoute());

    @Test
    public void pingGET() {
        appRoute.run(HttpRequest.GET("/status/ping"))
                .assertStatusCode(StatusCodes.OK)
                .assertMediaType(MediaTypes.TEXT_PLAIN)
                .assertEntity("pong");
    }

    @Test
    public void manifestGET() {
        appRoute.run(HttpRequest.GET("/status/manifest"))
                .assertStatusCode(StatusCodes.OK)
                .assertMediaType(MediaTypes.APPLICATION_JSON)
                .assertEntityAs(jsonAs(Map.class), Collections.singletonMap("foo", "bar"));
    }


    @Test
    public void statusGET() {
        appRoute.run(HttpRequest.GET("/status"))
                .assertStatusCode(StatusCodes.NOT_FOUND);
    }
}