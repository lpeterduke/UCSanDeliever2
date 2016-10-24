package edu.ucsd.cse110.ucsandeliever;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatAutoCompleteTextView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Button;

import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;
import com.firebase.client.ValueEventListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by Administrator on 2016/10/8.
 */

public class requestActivity extends Fragment{



    private EditText etRestaurants;
    private EditText etItemName;
    private EditText etTime;
    private EditText etDestination;
    private Button makeOrder;
    private   EditText etPid;

    final Firebase myFirebaseRef = new Firebase("https://uc-student-deliver.firebaseio.com/");




    View myView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.request_layout, container, false);



        //create button
        makeOrder = (Button) myView.findViewById(R.id.button2);

        makeOrder.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                updateDataBase();


                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.content_main, new homeActivity()).commit();


            }
        });


        return myView;
    }


    public void updateDataBase() {

        //get Input
        etRestaurants  = (EditText) myView.findViewById(R.id.editText7); // get user ID
        etItemName = (EditText) myView.findViewById(R.id.editText8); // get user Email
        etTime  = (EditText) myView.findViewById(R.id.editText9); // get user password
        etDestination  = (EditText) myView.findViewById(R.id.editText10); // get user password

       // etPid = (EditText) myView.findViewById(R.id.editText6);

        FirebaseAuth mAuth = FirebaseAuth.getInstance();


        final  String userEmail = mAuth.getCurrentUser().getEmail().toString();
        System.out.println("+++++++++++++++++++"+ userEmail+" ");

        final String userID = userEmail.substring(0,userEmail.indexOf('@'));
        System.out.println("+++++++++++++++++++"+ userID+" ");







        final String res = etRestaurants.getText().toString();

       final String  item= etItemName.getText().toString();
        final String time = etTime.getText().toString();
        final String destination = etDestination.getText().toString();

        final Firebase myFirebaseRef = new Firebase("https://uc-student-deliver.firebaseio.com/");





                    Order order = new Order();
                    order.setRestaurants(res);

                    order.setItem(item);
                    order.setTime(time);
                    order.setDestination(destination);
                    order.setRequestor(userID);


                    myFirebaseRef.child("orders").child(order.getRestaurants()).setValue(order);





    }





}
