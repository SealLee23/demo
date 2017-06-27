package com.gym.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

/**
 * Created by seal.li on 2017/6/27.
 */
@Configuration
@PropertySource(value = "classpath:context/application_context.properties")
public class ApplicationConfiguration {

    /**
     * Property placeholder Configuration
     */

    public ApplicationConfiguration(@Value(value = "${factor}") String factor){
        System.out.println("Initialize ApplicationConfiguration: factor: "+factor);
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
