package edu.ucsd.cse110.ucsandeliever;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.firebase.client.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by jasonchen on 11/9/16.
 */

public class bSetActivity extends Activity {





    private EditText etBalance;
    private Button confirmButton;

    DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
    DatabaseReference usersRef = mRootRef.child("users");


    public FirebaseUser currentUser;
    DatabaseReference ref;

    final Firebase myFirebaseRef = new Firebase("https://uc-student-deliver.firebaseio.com/");
    private FirebaseAuth mAuth;
    private String balance = null;


    View myView;


    @Nullable
    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.balance_setting );

        etBalance = (EditText)findViewById(R.id.balanceEdit);

        confirmButton =  (Button)findViewById(R.id.confirm);

        System.out.println("余额是："+balance);

        confirmButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                enterHome();
            }
        });


    }




    public void enterHome() {

        String balance = etBalance.getText().toString();



        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        FirebaseAuth mAuth = FirebaseAuth.getInstance();


        final  String userEmail = mAuth.getCurrentUser().getEmail().toString();

        final String userID = userEmail.substring(0,userEmail.indexOf('@'));


        DatabaseReference ref =FirebaseDatabase.getInstance().getReference().child("users").child(userID).child("balance");
        ref.setValue(balance);


        Intent intent = new Intent(this, drawerActivity.class);
        startActivity(intent);



    }





}
