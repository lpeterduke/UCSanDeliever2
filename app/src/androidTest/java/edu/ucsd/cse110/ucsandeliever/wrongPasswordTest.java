package edu.ucsd.cse110.ucsandeliever;


import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import com.google.firebase.auth.FirebaseAuth;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class wrongPasswordTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);


    /*
    test the wrong password checking function
     */
    @Test
    public void wrongPasswordTest() {
        System.out.println("AAAAA");
        ThreadController tc = new ThreadController();
        FirebaseStatus fs = new FirebaseStatus();

        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.buttonsignin), withText("Returning Customer"),
                        withParent(allOf(withId(R.id.activity_main),
                                withParent(withId(android.R.id.content)))),
                        isDisplayed()));
        appCompatButton.perform(click());

        ViewInteraction editText = onView(
                allOf(withId(R.id.editText), isDisplayed()));
        editText.perform(replaceText("z@ucsd.edu"), closeSoftKeyboard());

        ViewInteraction editText2 = onView(
                allOf(withId(R.id.editText2), isDisplayed()));
        editText2.perform(replaceText("1111111"), closeSoftKeyboard());

        ViewInteraction button = onView(
                allOf(withId(R.id.login2), withText("Login"), isDisplayed()));
        button.perform(click());

        ViewInteraction button2 = onView(
                allOf(withId(R.id.login2), withText("Login"), isDisplayed()));
        button2.perform(click());
    }

}
