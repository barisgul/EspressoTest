package com.mytaxi.android_demo;

import android.content.Intent;
import android.os.SystemClock;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.matcher.RootMatchers;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.mytaxi.android_demo.activities.MainActivity;
import com.mytaxi.android_demo.util.PropFileReader;
import com.mytaxi.android_demo.util.UserCredentials;

import org.junit.After;
import org.junit.Rule;
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
import static com.mytaxi.android_demo.activities.RecordTest.childAtPosition;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.AllOf.allOf;
import static org.junit.Assert.assertEquals;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class MyTaxiEspressoTest extends  BaseTest {

@Rule
public ActivityTestRule<MainActivity> mainActivityActivityTestRule =
        new ActivityTestRule<>(MainActivity.class,false,false);

    @Test
    public void useAppContext() throws Exception {
        mainActivityActivityTestRule.launchActivity(new Intent());

        assertEquals("com.mytaxi.android_demo", appContext.getPackageName());

        onView(withId(R.id.edt_username)).check(matches(isDisplayed()));

        /*PropFileReader fileReader = new PropFileReader();
        UserCredentials credentials = new UserCredentials(fileReader.getUserName(),fileReader.getPassword());
        String uName =credentials.userName;
        String pw = credentials.password;*/
        onView(withId(R.id.edt_username)).perform(typeText("crazydog335"), click());
        SystemClock.sleep(1500);
        onView(withId(R.id.edt_password)).perform(typeText("venture"), click());
        SystemClock.sleep(3500);
        //btn submit
        onView(withId(R.id.btn_login)).perform(click());

        //wit until authenticated activity appear
        SystemClock.sleep(500);

        //searchContainer
        onView(withId(R.id.textSearch)).perform(typeText("He"));

        SystemClock.sleep(500);

        onView(ViewMatchers.withText("Hector Gautier"))
                .inRoot(RootMatchers.isPlatformPopup())
                .perform(ViewActions.click());

        //assert driver name as "Hector Gautier"
        onView(withId(R.id.textViewDriverName)).check(matches(isDisplayed()));
    }

    @After
    public void tearDown() throws Exception {
        Espresso.pressBack();

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
}
