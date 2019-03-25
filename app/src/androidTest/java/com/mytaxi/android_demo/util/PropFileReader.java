package com.mytaxi.android_demo.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropFileReader {
    private Properties properties;
    private final String propertyFilePath= "src/androidTest/java/com.mytaxi.android_demo/environments/configuration.properties";


    public PropFileReader(){
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(propertyFilePath));
            properties = new Properties();
            try {
                properties.load(reader);
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("Configuration.properties not found at " + propertyFilePath);
        }
    }

    public String getRequestUrl() {
        String requestUrl = properties.getProperty("requestUrl");
        if(requestUrl != null) return requestUrl;
        else throw new RuntimeException("Login password is not specified in the Configuration.properties file for the Key:password");
    }

    public String getUserName() {
        String userName = properties.getProperty("userName");
        if(userName != null) return userName;
        else throw new RuntimeException("Login password is not specified in the Configuration.properties file for the Key:password");
    }

    public String getPassword() {
        String password = properties.getProperty("password");
        if(password != null) return password;
        else throw new RuntimeException("Login password is not specified in the Configuration.properties file for the Key:password");
    }


}
