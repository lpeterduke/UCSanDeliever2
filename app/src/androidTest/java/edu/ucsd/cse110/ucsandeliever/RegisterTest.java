package edu.ucsd.cse110.ucsandeliever;


import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.pressImeActionButton;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
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
        ThreadController tc=new ThreadController();
        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.buttonsignup), withText("New Member"),
                        withParent(allOf(withId(R.id.activity_main),
                                withParent(withId(android.R.id.content)))),
                        isDisplayed()));
        appCompatButton.perform(click());

        ViewInteraction editText = onView(
                allOf(withId(R.id.SUName), isDisplayed()));
        editText.perform(replaceText("J"), closeSoftKeyboard());

        ViewInteraction editText2 = onView(
                allOf(withId(R.id.editText6), isDisplayed()));
        editText2.perform(replaceText("A01234567"), closeSoftKeyboard());

        ViewInteraction editText3 = onView(
                allOf(withId(R.id.editText5), isDisplayed()));
        editText3.perform(replaceText("j@ucsd.edu"), closeSoftKeyboard());

        ViewInteraction editText4 = onView(
                allOf(withId(R.id.editText4), isDisplayed()));
        editText4.perform(replaceText("testregister"), closeSoftKeyboard());

        ViewInteraction editText5 = onView(
                allOf(withId(R.id.editText4), withText("testregister"), isDisplayed()));
        editText5.perform(pressImeActionButton());

        ViewInteraction editText6 = onView(
                allOf(withId(R.id.editTextRePassWord), isDisplayed()));
        editText6.perform(replaceText("testregister"), closeSoftKeyboard());

        ViewInteraction editText7 = onView(
                allOf(withId(R.id.editTextRePassWord), withText("testregister"), isDisplayed()));
        editText7.perform(pressImeActionButton());

        ViewInteraction button = onView(
                allOf(withId(R.id.button), withText("Sign Up Now!"), isDisplayed()));
        button.perform(click());

        tc.sleep(2500);

        ViewInteraction appCompatImageButton = onView(
                allOf(withContentDescription("Open navigation drawer"),
                        withParent(withId(R.id.toolbar)),
                        isDisplayed()));
        appCompatImageButton.perform(click());

        ViewInteraction appCompatCheckedTextView = onView(
                allOf(withId(R.id.design_menu_item_text), withText("log out"), isDisplayed()));
        appCompatCheckedTextView.perform(click());

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.buttonsignin), withText("Returning Customer"),
                        withParent(allOf(withId(R.id.activity_main),
                                withParent(withId(android.R.id.content)))),
                        isDisplayed()));
        appCompatButton2.perform(click());

        ViewInteraction editText8 = onView(
                allOf(withId(R.id.editText), isDisplayed()));
        editText8.perform(replaceText("j@ucsd.edu"), closeSoftKeyboard());

        ViewInteraction editText9 = onView(
                allOf(withId(R.id.editText2), isDisplayed()));
        editText9.perform(replaceText("testregister"), closeSoftKeyboard());

        ViewInteraction button2 = onView(
                allOf(withId(R.id.login2), withText("Login"), isDisplayed()));
        button2.perform(click());

        tc.sleep(2500);

        ViewInteraction appCompatImageButton2 = onView(
                allOf(withContentDescription("Open navigation drawer"),
                        withParent(withId(R.id.toolbar)),
                        isDisplayed()));
        appCompatImageButton2.perform(click());

    }

    private static Matcher<View> childAtPosition(
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
