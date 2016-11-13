package edu.ucsd.cse110.ucsandeliever;

/**
 * Created by PANYUE on 16/10/11.
 */

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.app.FragmentManager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Objects;


public class drawerActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,homeActivity.titleSelectInterface {


    private ArrayList<String> requests = new ArrayList<>();
    private ArrayList<String> ouput = new ArrayList<>();

    private  int i=0;
    private Hashtable<Integer,String> requestMaps = new Hashtable<Integer, String>();

    private List<String> orderHistory = new ArrayList<>();

    private String balance;
    public FirebaseUser currentUser;

    private FirebaseAuth mAuth;
    final String userID = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.draweractivity_main);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);





    }

    @Override
    protected void onStart() {
        super.onStart();

        DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference ordersRef = mRootRef.child("orders");


        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        FirebaseAuth mAuth = FirebaseAuth.getInstance();


        final  String userEmail = mAuth.getCurrentUser().getEmail().toString();

        final String userID = userEmail.substring(0,userEmail.indexOf('@'));


        DatabaseReference ref =FirebaseDatabase.getInstance().getReference().child("users").child(userID).child("balance");

        ref.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
            @Override
            public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {

                System.out.println("余额变更");
                String msg = dataSnapshot.getValue(String.class);
                System.out.println("msg: " + msg);
                balance = msg;


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });




        ordersRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(com.google.firebase.database.DataSnapshot dataSnapshot, String s) {

                System.out.println("数据抓包");

                java.util.Calendar calendar = java.util.Calendar.getInstance();
                int year = calendar.get(java.util.Calendar.YEAR);
                int month = calendar.get(java.util.Calendar.MONTH);
                int day = calendar.get(java.util.Calendar.DAY_OF_MONTH);

                String dayS=Integer.toString(day);
                if(day<10){
                    dayS = "0"+Integer.toString(day);
                }

                int hour = calendar.get(Calendar.HOUR_OF_DAY);
                int minutes = calendar.get(Calendar.MINUTE);
                //  System.out.println("当下年"+ year);
                //   System.out.println("当下月"+ month);
                //    System.out.println("当下天"+ day);

                String hourS = Integer.toString(day);
                if(hour<10){
                    hourS = "0"+Integer.toString(hour);
                }

                //     System.out.println("当下小时"+ hourS);
                //     System.out.println("当下分钟"+ minutes);


                String cday = Integer.toString(year)+
                        Integer.toString(month)+ dayS;

                String ctime =hourS +
                        Integer.toString(minutes);


                //  System.out.println("当下1"+ cday);
                //    System.out.println("当下2"+ ctime);

                int currDay=Integer.parseInt(cday);
                int currTime = Integer.parseInt(ctime);
                System.out.println("当下时间"+ cday+currTime);






                String  orderTime = dataSnapshot.getValue(Order.class).getTime();
                String orderDate = orderTime.substring(0,8);
                String orderT = orderTime.substring(8);

                //    System.out.println("创建时间："+orderTime);

                int orderTimeint = Integer.parseInt(orderT);
                int orderDay = Integer.parseInt(orderDate);





                FirebaseAuth auth = FirebaseAuth.getInstance();
                String uid = null;
                if(auth !=null)
                {
                    uid = auth.getCurrentUser().getUid().toString();
                }


                if(uid.contentEquals(dataSnapshot.getValue(Order.class).getRequestorUid()))
                {
                    orderHistory.add("Getting: "+ dataSnapshot.getValue(Order.class).getItem() + "\nFrom: " +
                            dataSnapshot.getValue(Order.class).getRestaurants() + "\nDeliver to: " +
                            dataSnapshot.getValue(Order.class).getDestination() + "\nNeed it by the time at: " +
                            orderT.substring(0,2)+": "+orderT.substring(2) );
                }




                System.out.println("双判定：" +(orderTimeint > currTime) + "|" + (orderDay >= currDay) );


                if(((orderTimeint > currTime) && (orderDay == currDay)) || orderDay > currDay) {


                    System.out.println("注入Request");
                    ouput.add("Getting: "+ dataSnapshot.getValue(Order.class).getItem() + "\nFrom: " +
                            dataSnapshot.getValue(Order.class).getRestaurants() + "\nDeliver to: " +
                            dataSnapshot.getValue(Order.class).getDestination() + "\nNeed it by the time at: " +
                            orderT.substring(0,2)+": "+orderT.substring(2) );


                    requestMaps.put(i,dataSnapshot.getValue(Order.class).getOrderNumber());
                    i++;





                    requests.add(dataSnapshot.getValue(Order.class).getItem() + "=" +
                            dataSnapshot.getValue(Order.class).getRestaurants() + "=" +
                            dataSnapshot.getValue(Order.class).getDestination() + "=" +
                            dataSnapshot.getValue(Order.class).getTime()+ "=" +
                            dataSnapshot.getValue(Order.class).getRequestorUid()+ "=" +
                            dataSnapshot.getValue(Order.class).getOrderNumber());

                }


            }

            @Override
            public void onChildChanged(com.google.firebase.database.DataSnapshot dataSnapshot, String s) {

                System.out.println("数据更新");

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

                int hour = calendar.get(Calendar.HOUR_OF_DAY);
                int minutes = calendar.get(Calendar.MINUTE);
                //  System.out.println("当下年"+ year);
                //   System.out.println("当下月"+ month);
                //    System.out.println("当下天"+ day);

                String hourS = Integer.toString(hour);
                if(hour<10){
                    hourS = "0"+Integer.toString(hour);
                }

                String minuteS = Integer.toString(minutes);
                if(minutes<10){
                    minuteS = "0"+Integer.toString(minutes);
                }

                //     System.out.println("当下小时"+ hourS);
                //     System.out.println("当下分钟"+ minutes);


                String cday = Integer.toString(year)+
                        monthS+ dayS;

                String ctime =hourS + minuteS;


              //   System.out.println("当下1:"+ cday);
                //   System.out.println("当下2:"+ ctime);

                int currDay=Integer.parseInt(cday);
                int currTime = Integer.parseInt(ctime);

                System.out.println("当下时间"+ cday+ctime);



                String  orderTime = dataSnapshot.getValue(Order.class).getTime();
                String orderDate = orderTime.substring(0,8);
                String orderT = orderTime.substring(8);

                System.out.println("更改的Order的"+ orderDate+orderT);


                int orderDay = Integer.parseInt(orderDate);
                int orderTimeint = Integer.parseInt(orderT);

                if(orderTimeint<1000){


                }

              //  System.out.println("用来做对比的日期： "+ orderDay);
             //   System.out.println("用来做对比的时间： "+ orderTimeint);


                //得到需要删除的Index然后删除这个order
                String oD = dataSnapshot.getValue(Order.class).getOrderNumber();
                int indexofChange=0;
                for(Map.Entry<Integer,String> orde: requestMaps.entrySet()){
                    if (oD.equals(orde.getValue())) {
                        indexofChange =   orde.getKey();
                    }
                }
                System.out.println(requestMaps);
                if(requestMaps.contains(oD)) {
                    System.out.println("数据更改的Order#被删掉了：" + indexofChange);
                    requestMaps.remove(indexofChange);
                    System.out.println("还剩下： "+requestMaps);
                    requests.remove(indexofChange);
                    // System.out.println(requests);
                    ouput.remove(indexofChange);
                }else{
                    System.out.println("数据更改的order现在没有显示");

                }


                // System.out.println("三判定2：" +(orderTimeint > currTime) + "|" + (orderDay >= currDay) + "|" +(requestMaps.contains(oD)));



                if(((orderTimeint > currTime) && (orderDay == currDay)) || orderDay > currDay) {


                    System.out.println("通过日期判定，注入Order");
                    ouput.add("Getting: "+ dataSnapshot.getValue(Order.class).getItem() + "\nFrom: " +
                            dataSnapshot.getValue(Order.class).getRestaurants() + "\nDeliver to: " +
                            dataSnapshot.getValue(Order.class).getDestination() + "\nNeed it by the time at: " +
                            orderT.substring(0,2)+": "+orderT.substring(2) );

                    requestMaps.put(i,dataSnapshot.getValue(Order.class).getOrderNumber());
                    i++;


                    requests.add(dataSnapshot.getValue(Order.class).getItem() + "=" +
                            dataSnapshot.getValue(Order.class).getRestaurants() + "=" +
                            dataSnapshot.getValue(Order.class).getDestination() + "=" +
                            dataSnapshot.getValue(Order.class).getTime()+ "=" +
                            dataSnapshot.getValue(Order.class).getRequestorUid()+ "=" +
                            dataSnapshot.getValue(Order.class).getOrderNumber());

                }

                System.out.println("新的OrderList："+requests);

                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.home_main, new homeActivity()).commit();




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




    public void prioritizeOwnOrder(){
        FirebaseAuth mAuth = FirebaseAuth.getInstance();

        final  String userEmail = mAuth.getCurrentUser().getEmail().toString();
        final String userID = userEmail.substring(0,userEmail.indexOf('@'));

        System.out.println("");


    }
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        FragmentManager fragmentManager = getFragmentManager();




        if (id == R.id.nav_home) {
            // Handle the camera action
            fragmentManager.beginTransaction().replace(R.id.content_main, new homeActivity()).commit();


        } else if (id == R.id.order_history_layout) {
            fragmentManager.beginTransaction().replace(R.id.content_main, new orderHistroyActivity()).commit();
        } else if (id == R.id.nav_request) {

            fragmentManager.beginTransaction().replace(R.id.content_main, new requestActivity()).commit();

        } else if (id == R.id.log_out) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);

        }
        else if (id == R.id.Account) {
            fragmentManager.beginTransaction().replace(R.id.content_main, new balanceActivity()).commit();


        }else if (id == R.id.orderStatus) {

            Intent intent = new Intent(drawerActivity.this,orderStatus.class);
            startActivity(intent);

        }
        else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }





    //向viewdetailed发射 创建
    private OnMainListener mainListener = new OnMainListener() {
        @Override
        public void onMainAction(String str) {

        }
    };

    // 接口
    public interface OnMainListener {
        public void onMainAction(String str);
    }



    String strFromHome;
    String itemSelected;
    String resSelected;
    String destSelected;
    String timeSelected;
    String orderNumSelected;
    String requestorUid;



    //从Home里面接受
    @Override
    public void onTitleSelect(String item, String res, String dest, String time,String orderNum, String requestor) {
        itemSelected=item;
        resSelected = res;
        destSelected=dest;
        timeSelected=time;
        orderNumSelected=orderNum;
        requestorUid = requestor;
    }

    public String getRequestorUid() {
        return requestorUid;
    }

    public List<String> getOrderHistory() {
        return orderHistory;
    }


    public String getBalance() {
        return balance;
    }

    public String getItemSelected(){
        return itemSelected;
    }

    public String getResSelected() {
        return resSelected;
    }

    public String getDestSelected() {
        return destSelected;
    }

    public String getOrderNumSelected() {
        return orderNumSelected;
    }

    public String getTimeSelected() {
        return timeSelected;
    }

    public ArrayList<String>  getDataArrayListFromDrawer(){
        return requests;
    }
    public ArrayList<String>  getOuputArrayListFromDrawer(){
        return ouput;
    }

}
