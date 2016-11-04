package edu.ucsd.cse110.ucsandeliever;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class ViewRequestDetailActivity extends Fragment implements Button.OnClickListener, drawerActivity.OnMainListener{

    private Button btnClick;
    private String mTv;

    View myView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.activity_view_request_detail, container, false);
        System.out.println("------------"+mTv);
        btnClick = (Button) myView.findViewById(R.id.button3);
        btnClick.setOnClickListener(this);
        return myView;
    }

    public void onClick (View v){
        int id = v.getId();
        FragmentManager fragmentManager = getFragmentManager();
        if (id == R.id.button3) {
            // Handle the camera action
            fragmentManager.beginTransaction().replace(R.id.content_main, new PlaceBidActivity()).commit();
        }
    }

    @Override
    public void onMainAction(String str) {
        System.out.println("---View---------"+str);

        mTv = str;

        System.out.println("---View---------"+mTv);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mTv = ((drawerActivity) activity).getStrFromHome();
    }
}