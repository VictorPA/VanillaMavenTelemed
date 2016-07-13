package com.monBus;

import com.google.gwt.thirdparty.guava.common.eventbus.Subscribe;

/**
 * Created by Victor on 02/06/2016.
 */

public class MyEventCatcher {

    @Subscribe
    public void handleMyEvent(MyEvent event){
        System.out.println("caught");
        event.signal();
    }
}
