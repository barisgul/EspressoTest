package com.mytaxi.android_demo;

import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.mytaxi.android_demo.activities.MainActivity;
import com.mytaxi.android_demo.utils.storage.SharedPrefStorage;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Before;
import org.junit.Rule;

public class BaseTest {
    public Context appContext;

    @Rule
    public ActivityTestRule<MainActivity> mainActivityActivityTestRule =
            new ActivityTestRule<>(MainActivity.class,false,false);

    @Before
    public void setUp() throws Exception {
        //get appContext
        appContext = InstrumentationRegistry.getTargetContext();

        //Create SharedPrefStorage instance from storage
        SharedPrefStorage sharedPrefStorage = new SharedPrefStorage(appContext);

        //wipe data
        sharedPrefStorage.resetUser();
        //launch main activity
        mainActivityActivityTestRule.launchActivity(new Intent());
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
