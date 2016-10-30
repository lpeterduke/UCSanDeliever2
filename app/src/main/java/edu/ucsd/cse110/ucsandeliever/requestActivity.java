package edu.ucsd.cse110.ucsandeliever;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatAutoCompleteTextView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
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

public class requestActivity extends Fragment implements TimePickerDialog.OnTimeSetListener{

    Button b_pick;


    Spinner spinner;
    ArrayAdapter<CharSequence> adapter;

    private String etRestaurants;
    private EditText etItemName;
    private EditText etTime;
    private EditText etDestination;
    private Button makeOrder;
    private   EditText etPid;
    private EditText timeFromClock;

    final Firebase myFirebaseRef = new Firebase("https://uc-student-deliver.firebaseio.com/");

    TimePickerDialog.OnTimeSetListener onTimeSetListener;

int hour,minute;

    View myView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.request_layout, container, false);
        timeFromClock = (EditText)myView.findViewById(R.id.editText3);
        b_pick = (Button) myView.findViewById(R.id.timePicker);
        b_pick.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                java.util.Calendar calendar = java.util.Calendar.getInstance();
                hour = calendar.get(java.util.Calendar.HOUR_OF_DAY);
                minute = calendar.get(java.util.Calendar.MINUTE);


                TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(),requestActivity.this,hour,minute,true);
                timePickerDialog.show();




            }
        });

        spinner = (Spinner) myView.findViewById(R.id.spinner_restaurant);
        adapter = ArrayAdapter.createFromResource(getActivity(),R.array.restaurant_names,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                etRestaurants  =  (String) parent.getItemAtPosition(position); // get user ID
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });





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
        etItemName = (EditText) myView.findViewById(R.id.editText8); // get user Email
       etTime  =  timeFromClock; // get user password
        etDestination  = (EditText) myView.findViewById(R.id.editText10); // get user password

       // etPid = (EditText) myView.findViewById(R.id.editText6);

        FirebaseAuth mAuth = FirebaseAuth.getInstance();


        final  String userEmail = mAuth.getCurrentUser().getEmail().toString();
        System.out.println("+++++++++++++++++++"+ userEmail+" ");

        final String userID = userEmail.substring(0,userEmail.indexOf('@'));
        System.out.println("+++++++++++++++++++"+ userID+" ");







        final String res = etRestaurants;

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


    @Override
    public void onTimeSet(TimePicker timePickerDialog, int hourOfDay, int minute) {

        System.out.println("----------------------------------"+hourOfDay+minute+"---------");


        Toast.makeText(getActivity(),"Hour: "+hourOfDay + " Minute: " + minute,Toast.LENGTH_SHORT);

        timeFromClock.setText(hourOfDay+" : "+ minute);

    }

}
