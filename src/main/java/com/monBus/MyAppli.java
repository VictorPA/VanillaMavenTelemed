package com.monBus;

import com.google.gwt.thirdparty.guava.common.eventbus.EventBus;

/**
 * Created by Victor on 02/06/2016.
 */
public class MyAppli {

    public static void main(String[] args) {

        EventBus eventBus = new EventBus();
        MyEventCatcher myEventCatcher = new MyEventCatcher();
        eventBus.register(myEventCatcher);
        eventBus.post(new MyEvent());
    }
}
