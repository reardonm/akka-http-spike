package com.mtnsat.ir.devices;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.guava.GuavaModule;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.joda.JodaModule;

import java.text.SimpleDateFormat;

public class ObjectMapperFactory {

    /**
     * Provide default Jackson ObjectMapper - configured for API purposes
     */
    public static ObjectMapper createDefaultObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        //mapper.setPropertyNamingStrategy(new AnnotationSensitivePropertyNamingStrategy());
        mapper.enable(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY);
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        mapper.getFactory().configure(JsonParser.Feature.ALLOW_COMMENTS, true);
        mapper.registerModule(new GuavaModule());
        mapper.registerModule(new JodaModule());
        mapper.registerModule(new Jdk8Module());
        mapper.setDateFormat(new SimpleDateFormat("MM/dd/yyyy HH:mm"));
        return  mapper;
    }
}

