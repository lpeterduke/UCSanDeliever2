package edu.ucsd.cse110.ucsandeliever;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import edu.ucsd.cse110.ucsandeliever.utils.Const;

public class requestor_finishActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_requestor_finish);

    }

    public void welcomeSystem(View view){

        String button_text;
        button_text = ((Button) view).getText().toString();
        if(button_text.equals("Done")){

            // change the done of order
            final String currUid = FirebaseAuth.getInstance().getCurrentUser().getUid();

            final DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
            final DatabaseReference usersRef = mRootRef.child("orders");

            usersRef.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    for (DataSnapshot ds : dataSnapshot.getChildren()) {

                        Order order = ds.getValue(Order.class);


                        if (order.getDone() == false || order.getRequestorUid() == currUid)
                            order.changeDone(true);


                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            // go to main page
            Intent intent = new Intent(this,homeActivity.class);
            startActivity(intent);

        }


        else if(button_text.equals("comment")){

            // finishing page
        }

    }

    @Override
    public void onBackPressed() {

    }


}
