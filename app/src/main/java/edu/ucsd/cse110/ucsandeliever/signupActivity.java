package edu.ucsd.cse110.ucsandeliever;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

public class signupActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Firebase.setAndroidContext(this);
        setContentView(R.layout.signup_layout);
    }

    public void enterHome(View view){

        EditText etName = (EditText) findViewById(R.id.SUName);
        EditText etPid = (EditText) findViewById(R.id.editText6);
        EditText etEmail = (EditText) findViewById(R.id.editText5);
        EditText etPassword = (EditText) findViewById(R.id.editText4);

        final String name = etName.getText().toString();
        final String id = etPid.getText().toString();
        final String email = etEmail.getText().toString();
        final String password = etPassword.getText().toString();

        final Firebase myFirebaseRef = new Firebase("https://uc-student-deliver.firebaseio.com/");

        // check if the id has been used
        Query queryRef1 = myFirebaseRef.orderByChild("studentId").equalTo(id);  // check id

        queryRef1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                if (snapshot == null || snapshot.getValue() == null)   // check if id exist.
                {
                    Student student = new Student();
                    student.setName(name);
                    student.setStudentId(id);
                    student.setEmail(email);
                    student.setPassword(password);




                    myFirebaseRef.child("users").setValue(student);



                    Intent intent = new Intent(signupActivity.this,drawerActivity.class);
                    startActivity(intent);
                }

                else
                {

                    Toast.makeText(signupActivity.this, "ID already registered, please try another one",
                            Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(FirebaseError error) {
            }
        });
        /*
        Student student = new Student();
        student.setName(name);
        student.setStudentId(id);
        student.setEmail(email);
        student.setPassword(password);
        myFirebaseRef.child(student.getStudentId()).setValue(student);

        Intent intent = new Intent(this,drawerActivity.class);
        startActivity(intent);
*/

    }




}
