package edu.ucsd.cse110.ucsandeliever;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.View;
import android.widget.Toast;
import android.widget.EditText;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;



import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;
import com.firebase.client.ValueEventListener;

/**
 * Created by Peterli on 10/6/16.
 */

public class loginActivity extends Activity  {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.login_layout );
    }



    public void enterHome(View view){


        EditText etID = (EditText) findViewById(R.id.editText); // get user ID
        EditText etPassword = (EditText) findViewById(R.id.editText2); // get user password
        String id = etID.getText().toString();      // change username to string
        String pword= etPassword.getText().toString();      //  change password to string
        Firebase myFirebaseRef = new Firebase("https://uc-student-deliver.firebaseio.com");
        Query queryRef1 = myFirebaseRef.orderByChild("studentId").equalTo(id);  // check id
        final Query queryRef2= myFirebaseRef.orderByChild("password").equalTo(pword); // password

        final boolean isID = false;    // indicator
        final boolean isPword = false;
        queryRef1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                if (snapshot == null || snapshot.getValue() == null)   // check if id exist.
                    {
                        Toast.makeText(loginActivity.this, "No ID found, please try again",
                                                                  Toast.LENGTH_SHORT).show();
                    }
                else {
                    //Toast.makeText(loginActivity.this, snapshot.getValue().toString(), Toast.LENGTH_SHORT).show();
                    //Toast.makeText(loginActivity.this, "Welcome back", Toast.LENGTH_SHORT).show();
                    queryRef2.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot snapshot) {
                            if (snapshot == null || snapshot.getValue() == null)   // check if id exist.
                            {
                                Toast.makeText(loginActivity.this, "Incorrect password, please try again",
                                        Toast.LENGTH_SHORT).show();
                            }
                            else {
                                Toast.makeText(loginActivity.this, snapshot.getValue().toString(), Toast.LENGTH_SHORT).show();

                                Intent intent = new Intent(loginActivity.this, drawerActivity.class);
                                startActivity(intent);
                            }
                        }

                        @Override
                        public void onCancelled(FirebaseError error) {
                        }
                    });


                }
            }

            @Override
            public void onCancelled(FirebaseError error) {
            }
        });




    }


}
