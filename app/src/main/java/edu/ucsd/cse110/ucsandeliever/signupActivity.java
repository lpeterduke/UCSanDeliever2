package edu.ucsd.cse110.ucsandeliever;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;


/**
 * Created by Peterli on 10/6/16.
 */

public class signupActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_layout);
    }

    public void enterHome(View view){
        Intent intent = new Intent(this,homeActivity.class);
        startActivity(intent);


    }




}
