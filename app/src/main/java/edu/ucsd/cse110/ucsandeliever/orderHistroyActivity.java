package edu.ucsd.cse110.ucsandeliever;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


/**
 * Created by Administrator on 2016/10/9.
 */

public class orderHistroyActivity extends Fragment {

    DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
    DatabaseReference ordersRef = mRootRef.child("orders");

    private List<String> requests = new ArrayList<>();
    View myView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.orderhistroy_layout, container, false);



        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_list_item_1, requests);

        ListView myFirstListView = (ListView) (myView.findViewById(R.id.History_List));

        myFirstListView.setAdapter(adapter);

        return myView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        requests = ((drawerActivity) activity).getOrderHistory();

    }

    public void onBackPressed() {
        Intent intent = new Intent(getActivity(),drawerActivity.class);
        startActivity(intent);
    }


}