package com.mtnsat.ir.devices;

import akka.event.Logging;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.event.LoggingAdapter;
import akka.http.scaladsl.Http;
import akka.stream.ActorMaterializer;
import akka.stream.javadsl.*;
import com.typesafe.config.ConfigFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import akka.http.javadsl.server.*;
import akka.http.javadsl.server.values.Parameters;
import scala.concurrent.Future;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

import static  com.mtnsat.ir.akka_util.SpringExtension.SpringExtProvider;


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
        ActorMaterializer materializer = ActorMaterializer.create(system);

        // HttpApp.bindRoute expects a route being provided by HttpApp.createRoute
        try {
            new DeviceAPI().bindRoute("localhost", 8080, system);
            System.out.println("Type RETURN to exit");
            System.in.read();
        } finally {
            system.shutdown();
            system.awaitTermination();
            ctx.close();
        }
    }
}
