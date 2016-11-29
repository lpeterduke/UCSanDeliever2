package edu.ucsd.cse110.ucsandeliever;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by taoyeyao on 2016/11/28.
 */

public class FirebaseStatus {

    boolean modified;
    Firebase myFirebaseRef;

    public FirebaseStatus()
    {
        modified=false;
        Firebase myFirebaseRef = new Firebase("https://uc-student-deliver.firebaseio.com/");
        FirebaseAuth mAuth = FirebaseAuth.getInstance();

        myFirebaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                modified=true;
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

    boolean isUpdated()
    {
        return modified;
    }

}
