package com.mtnsat.ir.devices;


import akka.http.javadsl.testkit.TestResponse;
import com.mtnsat.ir.test_util.JsonAPITest;
import com.mtnsat.zeroimpactcommon.RouterType;
import com.mtnsat.zeroimpactcommon.facilityreport.FacilityReport;

import akka.http.javadsl.model.HttpRequest;
import akka.http.javadsl.model.StatusCodes;
import akka.http.javadsl.testkit.TestRoute;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import static com.mtnsat.ir.test_util.OptionalMatcher.*;

public class V1DeviceControllerAPITest extends JsonAPITest {

    DeviceControllerConfig config = new DeviceControllerConfig();

    TestRoute appRoute = testRoute(new V1DeviceControllerAPI(getObjectMapper(), config).createRoute());

    @Test
    public void facilityGET() {
        TestResponse resp = appRoute.run(HttpRequest.GET("/v1.1/facility"));
        resp.assertStatusCode(StatusCodes.OK);

        FacilityReport facilityReport = parse(resp, FacilityReport.class);
        assertThat(facilityReport.getModes().getMobile().isEnabled(), is(false));
        assertThat(facilityReport.getModes().getMobile().getDescription(), isEmpty());
        assertThat(facilityReport.getModes().getTunnel().isEnabled(), is(false));
        assertThat(facilityReport.getModes().getTunnel().getDescription(), isEmpty());
        assertThat(facilityReport.getModes().getVsat().isEnabled(), is(false));
        assertThat(facilityReport.getModes().getVsat().getDescription(), isEmpty());
        assertThat(facilityReport.getBats(), is(0));
        assertThat(facilityReport.getRouter(), is(RouterType.CISCO));
    }

    //@Test
    public void alertsGET() {
        appRoute.run(HttpRequest.GET("/v1.1/alerts"))
                .fail("implement me");
    }

    //@Test
    public void getcurrentrouteGET() {
        appRoute.run(HttpRequest.GET("/v1.1/getcurrentroute"))
                .fail("implement me");
    }
}