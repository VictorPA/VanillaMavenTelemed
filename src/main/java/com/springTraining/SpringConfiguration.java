package com.springTraining;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Victor on 15/06/2016.
 */

@ComponentScan("com.springTraining")
@Configuration
public class SpringConfiguration {
    @Bean
    public Lac lac(){
        return new Lac();
    }
}
