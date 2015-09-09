package com.mtnsat.ir.test_util;

import akka.http.javadsl.server.Marshaller;
import akka.http.javadsl.server.Unmarshaller;
import akka.http.javadsl.testkit.JUnitRouteTest;
import akka.http.javadsl.testkit.TestResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mtnsat.ir.devices.ObjectMapperFactory;

import akka.http.javadsl.marshallers.jackson.Jackson;

public abstract class JsonAPITest extends JUnitRouteTest {

    static final ObjectMapper mapper = ObjectMapperFactory.createDefaultObjectMapper();

    public ObjectMapper getObjectMapper() {
        return mapper;
    }

    /** Helper to use the right ObjectMapper instance */
    protected  <T> T parse(TestResponse resp, Class<T> mapClass) {
        return resp.entityAs(jsonAs(mapClass));
    }

    /** Helper for akka-http use the right ObjectMapper instance */
    protected <T> Unmarshaller<T> jsonAs(Class<T> mapClass) {
        return Jackson.jsonAs(getObjectMapper(), mapClass);
    }

    /** Helper for akka-http to use the right ObjectMapper instance */
    protected <T> Marshaller<T> json(Class<T> mapClass) {
        return Jackson.json(getObjectMapper());
    }
}
