package com.autow;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextStartedEvent;

/**
 * Created by Victor on 31/05/2016.
 */
public class CStartEventHandler implements ApplicationListener<ContextStartedEvent> {

    public void onApplicationEvent(ContextStartedEvent event) {
        System.out.println("ContextStartedEvent received");
    }
}
