package edu.ucsd.cse110.ucsandeliever;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

/**
 * Created by Administrator on 2016/10/9.
 */

public class orderHistroyActivity extends AppCompatActivity{

    @Override
    public void onCreate(Bundle savedInstanceState) {

        setContentView(R.layout.orderhistroy_layout);

        super.onCreate(savedInstanceState);

        Toolbar my_toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(my_toolbar);
        getSupportActionBar().setIcon(R.drawable.acct_info);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_1:
                Toast.makeText(orderHistroyActivity.this, "Request Order",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this,requestActivity.class);
                startActivity(intent);
                break;

            case R.id.menu_2:
                Toast.makeText(orderHistroyActivity.this, "My Order Status",Toast.LENGTH_SHORT).show();
                Intent intent2 = new Intent(this,orderStatus.class);
                startActivity(intent2);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
