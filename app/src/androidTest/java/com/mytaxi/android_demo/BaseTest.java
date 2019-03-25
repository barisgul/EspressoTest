package com.mytaxi.android_demo;

import android.app.Instrumentation;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.test.InstrumentationRegistry;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.mytaxi.android_demo.util.PropFileReader;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Before;

import javax.inject.Inject;

public class BaseTest {
    public Context appContext;
    private SharedPreferences.Editor editor;

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

    public static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }


}
