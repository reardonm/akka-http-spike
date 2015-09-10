package com.mtnsat.ir.devices.monitoring;

import akka.actor.AbstractLoggingActor;
import akka.actor.Props;
import org.springframework.stereotype.Component;

public class MonitoringManager extends AbstractLoggingActor {

    public static Props props() {
        return Props.create(MonitoringManager.class);
    }
}
