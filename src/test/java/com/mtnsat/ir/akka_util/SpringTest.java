package com.mtnsat.ir.akka_util;


import akka.actor.ActorSystem;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringTest {

    private ActorSystem system;
    private AnnotationConfigApplicationContext ctx;

    public SpringTest() throws Exception {
        // create a spring context and scan the classes
        this.ctx = new AnnotationConfigApplicationContext();
        this.ctx.scan("com.mtnsat.ir");
        this.ctx.refresh();

        // get hold of the actor system
        this.system = ctx.getBean(ActorSystem.class);
    }

    public void termiate() {
        // shut down the actor system
        system.shutdown();
        system.awaitTermination();
    }
}
