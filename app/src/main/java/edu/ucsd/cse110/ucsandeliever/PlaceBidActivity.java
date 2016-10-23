package edu.ucsd.cse110.ucsandeliever;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class PlaceBidActivity extends android.app.Fragment implements Button.OnClickListener{


    private Button btnClick;
    View myView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.activity_place_bid, container, false);

        btnClick = (Button) myView.findViewById(R.id.button4);
        btnClick.setOnClickListener(this);

        return myView;
    }

    public void onClick (View v){
        int id = v.getId();

        if (id == R.id.button4) {
            Intent intent = new Intent(getActivity(), userRunnerWaitingActivity.class);
            startActivity(intent);
            (getActivity()).overridePendingTransition(0,0);
        }
    }
}
