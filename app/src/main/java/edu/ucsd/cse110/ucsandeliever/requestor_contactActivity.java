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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

public class requestor_contactActivity extends Activity {

    private String balance1 = "";
    private String balance2 = "";

    private Button reveived;
    private Button contRunner;

    private TextView to;
    private TextView restaurant;
    private TextView item;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_requestor_contact);


        //order information
        to = (TextView) findViewById(R.id.textView38);
        restaurant = (TextView) findViewById(R.id.textView39);
        item = (TextView) findViewById(R.id.textView40);
        FirebaseDatabase.getInstance().getReference().child("orders").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Order order = snapshot.getValue(Order.class);
                    if (FirebaseAuth.getInstance().getCurrentUser().getUid().contentEquals(order.getRequestorUid())) {
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

        reveived = (Button) findViewById(R.id.buttonReceived);
        reveived.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                // finishing page
                DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
                DatabaseReference ordersRef = mRootRef.child("orders");

                System.out.println("is about to end");


                FirebaseAuth mAuth = FirebaseAuth.getInstance();

                FirebaseUser currentUser = mAuth.getCurrentUser();


                final String requester = currentUser.getUid();

                Intent i = getIntent();
                Bundle data = i.getExtras();
                final String runner = data.getString("runner");
                System.out.print("runner id ----------- " + runner);
                int changedValue = 0;
                //DatabaseReference ref1 =FirebaseDatabase.getInstance().getReference().child("users").child(runner).child("balance");
                FirebaseDatabase.getInstance().getReference().child("users").child(runner).child("runnerStatusIndicator").setValue(false);

                DatabaseReference ref2 =FirebaseDatabase.getInstance().getReference().child("users").child(requester).child("balance");
                FirebaseDatabase.getInstance().getReference().child("users").child(requester).child("alreadyPick").setValue(false);
                FirebaseDatabase.getInstance().getReference().child("users").child(requester).child("requesting").setValue(false);




                // change the done of order to be true

                final DatabaseReference ordersRef2 = mRootRef.child("orders");

                ordersRef2.addListenerForSingleValueEvent(new com.google.firebase.database.ValueEventListener() {
                    @Override
                    public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {

                        for (com.google.firebase.database.DataSnapshot snapshot : dataSnapshot.getChildren()) {

                            Order order = snapshot.getValue(Order.class);


                            if (order.getDone() == false && order.getRequestorUid().equals(requester))

                            snapshot.getRef().child("done").setValue(true);


                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });




                Intent intent = new Intent(requestor_contactActivity.this,requestor_finishActivity.class);
                startActivity(intent);

            }
        });


        contRunner = (Button) findViewById(R.id.buttonContRunner);
        contRunner.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {


                System.out.println("is about to chat");
                // chatting page for Pan
                Intent i = getIntent();
                Bundle data = i.getExtras();
                String Runneuid = data.getString("runner");

                String currUid = FirebaseAuth.getInstance().getCurrentUser().getUid();

                Intent k = new Intent(requestor_contactActivity.this,UserList.class);
                Bundle b = new Bundle();
                b.putString("runner", Runneuid);
                b.putString("currRe", currUid);
                k.putExtras(b);
                startActivity(k);
            }
        });


    }


    @Override
    public void onBackPressed() {

    }

    }


