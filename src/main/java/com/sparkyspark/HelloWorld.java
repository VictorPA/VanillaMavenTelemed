package com.sparkyspark;

/**
 * Created by Victor on 29/06/2016.
 */
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;


public class HelloWorld {
    public static void main(String[] args) {
        Spark.get("/hello", new Route() {
            @Override public Object handle(Request req, Response res) throws Exception {
                return "Ca va";
            }
        });
    }
}