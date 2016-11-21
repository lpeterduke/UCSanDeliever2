package edu.ucsd.cse110.ucsandeliever;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class requestor_finishActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_requestor_finish);

    }

    public void welcomeSystem(View view){

        String button_text;
        button_text = ((Button) view).getText().toString();
        if(button_text.equals("Done")){

            // go to main page

        }else if(button_text.equals("comment")){

            // finishing page
        }

    }

    @Override
    public void onBackPressed() {

    }


}
