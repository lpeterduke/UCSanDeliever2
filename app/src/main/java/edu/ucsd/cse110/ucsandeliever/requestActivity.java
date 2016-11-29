package edu.ucsd.cse110.ucsandeliever;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.Button;

import com.firebase.client.Firebase;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by Administrator on 2016/10/8.
 */

public class requestActivity extends Fragment implements TimePickerDialog.OnTimeSetListener{

    Button b_pick;


    Spinner spinner;
    Spinner spinner2;
    ArrayAdapter<CharSequence> adapter;
    ArrayAdapter<CharSequence> adapter2;

    private String etRestaurants;
    private String etDestinations;
    private EditText etItemName;
    private TextView etTime;
    private Button makeOrder;
    private   EditText etPid;
    private TextView timeFromClock;

    final Firebase myFirebaseRef = new Firebase("https://uc-student-deliver.firebaseio.com/");

    TimePickerDialog.OnTimeSetListener onTimeSetListener;

    int hour,minute;

    View myView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.request_layout, container, false);
        timeFromClock = (TextView)myView.findViewById(R.id.requestTime);
        //timeFromClock.setText("23: 59");
        orderingTime="201612312359";
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

        // another spinner for the destination
        spinner2 = (Spinner) myView.findViewById(R.id.spinner_building);
        adapter2 = ArrayAdapter.createFromResource(getActivity(),R.array.building_names,android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner2.setAdapter(adapter2);
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                etDestinations  =  (String) parent.getItemAtPosition(position); // get user ID
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        //Zihan Zheng nov 6/2016
        //create button
        makeOrder = (Button) myView.findViewById(R.id.button2);
        makeOrder.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                updateDataBase();
                //FragmentManager fragmentManager = getFragmentManager();
                //fragmentManager.beginTransaction().replace(R.id.content_main, new homeActivity()).commit();
                int id = view.getId();

                if (id == R.id.button2) {
                    Intent intent = new Intent(getActivity(), orderStatus.class);
                    startActivity(intent);
                    (getActivity()).overridePendingTransition(0,0);
                }
            }
        });

        //Zihan Zheng nov 6/2016 end


        return myView;
    }


    public void updateDataBase() {

        //get Input
        etItemName = (EditText) myView.findViewById(R.id.editText8); // get user Email
        etTime  =  timeFromClock; // get user password


        FirebaseAuth mAuth = FirebaseAuth.getInstance();


        final  String userEmail = mAuth.getCurrentUser().getEmail().toString();
        final String userID = userEmail.substring(0,userEmail.indexOf('@'));
        final String res = etRestaurants;
        final String des = etDestinations;

        final String  item= etItemName.getText().toString();
        final String time = orderingTime;

        final Firebase myFirebaseRef = new Firebase("https://uc-student-deliver.firebaseio.com/");

        Order order = new Order();
        order.setRestaurants(res);
        order.setItem(item);
        order.setTime(time);
        order.setDestination(des);
        order.setRequestor(userID);

        order.setRequestorUid(FirebaseAuth.getInstance().getCurrentUser().getUid().toString());


        if(TextUtils.isEmpty(res) ||
                TextUtils.isEmpty(item)||
                TextUtils.isEmpty(time)||
                TextUtils.isEmpty(des)||
                TextUtils.isEmpty(userID)) {
            //do nothing
        }else{

            myFirebaseRef.child("orders").child(order.getOrderNumber()).setValue(order);
            
            myFirebaseRef.child("users").child(order.getRequestorUid()).child("requesting").setValue(true);


            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.content_main, new homeActivity()).commit();
        }
    }


    @Override
    public void onTimeSet(TimePicker timePickerDialog, int hourOfDay, int minute) {

        Toast.makeText(getActivity(),"Hour: "+hourOfDay + " Minute: " + minute,Toast.LENGTH_SHORT);

        java.util.Calendar calendar = java.util.Calendar.getInstance();
        int year = calendar.get(java.util.Calendar.YEAR);
        int month = calendar.get(java.util.Calendar.MONTH);
        int day = calendar.get(java.util.Calendar.DAY_OF_MONTH);

        String monthS=Integer.toString(month);
        if(month<10){
            monthS = "0"+Integer.toString(month);
        }


        String dayS=Integer.toString(day);
        if(day<10){
             dayS = "0"+Integer.toString(day);
        }

        String hourS = Integer.toString(hourOfDay);
        if(hourOfDay<10){
            hourS = "0"+Integer.toString(hourOfDay);
        }

        String minS = Integer.toString(minute);
        if(minute<10){
            minS = "0"+Integer.toString(minute);
        }

         orderingTime = ""+year+monthS+dayS+hourS+minS;

        timeFromClock.setText(""+hourS+": "+minS);

    }

    private String orderingTime="";

}
