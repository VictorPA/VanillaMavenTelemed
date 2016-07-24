package com.testOptionnal;

import org.junit.Test;

import java.io.File;
import java.util.Optional;

/**
 * @author Victor
 * @time 24 juillet 17h25
 */
public class TestOptional {

    @Test
    public void testOpt(){
        Optional<File> optional = Optional.ofNullable(new File("./sauvisse"));
        new File("./sauvisse");

    }
}
