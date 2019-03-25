package com.mytaxi.android_demo;

import android.app.Instrumentation;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.test.InstrumentationRegistry;

import org.junit.Before;

import javax.inject.Inject;

public class BaseTest {
    public Context appContext;
    public SharedPreferences.Editor editor;

    @Inject
    protected SharedPreferences pref;

    @Before
    public void setUp() throws Exception {
        //Instrumentation instrumentation = InstrumentationRegistry.getInstrumentation();
        appContext = InstrumentationRegistry.getTargetContext();
        editor = PreferenceManager.getDefaultSharedPreferences(appContext).edit();
        editor.clear();
        editor.apply();


    }


}
