package com.config;

import com.autow.CStartEventHandler;
import com.autow.Jardin;
import com.scratch.MaVersionInfo;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Victor on 30/05/2016.
 */
@ComponentScan ("com.autow")
@Configuration

public class SpringConfiguration {
    @Bean
    public Jardin jardin(){
        return new Jardin();
    }

    @Bean
    public CStartEventHandler cs(){
        return new CStartEventHandler();
    }

    @Bean
    public MaVersionInfo maVersionInfo() {
        try {
            PropertiesConfiguration pc = new PropertiesConfiguration("version.properties");
            String version = (String) pc.getProperty("version");
            String build = (String)pc.getProperty("teamcity");
            return new MaVersionInfo(version,build);
        } catch (ConfigurationException e) {
            e.printStackTrace();
            return null;
        }

    }

}
