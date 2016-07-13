package com.scratch;

import com.config.SpringConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by Victor on 31/05/2016.
 */
public class MaVersionInfo {

    private String version;
    private String build;


   public MaVersionInfo(String version, String build){
       this.version = version;
       this.build = build;

   }

    public String getVersion() {
        return version;
    }

    public String getBuild() {
        return build;
    }

    public static void main(String[] args) {

        ConfigurableApplicationContext ca = new AnnotationConfigApplicationContext(SpringConfiguration.class);
        MaVersionInfo versionInfo = (MaVersionInfo)ca.getBean("maVersionInfo");
        System.out.println("Mon build : " + versionInfo.getBuild());
        System.out.println("Ma version : " +  versionInfo.getVersion());

    }

}
