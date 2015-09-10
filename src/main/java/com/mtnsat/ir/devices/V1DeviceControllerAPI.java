package com.mtnsat.ir.devices;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.pattern.AskSupport;
import akka.pattern.Patterns;
import akka.util.Timeout;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mtnsat.ir.devices.monitoring.MonitoringManager;
import  com.mtnsat.zeroimpactcommon.facilityreport.FacilityReport;
import akka.http.javadsl.server.*;
import akka.http.javadsl.server.values.Parameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import scala.concurrent.Await;
import scala.concurrent.Future;
import scala.concurrent.duration.Duration;


import java.util.Collection;
import java.util.Collections;
import java.util.Map;

import static akka.http.javadsl.marshallers.jackson.Jackson.*;


@Component
public class V1DeviceControllerAPI extends AllDirectives {

    private final FacilityReport facilityReport;

    private final ObjectMapper mapper;
    private final ActorRef monitoringManager;

    @Autowired
    public V1DeviceControllerAPI(DeviceControllerConfig config, ObjectMapper mapper, ActorRef monitoringManager){
        this.facilityReport = new FacilityReport();
        this.mapper = mapper;
        this.monitoringManager = monitoringManager;
    }


    public Route createRoute() {
        return route(
            pathPrefix("v1.1").route(
                path("facility").route(
                    get(
                        completeAs(json(mapper), facilityReport)
                    )
                ),
                path("alerts").route(
                    get(
                        handleWith(ctx -> {
                                    Timeout timeout = new Timeout(Duration.create(5, "seconds"));
                                    Future<Object> future = Patterns.ask(monitoringManager, "foo", timeout);
                                    Map<String,String> result = (Map<String,String>) Await.result(future, timeout.duration());
                                    return ctx.completeAs(json(mapper), result);
                                }
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