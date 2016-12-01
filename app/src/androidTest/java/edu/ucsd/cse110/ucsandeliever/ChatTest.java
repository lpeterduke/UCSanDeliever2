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
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class ChatTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void chatTest() {
        ThreadController tc=new ThreadController();
        FirebaseStatus fs=new FirebaseStatus();
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
        editText2.perform(replaceText("111111"), closeSoftKeyboard());

        ViewInteraction button = onView(
                allOf(withId(R.id.login2), withText("Login"), isDisplayed()));
        button.perform(click());
        tc.sleep(3000);

        ViewInteraction appCompatImageButton = onView(
                allOf(withContentDescription("Open navigation drawer"),
                        withParent(withId(R.id.toolbar)),
                        isDisplayed()));
        appCompatImageButton.perform(click());

        ViewInteraction appCompatCheckedTextView = onView(
                allOf(withId(R.id.design_menu_item_text), withText("Order Status"), isDisplayed()));
        appCompatCheckedTextView.perform(click());

        ViewInteraction appCompatTextView = onView(
                allOf(withId(android.R.id.text1), withText("Estimated Money to Pay: 20\nEstimated Arrival Time: 23: 55\n                               (About: 5 hours and 39 minutes)\nDeliver From : xmmfj3xpmhUb0oIBLMXw6uoAezO2"),
                        childAtPosition(
                                withId(R.id.Bid_List),
                                0),
                        isDisplayed()));
        appCompatTextView.perform(click());
        tc.sleep(3000);

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.buttonPlaceBid), withText("Yes, choose this runner"),
                        withParent(allOf(withId(R.id.activity_confirm_picking_bid),
                                withParent(withId(android.R.id.content)))),
                        isDisplayed()));
        appCompatButton2.perform(click());

        ViewInteraction button2 = onView(
                allOf(withId(R.id.buttonContRunner), withText("contact the runner"),
                        withParent(allOf(withId(R.id.content_requestor_contact),
                                withParent(withId(android.R.id.content)))),
                        isDisplayed()));
        button2.perform(click());

        ViewInteraction textView = onView(
                allOf(withText("p"),
                        childAtPosition(
                                withId(R.id.list),
                                0),
                        isDisplayed()));
        textView.perform(click());

        ViewInteraction editText3 = onView(
                allOf(withId(R.id.txt), isDisplayed()));
        editText3.perform(click());

        ViewInteraction editText4 = onView(
                allOf(withId(R.id.txt), isDisplayed()));
        editText4.perform(replaceText("Hello"), closeSoftKeyboard());

        ViewInteraction button3 = onView(
                allOf(withId(R.id.btnSend), isDisplayed()));
        button3.perform(click());

        pressBack();

        pressBack();

        ViewInteraction button4 = onView(
                allOf(withId(R.id.buttonReceived), withText("I have received the order"),
                        withParent(allOf(withId(R.id.content_requestor_contact),
                                withParent(withId(android.R.id.content)))),
                        isDisplayed()));
        button4.perform(click());

        ViewInteraction button5 = onView(
                allOf(withId(R.id.buttonDone), withText("Done"),
                        withParent(allOf(withId(R.id.content_requestor_finish),
                                withParent(withId(android.R.id.content)))),
                        isDisplayed()));
        button5.perform(click());

        if (!fs.isUpdated())
            throw new NoFirebaseUpdateException();
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
