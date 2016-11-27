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
    Button done;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_requestor_finish);


        done = (Button) findViewById(R.id.buttonDone);
        done.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                // Clean up bid list - Zihan
                final String currUid = FirebaseAuth.getInstance().getCurrentUser().getUid();
                final DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
                DatabaseReference bidListRef = mRootRef.child("bidList");
                DatabaseReference requesterRef = bidListRef.child(currUid);
                requesterRef.removeValue();
                // Clean up finish, need test

                Intent intent = new Intent(requestor_finishActivity.this,drawerActivity.class);
                startActivity(intent);


                // go to main page


            }

    });
    }




    @Override
    public void onBackPressed() {

    }


}
