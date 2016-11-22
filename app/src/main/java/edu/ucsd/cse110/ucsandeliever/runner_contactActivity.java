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

    Button contact;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_runner_contact);

        contact = (Button) findViewById(R.id.button12);
        contact.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String currUid = FirebaseAuth.getInstance().getCurrentUser().getUid();

                Intent i = getIntent();
                Bundle data = i.getExtras();
                String requestor = data.getString("requestorGet");



                System.out.println("runnerContact 界面：" +currUid+requestor);


                Bundle b = new Bundle();
                b.putString("runner", currUid);
                b.putString("requestor", requestor);
                // chatting page for Pan
                // to change because chat needs to get done first - Zihan
                Intent intent = new Intent(runner_contactActivity.this, UserList.class);
                intent.putExtras(b);
                startActivity(intent);
            }
        });
    }
/*
    public void welcomeSystem(View view){

        // go to chatting
        String button_text;
        button_text = ((Button) view).getText().toString();
        if(button_text.equals("Contact the Requester")){
            String currUid = FirebaseAuth.getInstance().getCurrentUser().getUid();

            Intent i = getIntent();
            Bundle data = i.getExtras();
            String requestor = data.getString("requestorGet");

            Bundle b = new Bundle();
            b.putString("runner", currUid);
            b.putString("requestor", requestor);
            // chatting page for Pan
            // to change because chat needs to get done first - Zihan
            Intent intent = new Intent(runner_contactActivity.this, UserList.class);
            startActivity(intent);
        }

        final String currUid = FirebaseAuth.getInstance().getCurrentUser().getUid();

        DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
        final DatabaseReference usersRef = mRootRef.child("orders");
        DatabaseReference currRef = usersRef.child(currUid);
        usersRef.addChildEventListener(new ChildEventListener() {

            @Override
            public void onChildAdded(com.google.firebase.database.DataSnapshot dataSnapshot, String s) {
                int c = 0;
                System.out.println("Step: " + (c++));
                if(dataSnapshot.getValue(Order.class).getRunnerUid().contentEquals(currUid)
                        || dataSnapshot.getValue(Order.class).getDone() == true) {
                    System.out.println("Step: " + (c++));

                    System.out.println("!!!!!!!haha");

                    // to change because chat needs to get done first - Zihan
                    // Intent intent = new Intent(userRunnerWaitingActivity.this, Chat.class);
                    // startActivity(intent);
                    //
                    //go to runner_contact page - he chang

                    String currUid = FirebaseAuth.getInstance().getCurrentUser().getUid();

                    Intent i = getIntent();
                    Bundle data = i.getExtras();
                    String requestor = data.getString("requestorGet");

                    Bundle b = new Bundle();
                    b.putString("runnerGet", currUid);
                    b.putString("currRe", requestor);
                    Intent intent = new Intent(runner_contactActivity.this, UserList.class);
                    startActivity(intent);
                }

                    else {
                    System.out.println("Step: " + (c++));

                    System.out.println("runners are not done the job yet");
                    }
                }


            @Override
            public void onChildChanged(com.google.firebase.database.DataSnapshot dataSnapshot, String s) {
                System.out.println("Step2");

                if(dataSnapshot.getValue(Order.class).getRunnerUid().contentEquals(currUid)
                        || dataSnapshot.getValue(Order.class).getDone() == true) {

                    System.out.println("!!!!!!!haha");

                    // to change because chat needs to get done first - Zihan
                    // Intent intent = new Intent(userRunnerWaitingActivity.this, Chat.class);
                    // startActivity(intent);
                    //
                    //go to runner_contact page - he chang
                    Intent intent = new Intent(runner_contactActivity.this, UserList.class);
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
    */

    @Override
    public void onBackPressed() {

    }

}
