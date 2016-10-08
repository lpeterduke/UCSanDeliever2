package edu.ucsd.cse110.ucsandeliever;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.support.v7.widget.Toolbar;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }



    public void welcomeSystem(View view){

        String button_text;
        button_text = ((Button) view).getText().toString();
        if(button_text.equals("Returning Customer")){

            Intent intent = new Intent(this,loginActivity.class);
            startActivity(intent);

        }else if(button_text.equals("Become A New Member")){

            Intent intent = new Intent(this,signupActivity.class);
            startActivity(intent);
        }

    }
}
