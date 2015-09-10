package com.mtnsat.ir.devices;

import akka.event.Logging;
import akka.actor.ActorSystem;
import akka.event.LoggingAdapter;
import akka.http.javadsl.server.HttpApp;
import akka.http.javadsl.server.Route;

import akka.http.scaladsl.Http;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigRenderOptions;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import scala.concurrent.Future;

import java.util.Map;
import java.util.Optional;


/**
 * A main class to start up the application.
 */
public class Main {

    public static void main(String[] args) throws Exception {

        System.out.println("Initializing server");

        // create a spring context and scan the classes
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.scan(Main.class.getPackage().getName());
        ctx.refresh();

        Config config = ctx.getBean(Config.class).getConfig(DeviceControllerConfig.CONFIG_NAME);
        // dumping the effective configuration for auditing
        System.out.println(config.root().render());

        // get hold of the actor system
        ActorSystem system = ctx.getBean(ActorSystem.class);

        LoggingAdapter logger = Logging.getLogger(system, Main.class); // TODO: logback
        logger.info("Starting API service");

        V1DeviceControllerAPI deviceApi = ctx.getBean(V1DeviceControllerAPI.class);
        StatusAPI statusApi = new StatusAPI();

        // service
        try {
            HttpApp http = new HttpApp() {
                @Override
                public Route createRoute() {
                    return route(statusApi.createRoute(), deviceApi.createRoute());
                }
            };
            http.bindRoute(config.getString("http-interface"), config.getInt("http-port"), system);
            System.out.println("Type RETURN to exit");
            System.in.read();
        } finally {
            logger.info("Stopping service");
            system.shutdown();
            system.awaitTermination();
            ctx.close();
        }
    }
}
