package edu.ucsd.cse110.ucsandeliever;


import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

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
public class RegisterTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void registerTest() {
        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.buttonsignup), withText("New Member"),
                        withParent(allOf(withId(R.id.activity_main),
                                withParent(withId(android.R.id.content)))),
                        isDisplayed()));
        appCompatButton.perform(click());

        ViewInteraction editText = onView(
                allOf(withId(R.id.SUName), isDisplayed()));
        editText.perform(replaceText("Testregister"), closeSoftKeyboard());

        ViewInteraction editText2 = onView(
                allOf(withId(R.id.editText6), isDisplayed()));
        editText2.perform(replaceText("A98234567"), closeSoftKeyboard());

        ViewInteraction editText3 = onView(
                allOf(withId(R.id.editText5), isDisplayed()));
        editText3.perform(replaceText("ttt@ucsd.edu"), closeSoftKeyboard());

        ViewInteraction editText4 = onView(
                allOf(withId(R.id.editText4), isDisplayed()));
        editText4.perform(replaceText("111111"), closeSoftKeyboard());

        ViewInteraction editText5 = onView(
                allOf(withId(R.id.editTextRePassWord), isDisplayed()));
        editText5.perform(replaceText("111111"), closeSoftKeyboard());

        ViewInteraction button = onView(
                allOf(withId(R.id.button), withText("Sign Up Now!"), isDisplayed()));
        button.perform(click());

    }

}
