package edu.ucsd.cse110.ucsandeliever;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatAutoCompleteTextView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

/**
 * Created by Administrator on 2016/10/8.
 */

public class requestActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.request_layout);

        Toolbar my_toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(my_toolbar);
        getSupportActionBar().setIcon(R.drawable.acct_info);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        /*
        switch (item.getItemId()){
            case R.id.menu_2:
                Toast.makeText(requestActivity.this, "My Order Status",Toast.LENGTH_SHORT).show();
                Intent intent2 = new Intent(this,orderStatus.class);
                startActivity(intent2);
                break;

            case R.id.menu_3:
                Toast.makeText(requestActivity.this, "Order history",Toast.LENGTH_SHORT).show();
                Intent intent3 = new Intent(this,orderHistroyActivity.class);
                startActivity(intent3);
                break;
        }
        */
        return super.onOptionsItemSelected(item);
    }
}
