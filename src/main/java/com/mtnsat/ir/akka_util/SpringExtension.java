package com.mtnsat.ir.akka_util;

import akka.actor.*;
import org.springframework.context.ApplicationContext;

/**
 * An Akka Extension to provide access to Spring managed Actor Beans.
 */
public class SpringExtension extends
        AbstractExtensionId<SpringExtension.AppContextAdaptor> {

    /**
     * The identifier used to access the SpringExtension.
     */
    public static final SpringExtension SpringExtensionProvider = new SpringExtension();

    /**
     * Is used by Akka to instantiate the Extension identified by this
     * ExtensionId, internal use only.
     */
    @Override
    public AppContextAdaptor createExtension(ExtendedActorSystem system) {
        return new AppContextAdaptor();
    }

    /**
     * The Extension implementation.
     */
    public static class AppContextAdaptor implements Extension {
        private volatile ApplicationContext applicationContext;

        /**
         * Used to initialize the Spring application context for the extension.
         * @param applicationContext
         */
        public void initialize(ApplicationContext applicationContext) {
            this.applicationContext = applicationContext;
        }

        /**
         * Create a Props for the specified actorBeanName using the
         * SpringActorProducerByName class.
         *
         * @param actorBeanName  The name of the actor bean to create Props for
         * @return a Props that will create the named actor bean using Spring
         */
        public Props props(String actorBeanName) {
            return Props.create(SpringActorProducerByName.class, applicationContext, actorBeanName);
        }

        /**
         * Create a Props for the specified actorBeanName using the
         * SpringActorProducerByName class.
         *
         * @param actorClass  The name of the actor bean to create Props for
         * @return a Props that will create the named actor bean using Spring
         */
        public Props props(Class<? extends Actor> actorClass) {
            return Props.create(SpringActorProducerByType.class, applicationContext, actorClass);
        }
    }
}
