package com.mytaxi.android_demo;

import android.os.SystemClock;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.matcher.RootMatchers;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.runner.AndroidJUnit4;

import com.mytaxi.android_demo.util.FileReaderUtil;
import com.mytaxi.android_demo.entity.UserCredentials;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.AllOf.allOf;
import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class MyTaxiEspressoTest extends BaseTest {

    @Test
    public void myTaxiLoginAndViewDriverProfileTest() throws Exception {

        String userName = FileReaderUtil.getProperty("userName",appContext);
        String password = FileReaderUtil.getProperty("password",appContext);

        UserCredentials  credentials = new UserCredentials(userName,password);

        assertEquals("com.mytaxi.android_demo", appContext.getPackageName());

        //assert username edt text
        onView(withId(R.id.edt_username)).check(matches(isDisplayed()));

        onView(withId(R.id.edt_username)).perform(typeText(credentials.userName), click());
        SystemClock.sleep(1500);
        onView(withId(R.id.edt_password)).perform(typeText(credentials.password), click());

        //logon
        onView(withId(R.id.btn_login)).perform(click());
        //searchContainer
        onView(withId(R.id.textSearch)).perform(typeText("He"));

        onView(ViewMatchers.withText("Hector Gautier"))
                .inRoot(RootMatchers.isPlatformPopup())
                .perform(ViewActions.click());

        //assert driver name as "Hector Gautier"
        onView(withId(R.id.textViewDriverName)).check(matches(isDisplayed()));

        //click on back
        Espresso.pressBack();

        //find and click on menu item
        onView(withId(R.id.toolbar)).perform(click());
        ViewInteraction appCompatImageButton = onView(
                allOf(withContentDescription("Open navigation drawer"),
                        childAtPosition(
                                allOf(withId(R.id.toolbar),
                                        childAtPosition(
                                                withClassName(is("android.support.design.widget.AppBarLayout")),
                                                0)),
                                1),
                        isDisplayed()));
        appCompatImageButton.perform(click());

        //find and click on logout button
        ViewInteraction navigationMenuItemView = onView(
                allOf(childAtPosition(
                        allOf(withId(R.id.design_navigation_view),
                                childAtPosition(
                                        withId(R.id.nav_view),
                                        0)),
                        1),
                        isDisplayed()));
        navigationMenuItemView.perform(click());
    }

    @After
    public void tearDown() throws Exception {

    }
}
