package com.visitoto;

import static java.lang.System.*;

/**
 * Created by Victor on 02/06/2016.
 */
class Vue {

    /*public void handleAction(Object target) {
        if(target instanceof EventCreate)
            System.out.println("Création");
        else if(target instanceof EventDelete)
            System.out.println("Delete");
    }
*/
    void handleAction(Object target) {
        //new Handler().handle(target);

    }


}

class EventCreate {}
class EventDelete {}

class Trigger {

    public Object create() {
        return new EventCreate();
    }

    public Object delete() {
        return new EventDelete();
    }
}

class Appli{
    public static void main(String[] args) {
        Trigger trigger = new Trigger();
        Vue vue = new Vue();
        vue.handleAction(trigger.create());
    }
}

class Handler{
    public void handle(EventCreate target){
        out.println("Delete");
    }

    public void handle(EventDelete target){
        out.println("Création");
    }
}

