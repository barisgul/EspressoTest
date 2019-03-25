package com.mytaxi.android_demo.util;

import android.content.Context;
import android.content.res.AssetManager;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropFileReader {
    private Properties properties;

    private final String propertyFilePath=
            "src\\androidTest\\assets\\files\\configuration.properties";


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

    public static String getProperty(String key, Context context) throws IOException {
        Properties properties = new Properties();;
        AssetManager assetManager = context.getAssets();
        InputStream inputStream = assetManager.open("configuration.properties");
        properties.load(inputStream);
        return properties.getProperty(key);
    }


}
