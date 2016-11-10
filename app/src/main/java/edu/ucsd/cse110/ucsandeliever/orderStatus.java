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

    private Button refresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.orderstatus_layout);

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

        // refersh button. Better if it can update in real time
        refresh = (Button)findViewById(R.id.StatusRefresh);
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refresh();

            }
        });
    }

    public void refresh(){
        System.out.println("refresh order_status");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, output);
        ListView myFirstListView = (ListView)(findViewById(R.id.Bid_List));
        myFirstListView.setAdapter(adapter);
    }


    @Override
    protected void onStart() {
        super.onStart();

        DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference ordersRef = mRootRef.child("orders");

        ordersRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(com.google.firebase.database.DataSnapshot dataSnapshot, String s) {

                // get the current uid
                String currUid = FirebaseAuth.getInstance().getCurrentUser().getUid();
                
                if(currUid.contentEquals(dataSnapshot.getValue(Order.class).getRequestorUid())){
                    //需要更新bid list
                    //need more testing over here!!!!!!
                    output.clear();
                    bids.clear();


                    // test1, if bug then change to add test-null condition for bidLevel (some have bids but some don't)
                    // and just delete the iterable and loop.
                    if(dataSnapshot.child("bids") != null){
                        com.google.firebase.database.DataSnapshot bidLevel = dataSnapshot.child("bids");

                        Iterable<com.google.firebase.database.DataSnapshot> newbids = bidLevel.getChildren();
                        for(com.google.firebase.database.DataSnapshot e: newbids) {
                            output.add("Money: " + e.getValue(Bid.class).getMoney() + "\nTime: " +
                                    e.getValue(Bid.class).getTime() + "\nWho : " +
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
            }

            @Override
            public void onChildChanged(com.google.firebase.database.DataSnapshot dataSnapshot, String s) {

                // get the current uid
                String currUid = FirebaseAuth.getInstance().getCurrentUser().getUid();


                // need getRequestorUID
                if(currUid.contentEquals(dataSnapshot.getValue(Order.class).getRequestorUid())){
                    //need more testing over here!!!!!!
                    output.clear();
                    bids.clear();

                    if(dataSnapshot.child("bids") != null){
                        com.google.firebase.database.DataSnapshot bidLevel = dataSnapshot.child("bids");

                        Iterable<com.google.firebase.database.DataSnapshot> newbids = bidLevel.getChildren();
                        for(com.google.firebase.database.DataSnapshot e: newbids) {
                            output.add("Money: " + e.getValue(Bid.class).getMoney() + "\nTime: " +
                                    e.getValue(Bid.class).getTime() + "\nWho : " +
                                    e.getValue(Bid.class).getRunner());

                            bids.add(e.getValue(Bid.class).getMoney() + "=" +
                                    e.getValue(Bid.class).getTime() + "=" +
                                    e.getValue(Bid.class).getRunner());
                        }
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
