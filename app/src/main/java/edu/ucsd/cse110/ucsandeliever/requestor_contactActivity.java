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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class requestor_contactActivity extends Activity {

    private String balance1 = "";
    private String balance2 = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_requestor_contact);

    }


    public void welcomeSystem(View view){

        String button_text;
        button_text = ((Button) view).getText().toString();

        if(button_text.equals("Contact the Runner")){

            System.out.println("is about to chat");
            // chatting page for Pan
            Intent i = getIntent();
            Bundle data = i.getExtras();
            String Runneuid = data.getString("runnerGet");

            Intent k = new Intent(requestor_contactActivity.this,UserList.class);
            Bundle b = new Bundle();
            b.putString("runnerGet", Runneuid);
            k.putExtras(b);
            startActivity(k);

        }else if(button_text.equals("I have received the order")){

            // finishing page
            DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
            DatabaseReference ordersRef = mRootRef.child("orders");

            System.out.println("is about to end");


            FirebaseAuth mAuth = FirebaseAuth.getInstance();

            FirebaseUser currentUser = mAuth.getCurrentUser();


            final String requester = currentUser.getUid();

            Intent i = getIntent();
            Bundle data = i.getExtras();
            final String runner = data.getString("runnerGet");;
            int changedValue = 0;
            DatabaseReference ref1 =FirebaseDatabase.getInstance().getReference().child("users").child(runner).child("balance");
            FirebaseDatabase.getInstance().getReference().child("users").child(runner).child("runnerStatusIndicator").setValue(false);

            DatabaseReference ref2 =FirebaseDatabase.getInstance().getReference().child("users").child(requester).child("balance");
            FirebaseDatabase.getInstance().getReference().child("users").child(requester).child("alreadyPick").setValue(false);
            FirebaseDatabase.getInstance().getReference().child("users").child(requester).child("requesting").setValue(false);

            ref1.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {

                    System.out.println("余额变更");
                    String msg = dataSnapshot.getValue(String.class);
                    System.out.println("msg: " + msg);

                    balance1 = msg;


                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });


            ref2.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {

                    System.out.println("余额变更");
                    String msg = dataSnapshot.getValue(String.class);
                    System.out.println("msg: " + msg);
                    balance2 = msg;


                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
            int newBalanceForUser1 = Integer.parseInt(balance1) + changedValue;
            int newBalanceForUser2 = Integer.parseInt(balance2) - changedValue;

            ref1.setValue(Integer.toString(newBalanceForUser1));
            ref2.setValue(Integer.toString(newBalanceForUser2));



            Intent intent = new Intent(this,requestor_finishActivity.class);
            startActivity(intent);
        }

    }

    @Override
    public void onBackPressed() {

    }

    }


