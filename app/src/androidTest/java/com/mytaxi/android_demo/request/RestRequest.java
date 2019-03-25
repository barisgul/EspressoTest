package com.mytaxi.android_demo.request;

import com.mytaxi.android_demo.util.PropFileReader;

public class RestRequest {
    private static String requestUrl = "";

    PropFileReader fileReader = new PropFileReader();

    public RestRequest() {
        requestUrl = fileReader.getRequestUrl();
    }

}
