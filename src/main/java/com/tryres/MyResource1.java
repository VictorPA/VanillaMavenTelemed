package com.tryres;

/**
 * Created by Victor on 03/06/2016.
 */
class MyResource1 implements AutoCloseable {

    public void use() throws MyException1 {
        System.out.println("Using my resource");
        throw new MyException1("Error while using");
    }

    @Override
    public void close() throws MyException1 {
        System.out.println("Closing my resource");
        throw new MyException1("Error while closing");
    }
}

class MyResource2 implements AutoCloseable {

    public void use() throws MyException2 {
        System.out.println("Using my resource");
        throw new MyException2("Error while using");
    }

    @Override
    public void close() throws MyException2 {
        System.out.println("Closing my resource");
        throw new MyException2("Error while closing");
    }
}


class MyException1 extends Exception {
    MyException1(String message) {
        super(message);
    }
}

class MyException2 extends Exception {
    MyException2(String message) {
        super(message);
    }
}

class TestTryWithResources {
    public static void main(String[] args) {
        try (
                MyResource1 res1 = new MyResource1();
                MyResource2 res2 = new MyResource2();

        )
        {
            res1.use();
            res2.use();
        } catch (MyException1 | MyException2 e) {
            e.printStackTrace();
        }
    }
}

