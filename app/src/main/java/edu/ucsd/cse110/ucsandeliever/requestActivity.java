package edu.ucsd.cse110.ucsandeliever;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatAutoCompleteTextView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;
import android.widget.Button;

/**
 * Created by Administrator on 2016/10/8.
 */

public class requestActivity extends Fragment implements Button.OnClickListener{

    private Button btnClick;
    View myView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.request_layout, container, false);

        btnClick = (Button) myView.findViewById(R.id.button2);
        btnClick.setOnClickListener(this);

        return myView;
    }

    public void onClick (View v){
        int id = v.getId();
        FragmentManager fragmentManager = getFragmentManager();


        if (id == R.id.button2) {
            // Handle the camera action
            fragmentManager.beginTransaction().replace(R.id.content_main, new homeActivity()).commit();

        }
    }



/*
    "@+id/button2"

    public void
    int id = item.getItemId();
    FragmentManager fragmentManager = getFragmentManager();




    if (id == R.id.nav_home) {
        // Handle the camera action
        fragmentManager.beginTransaction().replace(R.id.content_main, new homeActivity()).commit();

    }
*/
}
