package edu.ucsd.cse110.ucsandeliever;

/**
 * Created by jasonchen on 10/31/16.
 */

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.firebase.client.Firebase;
import com.google.firebase.auth.FirebaseAuth;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;
import com.firebase.client.ValueEventListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class balanceActivity extends Fragment {


    private EditText etBalance;
    private EditText name;
    private TextView mTextView;


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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        myView = inflater.inflate(R.layout.account_main, container, false);

        mTextView = (TextView) myView.findViewById(R.id.Balance);

        System.out.println("余额是："+balance);

        mTextView.setText(balance);


        return myView;

    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        balance = ((drawerActivity) activity).getBalance();


        System.out.println("Orders从Server接受了");
    }





}