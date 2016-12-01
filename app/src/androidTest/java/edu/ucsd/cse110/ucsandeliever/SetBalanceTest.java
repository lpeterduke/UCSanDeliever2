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

@LargeTest
@RunWith(AndroidJUnit4.class)
public class SetBalanceTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    /*
    test the function of resetting balance
    */
    @Test
    public void setBalanceTest() {
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

        tc.sleep(3000);

        ViewInteraction appCompatImageButton = onView(
                allOf(withContentDescription("Open navigation drawer"),
                        withParent(withId(R.id.toolbar)),
                        isDisplayed()));
        appCompatImageButton.perform(click());

        ViewInteraction appCompatCheckedTextView = onView(
                allOf(withId(R.id.design_menu_item_text), withText("Balance"), isDisplayed()));
        appCompatCheckedTextView.perform(click());

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.balanceSetting), withText("You Can Set Your Balance Here"),
                        withParent(allOf(withId(R.id.account_main),
                                withParent(withId(R.id.content_main)))),
                        isDisplayed()));
        appCompatButton2.perform(click());

        ViewInteraction editText3 = onView(
                allOf(withId(R.id.balanceEdit),
                        withParent(allOf(withId(R.id.balance_setting),
                                withParent(withId(android.R.id.content)))),
                        isDisplayed()));
        editText3.perform(replaceText("10"), closeSoftKeyboard());

        ViewInteraction button2 = onView(
                allOf(withId(R.id.confirm), withText("Confirm"),
                        withParent(allOf(withId(R.id.balance_setting),
                                withParent(withId(android.R.id.content)))),
                        isDisplayed()));
        button2.perform(click());

        if (!fs.isUpdated())
            throw new NoFirebaseUpdateException();

    }

}
