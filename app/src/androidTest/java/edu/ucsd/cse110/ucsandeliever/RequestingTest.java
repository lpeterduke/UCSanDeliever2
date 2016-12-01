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
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

import java.util.*;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class RequestingTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void requestingTest() {
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
        editText.perform(replaceText("yetao@ucsd.edu"), closeSoftKeyboard());

        ViewInteraction editText2 = onView(
                allOf(withId(R.id.editText2), isDisplayed()));
        editText2.perform(replaceText("123456"), closeSoftKeyboard());

        ViewInteraction button = onView(
                allOf(withId(R.id.login2), withText("Login"), isDisplayed()));
        button.perform(click());

        //System.out.println("waiting");
        tc.sleep(2500);

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
                allOf(withId(android.R.id.text1), withText("Tapioca"), isDisplayed()));
        appCompatTextView.perform(click());

        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.editText8), isDisplayed()));
        appCompatEditText.perform(replaceText("Combo B"), closeSoftKeyboard());

        ViewInteraction appCompatSpinner2 = onView(
                allOf(withId(R.id.spinner_building), isDisplayed()));
        appCompatSpinner2.perform(click());

        ViewInteraction appCompatTextView2 = onView(
                allOf(withId(android.R.id.text1), withText("ERC Europe Hall"), isDisplayed()));
        appCompatTextView2.perform(click());

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.button2), withText("make my order!"), isDisplayed()));
        appCompatButton2.perform(click());

        if (!fs.isUpdated())
            throw new NoFirebaseUpdateException();

    }

}
