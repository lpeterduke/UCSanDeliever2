package edu.ucsd.cse110.ucsandeliever;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import com.firebase.client.Firebase;
import android.widget.EditText;


/**
 * Created by Peterli on 10/6/16.
 */

public class signupActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Firebase.setAndroidContext(this);
        setContentView(R.layout.signup_layout);
    }

    public void enterHome(View view){

        EditText etName = (EditText) findViewById(R.id.SUName);
        EditText etPid = (EditText) findViewById(R.id.editText6);
        EditText etEmail = (EditText) findViewById(R.id.editText5);
        EditText etPassword = (EditText) findViewById(R.id.editText4);

        String name = etName.getText().toString();
        String id = etPid.getText().toString();
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();

        Firebase myFirebaseRef = new Firebase("https://uc-student-deliver.firebaseio.com/");
        Student student = new Student();
        student.setName(name);
        student.setStudentId(id);
        student.setEmail(email);
        student.setPassword(password);
        myFirebaseRef.child(student.getStudentId()).setValue(student);

        Intent intent = new Intent(this,drawerActivity.class);
        startActivity(intent);


    }




}
