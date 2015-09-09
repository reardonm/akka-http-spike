package com.mtnsat.ir.akka_util;

import akka.actor.Actor;
import akka.actor.IndirectActorProducer;
import org.springframework.context.ApplicationContext;

/**
 * An actor producer that lets Spring create the Actor instances.
 */
public class SpringActorProducerByType implements IndirectActorProducer {
    final ApplicationContext applicationContext;
    final Class<? extends Actor> actorClz;

    public SpringActorProducerByType(ApplicationContext applicationContext, Class<? extends Actor> actorClass) {
        this.applicationContext = applicationContext;
        this.actorClz = actorClass;
    }

    @Override
    public Actor produce() {
        return applicationContext.getBean(actorClz);
    }

    @Override
    public Class<? extends Actor> actorClass() {
        return actorClz;
    }
}
