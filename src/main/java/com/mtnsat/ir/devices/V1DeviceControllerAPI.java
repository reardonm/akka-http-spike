package com.mtnsat.ir.devices;

import com.fasterxml.jackson.databind.ObjectMapper;
import  com.mtnsat.zeroimpactcommon.facilityreport.FacilityReport;
import akka.http.javadsl.server.*;
import akka.http.javadsl.server.values.Parameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Collections;

import static akka.http.javadsl.marshallers.jackson.Jackson.*;

@Component
public class V1DeviceControllerAPI extends AllDirectives {

    private final FacilityReport facilityReport;

    private final ObjectMapper mapper;

    @Autowired
    public V1DeviceControllerAPI(ObjectMapper mapper, DeviceControllerConfig config){
        this.facilityReport = new FacilityReport();
        this.mapper = mapper;
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