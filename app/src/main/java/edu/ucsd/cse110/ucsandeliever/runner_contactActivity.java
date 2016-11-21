package edu.ucsd.cse110.ucsandeliever;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class runner_contactActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_runner_contact);

    }

    public void welcomeSystem(View view){

        // go to chatting
        String button_text;
        button_text = ((Button) view).getText().toString();
        if(button_text.equals("Contact the Requester")){

            // chatting page for Pan
            // to change because chat needs to get done first - Zihan
            Intent intent = new Intent(this,UserList.class);
            startActivity(intent);
        }

        final String currUid = FirebaseAuth.getInstance().getCurrentUser().getUid();

        DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
        final DatabaseReference usersRef = mRootRef.child("users");
        DatabaseReference currRef = usersRef.child(currUid);
        usersRef.addChildEventListener(new ChildEventListener() {

            @Override
            public void onChildAdded(com.google.firebase.database.DataSnapshot dataSnapshot, String s) {

                if(dataSnapshot.getValue(Order.class).getRunnerUid().contentEquals(currUid)
                        || dataSnapshot.getValue(Order.class).getDone() == true) {

                    System.out.println("!!!!!!!haha");

                    // to change because chat needs to get done first - Zihan
                    // Intent intent = new Intent(userRunnerWaitingActivity.this, Chat.class);
                    // startActivity(intent);
                    //
                    //go to runner_contact page - he chang
                    Intent intent = new Intent(runner_contactActivity.this, runner_finishActivity.class);
                    startActivity(intent);
                }

                    else {
                        System.out.println("runners are not done the job yet");
                    }
                }


            @Override
            public void onChildChanged(com.google.firebase.database.DataSnapshot dataSnapshot, String s) {

                if(dataSnapshot.getValue(Order.class).getRunnerUid().contentEquals(currUid)
                        || dataSnapshot.getValue(Order.class).getDone() == true) {

                    System.out.println("!!!!!!!haha");

                    // to change because chat needs to get done first - Zihan
                    // Intent intent = new Intent(userRunnerWaitingActivity.this, Chat.class);
                    // startActivity(intent);
                    //
                    //go to runner_contact page - he chang
                    Intent intent = new Intent(runner_contactActivity.this, runner_finishActivity.class);
                    startActivity(intent);
                }

                else {
                    System.out.println("runners are not done the job yet");
                }
            }

            @Override
            public void onChildRemoved(com.google.firebase.database.DataSnapshot dataSnapshot) {


            }

            @Override
            public void onChildMoved(com.google.firebase.database.DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });




    }

    @Override
    public void onBackPressed() {

    }

}
