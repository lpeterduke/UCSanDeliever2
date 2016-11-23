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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class runner_finishActivity extends Activity {
    Button done;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_runner_finish);

        done = (Button) findViewById(R.id.button5);
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent(runner_finishActivity.this, drawerActivity.class);
                startActivity(intent);

            }

        });
    }
}
                // change the done of order to be true
                //final String currUid = FirebaseAuth.getInstance().getCurrentUser().getUid();
       /*
                Intent i = getIntent();
                Bundle data = i.getExtras();
                final String requester = data.getString("requestor");

                final DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
                final DatabaseReference ordersRef = mRootRef.child("orders");

                ordersRef.addListenerForSingleValueEvent(new com.google.firebase.database.ValueEventListener() {
                    @Override
                    public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {

                        for (com.google.firebase.database.DataSnapshot snapshot : dataSnapshot.getChildren()) {

                            Order order = snapshot.getValue(Order.class);


                            if (order.getDone() == true && order.getRequestorUid().equals(requester))

                                System.out.println("ready to finish on the runner side");


                                Intent intent = new Intent(runner_finishActivity.this,drawerActivity.class);
                                startActivity(intent);


                        }
                    }


                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });



            }

        });

    }

    @Override
    public void onBackPressed() {

    }
    */


