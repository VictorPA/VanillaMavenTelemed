package com.halp;

import com.google.gwt.thirdparty.guava.common.eventbus.Subscribe;

/**
 * Created by Victor on 02/06/2016.
 */
public class EventListener {

    public int lastMessage = 0;

    @Subscribe
    public void listen(OurTestEvent event) {
        lastMessage = event.getMessage();
    }
    public int getLastMessage() {
        return lastMessage;
    }
}


