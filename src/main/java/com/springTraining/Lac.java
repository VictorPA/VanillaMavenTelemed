package com.springTraining;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Victor on 15/06/2016.
 */


public class Lac {

    @Autowired
    Alligator alligator;


    public Alligator getAlligator() {
        return alligator;
    }
}
