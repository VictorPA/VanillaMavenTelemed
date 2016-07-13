package com.halp;

import com.google.gwt.thirdparty.guava.common.eventbus.EventBus;

/**
 * Created by Victor on 02/06/2016.
 */
public class Appli {

    public void shouldReceiveEvent() throws Exception {

        // given
        EventBus eventBus = new EventBus("test");
        EventListener listener = new EventListener();
        eventBus.register(listener);

        // when
        eventBus.post(new OurTestEvent(200));

        // then
        System.out.println(listener.getLastMessage());
    }

    public static void main(String[] args) throws Exception {
        Appli app = new Appli();
        app.shouldReceiveEvent();
    }

}
