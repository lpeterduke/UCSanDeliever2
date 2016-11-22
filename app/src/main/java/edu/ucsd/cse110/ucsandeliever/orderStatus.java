package edu.ucsd.cse110.ucsandeliever;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
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
import java.util.Calendar;
import java.util.List;

import static android.icu.lang.UCharacter.JoiningGroup.E;


/**
 * Created by Administrator on 2016/10/8.
 */

public class orderStatus extends AppCompatActivity {

    // temp list for reading bids
    private List<String> bids = new ArrayList<>();
    private List<String> output = new ArrayList<>();
    private String runnerId;
    private Button refresh;
    private Button cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.orderstatus_layout);

        DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference bidListRef = mRootRef.child("bidList");
        bidListRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(com.google.firebase.database.DataSnapshot dataSnapshot, String s) {

                // get the current uid
                String currUid = FirebaseAuth.getInstance().getCurrentUser().getUid();

                System.out.println("登陆者是："+ currUid);
                System.out.println("订单人是："+ dataSnapshot.getKey());


                if(currUid.contentEquals(dataSnapshot.getKey())){
                    //需要更新bid list
                    //need more testing over here!!!!!!
                    output.clear();
                    bids.clear();

                    System.out.println("找到对应的Requestor");

                    // test1, if bug then change to add test-null condition for bidLevel (some have bids but some don't)
                    // and just delete the iterable and loop.

                    Iterable<com.google.firebase.database.DataSnapshot> newbids = dataSnapshot.getChildren();
                    for(com.google.firebase.database.DataSnapshot e: newbids) {
                        String time = e.getValue(Bid.class).getTime();
                        String hour = time.substring(0,2);
                        String minute = time.substring(2);

                        int dhour = Integer.parseInt(hour);
                        int dMinute = Integer.parseInt(minute);

                        java.util.Calendar calendar = java.util.Calendar.getInstance();
                        int cHour = calendar.get(Calendar.HOUR_OF_DAY);
                        int cMintue = calendar.get(Calendar.MINUTE);

                        int waitHour = dhour - cHour;

                        if(waitHour<0){
                            waitHour=waitHour+24;
                        }
                        int waitMinute = dMinute - cMintue;
                        String waitTime = ""+ waitHour+" hours and "+waitMinute+ " minutes";


                        if(waitMinute < 0){
                            waitMinute = 60+waitMinute;
                            waitHour --;

                            waitTime = ""+ waitHour+" hours and "+waitMinute+ " minutes";
                        }

                        runnerId = e.getValue(Bid.class).getRunner();

                        output.add("Estimated Money to Pay: " + e.getValue(Bid.class).getMoney() + "\nEstimated Arrival Time: " +
                                hour + ": "+minute+ "\n                               (About: "+waitTime+")"+"\nDeliver From : " +
                                runnerId);


                        bids.add(e.getValue(Bid.class).getMoney() + "=" +
                                e.getValue(Bid.class).getTime() + "=" +
                                runnerId);
                    }
                    //see what's in bids after adding
                    for(int i =0; i<bids.size(); i++){
                        System.out.println(bids.get(i));
                    }


                }

            }

            @Override
            public void onChildChanged(com.google.firebase.database.DataSnapshot dataSnapshot, String s) {
                // get the current uid
                String currUid = FirebaseAuth.getInstance().getCurrentUser().getUid();



                if(currUid.contentEquals(dataSnapshot.getKey())){
                    //需要更新bid list
                    //need more testing over here!!!!!!
                    output.clear();
                    bids.clear();

                    System.out.println("找到对应的Requestor");

                    // test1, if bug then change to add test-null condition for bidLevel (some have bids but some don't)
                    // and just delete the iterable and loop.

                    Iterable<com.google.firebase.database.DataSnapshot> newbids = dataSnapshot.getChildren();
                    for(com.google.firebase.database.DataSnapshot e: newbids) {
                        String time = e.getValue(Bid.class).getTime();
                        String hour = time.substring(0,2);
                        String minute = time.substring(2);


                        output.add("Estimated Money to Pay: " + e.getValue(Bid.class).getMoney() + "\nEstimated Arrival Time: " +
                                hour + ": "+minute+ "\nDeliver From : " +
                                e.getValue(Bid.class).getRunner());

                        bids.add(e.getValue(Bid.class).getMoney() + "=" +
                                e.getValue(Bid.class).getTime() + "=" +
                                e.getValue(Bid.class).getRunner());
                    }
                    //see what's in bids after adding
                    for(int i =0; i<bids.size(); i++){
                        System.out.println(bids.get(i));
                    }


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


        //把bids里的东西展示在界面上
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, output);

        ListView myFirstListView = (ListView)(findViewById(R.id.Bid_List));
        myFirstListView.setAdapter(adapter);



        // to change because bid category on firebase needs to get done first
        myFirstListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // reading the bid information according to the format
                String bidInfo = bids.get(position);
                String moneyGet = bidInfo.substring(0,bidInfo.indexOf('='));
                bidInfo = bidInfo.substring(bidInfo.indexOf('=')+1);
                String timeGet = bidInfo.substring(0,bidInfo.indexOf('='));
                bidInfo = bidInfo.substring(bidInfo.indexOf('=')+1);
                String runnerGet = bidInfo;

                // Bundle:
                // transmit data to the next page
                Intent i = new Intent(orderStatus.this, confirmPickingBidActivity.class);
                Bundle b = new Bundle();
                b.putString("moneyGet", moneyGet);
                b.putString("timeGet", timeGet);
                b.putString("runnerGet", runnerGet);
                i.putExtras(b);
                startActivity(i);
            }
        });

        refresh = (Button)findViewById(R.id.StatusRefresh);
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refresh();
            }
        });

        cancel = (Button)findViewById(R.id.button6);
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelIt();
            }
        });


    }

    public void cancelIt()
    {
        System.out.println("order cancelled");

        DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference ordersRef = mRootRef.child("orders");

        ordersRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(com.google.firebase.database.DataSnapshot dataSnapshot, String s) {


            }

            @Override
            public void onChildChanged(com.google.firebase.database.DataSnapshot dataSnapshot, String s) {

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

    public void refresh(){
        System.out.println("refresh order_status");

        DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference ordersRef = mRootRef.child("bidList");

        ordersRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(com.google.firebase.database.DataSnapshot dataSnapshot, String s) {

                // get the current uid
                String currUid = FirebaseAuth.getInstance().getCurrentUser().getUid();



                if(currUid.contentEquals(dataSnapshot.getKey())){
                    //需要更新bid list
                    //need more testing over here!!!!!!
                    output.clear();
                    bids.clear();

                    System.out.println("找到对应的Requestor");

                    // test1, if bug then change to add test-null condition for bidLevel (some have bids but some don't)
                    // and just delete the iterable and loop.

                    Iterable<com.google.firebase.database.DataSnapshot> newbids = dataSnapshot.getChildren();
                    for(com.google.firebase.database.DataSnapshot e: newbids) {
                        String time = e.getValue(Bid.class).getTime();
                        String hour = time.substring(0,2);
                        String minute = time.substring(2);

                        int dhour = Integer.parseInt(hour);
                        int dMinute = Integer.parseInt(minute);

                        java.util.Calendar calendar = java.util.Calendar.getInstance();
                        int cHour = calendar.get(Calendar.HOUR_OF_DAY);
                        int cMintue = calendar.get(Calendar.MINUTE);

                        int waitHour = dhour - cHour;

                        if(waitHour<0){
                            waitHour=waitHour+24;
                        }
                        int waitMinute = dMinute - cMintue;
                        String waitTime = ""+ waitHour+" hours and "+waitMinute+ " minutes";


                        if(waitMinute < 0){
                            waitMinute = 60+waitMinute;
                            waitHour --;

                            waitTime = ""+ waitHour+" hours and "+waitMinute+ " minutes";
                        }

                        output.add("Estimated Money to Pay: " + e.getValue(Bid.class).getMoney() + "\nEstimated Arrival Time: " +
                                hour + ": "+minute+ "\n                               (About: "+waitTime+")"+"\nDeliver From : " +
                                e.getValue(Bid.class).getRunner());


                        bids.add(e.getValue(Bid.class).getMoney() + "=" +
                                e.getValue(Bid.class).getTime() + "=" +
                                e.getValue(Bid.class).getRunner());
                    }
                    //see what's in bids after adding
                    for(int i =0; i<bids.size(); i++){
                        System.out.println(bids.get(i));
                    }


                }

            }

            @Override
            public void onChildChanged(com.google.firebase.database.DataSnapshot dataSnapshot, String s) {
                // get the current uid
                String currUid = FirebaseAuth.getInstance().getCurrentUser().getUid();



                if(currUid.contentEquals(dataSnapshot.getKey())){
                    //需要更新bid list
                    //need more testing over here!!!!!!
                    output.clear();
                    bids.clear();

                    System.out.println("找到对应的Requestor");

                    // test1, if bug then change to add test-null condition for bidLevel (some have bids but some don't)
                    // and just delete the iterable and loop.

                    Iterable<com.google.firebase.database.DataSnapshot> newbids = dataSnapshot.getChildren();
                    for(com.google.firebase.database.DataSnapshot e: newbids) {
                        String time = e.getValue(Bid.class).getTime();
                        String hour = time.substring(0,2);
                        String minute = time.substring(2);


                        output.add("Estimated Money to Pay: " + e.getValue(Bid.class).getMoney() + "\nEstimated Arrival Time: " +
                                hour + ": "+minute+ "\nDeliver From : " +
                                e.getValue(Bid.class).getRunner());

                        bids.add(e.getValue(Bid.class).getMoney() + "=" +
                                e.getValue(Bid.class).getTime() + "=" +
                                e.getValue(Bid.class).getRunner());
                    }
                    //see what's in bids after adding
                    for(int i =0; i<bids.size(); i++){
                        System.out.println(bids.get(i));
                    }


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



        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, output);
        ListView myFirstListView = (ListView)(findViewById(R.id.Bid_List));
        myFirstListView.setAdapter(adapter);
    }


    @Override
    protected void onStart() {
        super.onStart();


    }

    // need modify end

    public void onBackPressed() {
    }

    // String moneySelected;
    // String timeSelected;
    // String runnerSelected;

    /*
    //从confirm page里面接受
    @Override
    public void onTitleSelect(String mon, String tim, String run) {
        moneySelected = mon;
        timeSelected = tim;
        runnerSelected = run;
    }

    public String getMoneySelected(){
        return moneySelected;
    }

    public String getTimeSelected() {
        return timeSelected;
    }

    public String getRunnerSelected() {
        return runnerSelected;
    }

    public List<String>  getDataArrayListFromStatus(){
        return bids;
    }
    public List<String>  getOuputArrayListFromStatus(){
        return output;
    }
*/

}
