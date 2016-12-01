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
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class ViewBidTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void viewBidTest() {
        ThreadController tc=new ThreadController();
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
                allOf(withId(R.id.design_menu_item_text), withText("Request A Delivery"), isDisplayed()));
        appCompatCheckedTextView.perform(click());

        ViewInteraction appCompatSpinner = onView(
                allOf(withId(R.id.spinner_restaurant), isDisplayed()));
        appCompatSpinner.perform(click());

        ViewInteraction appCompatTextView = onView(
                allOf(withId(android.R.id.text1), withText("Burger King"), isDisplayed()));
        appCompatTextView.perform(click());

        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.editText8), isDisplayed()));
        appCompatEditText.perform(replaceText("orderstatTest"), closeSoftKeyboard());

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.timePicker), withText(" Pick A Time"), isDisplayed()));
        appCompatButton2.perform(click());

        ViewInteraction appCompatButton3 = onView(
                allOf(withId(android.R.id.button1), withText("OK")));
        appCompatButton3.perform(scrollTo(), click());

        ViewInteraction appCompatSpinner2 = onView(
                allOf(withId(R.id.spinner_building), isDisplayed()));
        appCompatSpinner2.perform(click());

        ViewInteraction appCompatSpinner3 = onView(
                allOf(withId(R.id.spinner_building), isDisplayed()));
        appCompatSpinner3.perform(click());

        ViewInteraction appCompatTextView2 = onView(
                allOf(withId(android.R.id.text1), withText("ERC Europe Hall"), isDisplayed()));
        appCompatTextView2.perform(click());

        ViewInteraction appCompatButton4 = onView(
                allOf(withId(R.id.button2), withText("make my order!"), isDisplayed()));
        appCompatButton4.perform(click());

        pressBack();

        ViewInteraction appCompatImageButton2 = onView(
                allOf(withContentDescription("Open navigation drawer"),
                        withParent(withId(R.id.toolbar)),
                        isDisplayed()));
        appCompatImageButton2.perform(click());

        ViewInteraction appCompatCheckedTextView2 = onView(
                allOf(withId(R.id.design_menu_item_text), withText("log out"), isDisplayed()));
        appCompatCheckedTextView2.perform(click());

        ViewInteraction appCompatButton5 = onView(
                allOf(withId(R.id.buttonsignin), withText("Returning Customer"),
                        withParent(allOf(withId(R.id.activity_main),
                                withParent(withId(android.R.id.content)))),
                        isDisplayed()));
        appCompatButton5.perform(click());

        ViewInteraction editText3 = onView(
                allOf(withId(R.id.editText), isDisplayed()));
        editText3.perform(replaceText("z@ucsd.edu"), closeSoftKeyboard());

        ViewInteraction editText4 = onView(
                allOf(withId(R.id.editText2), isDisplayed()));
        editText4.perform(replaceText("111"), closeSoftKeyboard());

        ViewInteraction editText5 = onView(
                allOf(withId(R.id.editText), withText("z@ucsd.edu"), isDisplayed()));
        editText5.perform(click());

        ViewInteraction editText6 = onView(
                allOf(withId(R.id.editText), withText("z@ucsd.edu"), isDisplayed()));
        editText6.perform(replaceText("p@ucsd.edu"), closeSoftKeyboard());

        ViewInteraction editText7 = onView(
                allOf(withId(R.id.editText2), withText("111"), isDisplayed()));
        editText7.perform(replaceText("111111"), closeSoftKeyboard());

        ViewInteraction button2 = onView(
                allOf(withId(R.id.login2), withText("Login"), isDisplayed()));
        button2.perform(click());
        tc.sleep(3000);

        ViewInteraction appCompatImageButton3 = onView(
                allOf(withContentDescription("Open navigation drawer"),
                        withParent(withId(R.id.toolbar)),
                        isDisplayed()));
        appCompatImageButton3.perform(click());

        ViewInteraction appCompatCheckedTextView3 = onView(
                allOf(withId(R.id.design_menu_item_text), withText("View Orders"), isDisplayed()));
        appCompatCheckedTextView3.perform(click());

        ViewInteraction appCompatTextView3 = onView(
                allOf(withId(android.R.id.text1), withText("Getting: orderstatTest\nFrom: Burger King\nDeliver to: ERC Europe Hall\nNeed it by the time at: 22: 10"),
                        childAtPosition(
                                allOf(withId(R.id.Request_List),
                                        withParent(withId(R.id.home_main))),
                                1),
                        isDisplayed()));
        appCompatTextView3.perform(click());

        ViewInteraction appCompatButton6 = onView(
                allOf(withId(R.id.button3), withText("I am interested!"),
                        withParent(allOf(withId(R.id.activity_view_request_detail),
                                withParent(withId(R.id.content_main)))),
                        isDisplayed()));
        appCompatButton6.perform(click());

        ViewInteraction appCompatButton7 = onView(
                allOf(withId(R.id.timePicker), withText("Pick a time"),
                        withParent(allOf(withId(R.id.activity_place_bid),
                                withParent(withId(R.id.content_main)))),
                        isDisplayed()));
        appCompatButton7.perform(click());

        ViewInteraction appCompatButton8 = onView(
                allOf(withId(android.R.id.button1), withText("OK")));
        appCompatButton8.perform(scrollTo(), click());

        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.editText12),
                        withParent(allOf(withId(R.id.activity_place_bid),
                                withParent(withId(R.id.content_main)))),
                        isDisplayed()));
        appCompatEditText2.perform(replaceText("20"), closeSoftKeyboard());

        pressBack();

        ViewInteraction appCompatButton9 = onView(
                allOf(withId(R.id.button4), withText("Place Bid!"),
                        withParent(allOf(withId(R.id.activity_place_bid),
                                withParent(withId(R.id.content_main)))),
                        isDisplayed()));
        appCompatButton9.perform(click());

        pressBack();

        pressBack();

        ViewInteraction appCompatImageButton4 = onView(
                allOf(withContentDescription("Open navigation drawer"),
                        withParent(withId(R.id.toolbar)),
                        isDisplayed()));
        appCompatImageButton4.perform(click());

        ViewInteraction appCompatCheckedTextView4 = onView(
                allOf(withId(R.id.design_menu_item_text), withText("log out"), isDisplayed()));
        appCompatCheckedTextView4.perform(click());

        ViewInteraction appCompatButton10 = onView(
                allOf(withId(R.id.buttonsignin), withText("Returning Customer"),
                        withParent(allOf(withId(R.id.activity_main),
                                withParent(withId(android.R.id.content)))),
                        isDisplayed()));
        appCompatButton10.perform(click());

        ViewInteraction editText8 = onView(
                allOf(withId(R.id.editText), isDisplayed()));
        editText8.perform(replaceText("z@ucsd.edu"), closeSoftKeyboard());

        ViewInteraction editText9 = onView(
                allOf(withId(R.id.editText2), isDisplayed()));
        editText9.perform(replaceText("111111"), closeSoftKeyboard());

        ViewInteraction button3 = onView(
                allOf(withId(R.id.login2), withText("Login"), isDisplayed()));
        button3.perform(click());
        tc.sleep(3000);

        ViewInteraction appCompatImageButton5 = onView(
                allOf(withContentDescription("Open navigation drawer"),
                        withParent(withId(R.id.toolbar)),
                        isDisplayed()));
        appCompatImageButton5.perform(click());

        ViewInteraction appCompatCheckedTextView5 = onView(
                allOf(withId(R.id.design_menu_item_text), withText("Order Status"), isDisplayed()));
        appCompatCheckedTextView5.perform(click());

        ViewInteraction appCompatTextView4 = onView(
                allOf(withId(android.R.id.text1), withText("Estimated Money to Pay: 20\nEstimated Arrival Time: 23: 55\n                               (About: 5 hours and 42 minutes)\nDeliver From : xmmfj3xpmhUb0oIBLMXw6uoAezO2"),
                        childAtPosition(
                                withId(R.id.Bid_List),
                                0),
                        isDisplayed()));
        appCompatTextView4.perform(click());

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
