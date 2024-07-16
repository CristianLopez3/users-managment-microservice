package com.cristian.tiusers.steps;

public class StepUtils {

    private StepUtils(){}

    public static  String createUrl(int port, String endpoint) {
        return "http://localhost:" + port + endpoint;
    }


}
