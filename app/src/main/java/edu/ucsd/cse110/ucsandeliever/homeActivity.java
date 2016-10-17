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
import java.util.List;
import android.widget.Toast;

/**
 * Created by Peterli on 10/15/16.
 */

public class homeActivity extends Fragment {

    // temp list for exhibiting fake requests
    private List<String> requests = new ArrayList<>();

    View myView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.home_screen_layout, container, false);

        requests.add("User1 -- Tapioca -- ERC Europe Hall");
        requests.add("User2 -- BurgerKing -- CSE B230");
        requests.add("User3 -- Panda -- Geisel 2nd west");

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
