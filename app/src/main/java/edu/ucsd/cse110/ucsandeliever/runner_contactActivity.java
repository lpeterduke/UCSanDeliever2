package edu.ucsd.cse110.ucsandeliever;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class runner_contactActivity extends Activity {

    Button contact;

    private TextView to;
    private TextView restaurant;
    private TextView item;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_runner_contact);

        //order information

        to = (TextView) findViewById(R.id.textView42);
        restaurant = (TextView) findViewById(R.id.textView43);
        item = (TextView) findViewById(R.id.textView44);
        FirebaseDatabase.getInstance().getReference().child("orders").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Order order = snapshot.getValue(Order.class);
                    Intent i = getIntent();
                    Bundle data = i.getExtras();
                    String requestor = data.getString("requestorGet");
                    if (requestor.contentEquals(order.getRequestorUid())) {
                        to.setText(order.getDestination());
                        restaurant.setText(order.getRestaurants());
                        item.setText(order.getItem());
                    }
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        contact = (Button) findViewById(R.id.button12);
        contact.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String currUid = FirebaseAuth.getInstance().getCurrentUser().getUid();

                Intent i = getIntent();
                Bundle data = i.getExtras();
                String requestor = data.getString("requestorGet");


                Bundle b = new Bundle();
                b.putString("runner", currUid);
                b.putString("requestor", requestor);
                // chatting page for Pan
                // to change because chat needs to get done first - Zihan
                Intent intent = new Intent(runner_contactActivity.this, UserList.class);

                intent.putExtras(b);
                startActivity(intent);
            }
        });


        DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
        final DatabaseReference usersRef = mRootRef.child("users");
        //DatabaseReference currRef = usersRef.child(currUid);
        usersRef.addChildEventListener(new ChildEventListener() {

            @Override
            public void onChildAdded(com.google.firebase.database.DataSnapshot dataSnapshot, String s) {


                    Intent i = getIntent();
                    Bundle data = i.getExtras();
                    String requestor = data.getString("requestorGet");

                    // make sure that the requstor is not requesting anymore
                    if(dataSnapshot.getValue(Student.class).getuid().contentEquals(requestor)) {
                        if (dataSnapshot.getValue(Student.class).getRequesting() == false) {
                            System.out.println("requester is not requesting");


                            // to change because chat needs to get done first - Zihan
                            // Intent intent = new Intent(userRunnerWaitingActivity.this, Chat.class);
                            // startActivity(intent);
                            //
                            //go to runner_contact page - he chang

                            Intent oldIntent = getIntent();
                            Bundle oldData = oldIntent.getExtras();
                            String payment = oldData.getString("payment");
                            String balance = oldData.getString("balance");
                            int newBalance = Integer.parseInt(balance) + Integer.parseInt(payment);

                            FirebaseDatabase.getInstance().getReference().child("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("balance").setValue(Integer.toString(newBalance));

                            Bundle o = new Bundle();
                            o.putString("requestor", requestor);
                            Intent newintent = new Intent(runner_contactActivity.this,runner_finishActivity.class);
                            newintent.putExtras(o);
                            startActivity(newintent);
                        }
                    }


                }




            @Override
            public void onChildChanged(com.google.firebase.database.DataSnapshot dataSnapshot, String s) {
                Intent i = getIntent();
                Bundle data = i.getExtras();
                String requestor = data.getString("requestorGet");

                // make sure that the requstor is not requesting anymore
                if(dataSnapshot.getValue(Student.class).getuid().contentEquals(requestor)) {
                    if (dataSnapshot.getValue(Student.class).getRequesting() == false) {
                        System.out.println("requester is not requesting");


                        // to change because chat needs to get done first - Zihan
                        // Intent intent = new Intent(userRunnerWaitingActivity.this, Chat.class);
                        // startActivity(intent);
                        //
                        //go to runner_contact page - he chang

                        Intent oldIntent = getIntent();
                        Bundle oldData = oldIntent.getExtras();
                        String payment = oldData.getString("payment");
                        String balance = oldData.getString("balance");

                        int newBalance = Integer.parseInt(balance) + Integer.parseInt(payment);


                        FirebaseDatabase.getInstance().getReference().child("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("balance").setValue(Integer.toString(newBalance));

                        Bundle o = new Bundle();
                        o.putString("requestor", requestor);
                        o.putString("payment",payment);
                        Intent newintent = new Intent(runner_contactActivity.this,runner_finishActivity.class);
                        newintent.putExtras(o);
                        startActivity(newintent);
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

    @Override
    public void onBackPressed() {

    }

}
