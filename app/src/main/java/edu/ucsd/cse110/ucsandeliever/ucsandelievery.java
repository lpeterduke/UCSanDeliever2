package edu.ucsd.cse110.ucsandeliever;

import android.app.Application;

import com.firebase.client.Firebase;

/**
 * Created by Peterli on 10/22/16.
 */

public class ucsandelievery extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Firebase.setAndroidContext(this);
    }
}
