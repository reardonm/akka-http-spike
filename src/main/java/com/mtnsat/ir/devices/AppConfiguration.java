package com.mtnsat.ir.devices;

import akka.actor.ActorSystem;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.mtnsat.ir.akka_util.SpringExtension.SpringExtensionProvider;

/**
 * The application configuration.
 */
@Configuration
class AppConfiguration {

    // the application context is needed to initialize the Akka Spring Extension
    @Autowired
    private ApplicationContext applicationContext;

    @Bean
    public Config config() {
        //TODO: config file
        return ConfigFactory.load();
    }

    /**
     * Actor system singleton for this application.
     */
    @Bean
    public ActorSystem actorSystem(Config config) {
        ActorSystem system = ActorSystem.create("DeviceCntrl", config);
        // initialize the application context in the Akka Spring Extension
        SpringExtensionProvider.get(system).initialize(applicationContext);
        return system;
    }
}
