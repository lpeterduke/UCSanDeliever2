package edu.ucsd.cse110.ucsandeliever;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;
import com.firebase.client.ValueEventListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


/**
 * Created by Peterli on 10/6/16.
 */

public class signupActivity extends Activity {




    private EditText etID;
    private EditText etEmail;

    private EditText etPassword;

    private Button signUpBtn;


    final Firebase myFirebaseRef = new Firebase("https://uc-student-deliver.firebaseio.com/");

    private FirebaseAuth mAuth;
    final String userID=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_layout);


        mAuth = FirebaseAuth.getInstance();


        etID  = (EditText) findViewById(R.id.SUName); // get user ID
        etEmail = (EditText) findViewById(R.id.editText5); // get user Email
        etPassword  = (EditText) findViewById(R.id.editText4); // get user password
        signUpBtn = (Button) findViewById(R.id.button);

        signUpBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                enterHome();


            }
        });



    }


    public void enterHome(){


        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();

        if(TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {

            Toast.makeText(signupActivity.this, "input empty",
                    Toast.LENGTH_SHORT).show();


        }else{

            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(
                    new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {





                        EditText etName = (EditText) findViewById(R.id.SUName);
                        EditText etPid = (EditText) findViewById(R.id.editText6);
                        EditText etEmail = (EditText) findViewById(R.id.editText5);
                        EditText etPassword = (EditText) findViewById(R.id.editText4);

                        final String name = etName.getText().toString();
                        final String id = etPid.getText().toString();
                        final String email8 = etEmail.getText().toString();
                        final String password = etPassword.getText().toString();

                        final Firebase myFirebaseRef = new Firebase("https://uc-student-deliver.firebaseio.com/");

                        // check if the id has been used
                        Query queryRef1 = myFirebaseRef.orderByChild("studentId").equalTo(id);  // check id

                        queryRef1.addListenerForSingleValueEvent(new ValueEventListener() {

                            //Push到Data Space
                            @Override
                            public void onDataChange(DataSnapshot snapshot) {
                                if (snapshot == null || snapshot.getValue() == null)   // check if id exist.
                                {
                                    Student student = new Student();
                                    student.setName(name);
                                    student.setStudentId(id);
                                    student.setEmail2(email8);
                                    student.setPassword(password);
                                    student.setRequestingStatus(false);


                                    final  String userEmail = mAuth.getCurrentUser().getEmail().toString();
                                    System.out.println("+++++++++++++++++++"+ userEmail+" ");

                                    final String userID = userEmail.substring(0,userEmail.indexOf('@'));
                                    System.out.println("+++++++++++++++++++"+ userID+" ");


                                    myFirebaseRef.child("users").child(userID).setValue(student);

                                } else {

                                    Toast.makeText(signupActivity.this, "ID already registered, please try another one",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }

                            //没用
                            @Override
                            public void onCancelled(FirebaseError error) {
                            }
                        });




















                        Intent intent = new Intent(signupActivity.this,drawerActivity.class);
                        startActivity(intent);

                        //means user is loged in
                    }else{

                        Toast.makeText(signupActivity.this, "Invalid Id or Password",
                                Toast.LENGTH_SHORT).show();

                    }


                }
            });

        }


    }



    }





