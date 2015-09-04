package com.mtnsat.ir.devices;

import akka.event.Logging;
import akka.actor.ActorSystem;
import akka.event.LoggingAdapter;
import akka.http.javadsl.server.HttpApp;
import akka.http.javadsl.server.Route;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


/**
 * A main class to start up the application.
 */
public class Main {
    public static void main(String[] args) throws Exception {

        // create a spring context and scan the classes
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.scan(Main.class.getPackage().getName());
        ctx.refresh();

        // get hold of the actor system
        ActorSystem system = ctx.getBean(ActorSystem.class);

        // TODO DI, SLF4J?
        LoggingAdapter logger = Logging.getLogger(system, Main.class);

        // TODO Json
        //ActorMaterializer materializer = ActorMaterializer.create(system);

        try {
            HttpApp http = new HttpApp() {
                @Override
                public Route createRoute() {
                    return route(
                        new V1DeviceControllerAPI().createRoute(),
                        new StatusAPI().createRoute());
                }
            };

            http.bindRoute("localhost", 8080, system);
            System.out.println("Type RETURN to exit");
            System.in.read();
        } finally {
            system.shutdown();
            system.awaitTermination();
            ctx.close();
        }
    }
}
