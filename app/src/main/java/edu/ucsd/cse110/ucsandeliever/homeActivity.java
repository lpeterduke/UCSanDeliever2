package edu.ucsd.cse110.ucsandeliever;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import android.widget.Toast;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;
import com.firebase.client.ValueEventListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;



import com.firebase.client.Firebase;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Peterli on 10/15/16.
 */

public class homeActivity extends Fragment {


    DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
    DatabaseReference ordersRef = mRootRef.child("orders");


    // temp list for exhibiting fake requests
    private List<String> requests = new ArrayList<>();

    View myView;
    @Nullable


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.home_screen_layout, container, false);



        ordersRef.addValueEventListener(new com.google.firebase.database.ValueEventListener() {



            @Override
            public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {

                //Order order1 = dataSnapshot.getValue(Order.class);

                Iterable<com.google.firebase.database.DataSnapshot> orders = dataSnapshot.getChildren();

                Iterator<com.google.firebase.database.DataSnapshot> iterator = orders.iterator();

               for(int i =0; i<dataSnapshot.getChildrenCount();i++) {


                   com.google.firebase.database.DataSnapshot current = iterator.next();

                   requests.add(current.getValue(Order.class).getRequestor() + " -- " +
                           current.getValue(Order.class).getRestaurants() + " -- " +
                           current.getValue(Order.class).getDestination() + " -- ");

                   //System.out.println(current.getValue(Order.class).getDestination());
                   //System.out.println(current.getValue(Order.class).getItem());
                   //System.out.println(current.getValue(Order.class).getRequestor());
                   //System.out.println(current.getValue(Order.class).getRestaurants());
                   //System.out.println(current.getValue(Order.class).getTime());
               }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });





        //requests.add("User2 -- BurgerKing -- CSE B230");

        //requests.add("User3 -- Panda -- Geisel 2nd west");

        // diff from tutorial30
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, requests);

        ListView myFirstListView = (ListView) (myView.findViewById(R.id.Request_List));
        myFirstListView.setAdapter(adapter);

        myFirstListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.content_main, new ViewRequestDetailActivity()).commit();
            }
        });

        return myView;
    }
}
