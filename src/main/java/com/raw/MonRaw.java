package com.raw;

/**
 * Created by Victor on 08/06/2016.
 */
public class MonRaw <T>{
    private MonT<T> monT;

    public MonRaw(MonT<T> monT) {
        this.monT = monT;
    }

    public MonT<T> getMonT() {
        return monT;
    }
}
