package edu.ucsd.cse110.ucsandeliever;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
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


import com.firebase.client.AuthData;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;
import com.firebase.client.ValueEventListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by Peterli on 10/6/16.
 */

public class loginActivity extends Activity  {


    public FirebaseUser currentUser;

    private EditText etID;
    private EditText etPassword;

    private Button loginBtn;


    final Firebase myFirebaseRef = new Firebase("https://uc-student-deliver.firebaseio.com/");

    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout );


        mAuth = FirebaseAuth.getInstance();

         currentUser = mAuth.getCurrentUser();

        etID  = (EditText) findViewById(R.id.editText); // get user ID
        etPassword  = (EditText) findViewById(R.id.editText2); // get user password
        loginBtn = (Button) findViewById(R.id.login2);

        loginBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                enterHome();
            }
        });


    }




    public void enterHome(){

        String email = etID.getText().toString();
        String password = etPassword.getText().toString();


        if(TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {

            Toast.makeText(loginActivity.this, "input empty",
                    Toast.LENGTH_SHORT).show();


        }else{

            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {

                        Intent intent = new Intent(loginActivity.this,drawerActivity.class);
                        startActivity(intent);

                        //means user is loged in
                    }else{

                        Toast.makeText(loginActivity.this, "Invalid Id or Password",
                                Toast.LENGTH_SHORT).show();

                    }


                }
            });

        }


    }


}
