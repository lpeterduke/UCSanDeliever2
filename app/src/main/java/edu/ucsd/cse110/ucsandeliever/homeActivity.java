package edu.ucsd.cse110.ucsandeliever;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import android.widget.Switch;
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
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Peterli on 10/15/16.
 */

public class homeActivity extends Fragment {

    private Button refresh;

    DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
    DatabaseReference ordersRef = mRootRef.child("orders");


    // temp list for exhibiting fake requests
    private ArrayList<String> requests = new ArrayList<>();
    private ArrayList<String> ouputList = new ArrayList<>();

    View myView;
    @Nullable


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        myView = inflater.inflate(R.layout.home_screen_layout, container, false);



        refresh = (Button) myView.findViewById(R.id.HomeRefresh);
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refresh();

            }
        });




        return myView;
    }


    public void refresh(){

        System.out.println("刷新主界面");



        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, ouputList);

        ListView myFirstListView = (ListView) (myView.findViewById(R.id.Request_List));
        myFirstListView.setAdapter(adapter);

        myFirstListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                System.out.println("试图从Home Queue传输的数据：" +requests.get(position));

                String orderInfo = requests.get(position);

                String item = orderInfo.substring(0,orderInfo.indexOf('='));
                orderInfo = orderInfo.substring(orderInfo.indexOf('=')+1);
                System.out.println("剩下的OrderInfo: "+orderInfo);

                String res = orderInfo.substring(0,orderInfo.indexOf('='));
                orderInfo = orderInfo.substring(orderInfo.indexOf('=')+1);
                System.out.println("剩下的OrderInfo: "+orderInfo);

                String dest = orderInfo.substring(0,orderInfo.indexOf('='));
                orderInfo = orderInfo.substring(orderInfo.indexOf('=')+1);
                System.out.println("剩下的OrderInfo: "+orderInfo);

                String time = orderInfo.substring(0,orderInfo.indexOf('='));
                orderInfo = orderInfo.substring(orderInfo.indexOf('=')+1);
                System.out.println("剩下的OrderInfo: "+orderInfo);

                String orderNum = orderInfo;

                System.out.println("提取出来的item"+item);
                System.out.println("提取出来的item"+res);
                System.out.println("提取出来的item"+dest);
                System.out.println("提取出来的item"+time);
                System.out.println("提取出来的item"+orderNum);



                mSelectInterface.onTitleSelect(item,res,dest,time,orderNum);

                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.content_main, new ViewRequestDetailActivity()).commit();




            }
        });
    }


    @Override
    public void onStart() {
        super.onStart();



        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, ouputList);

        ListView myFirstListView = (ListView) (myView.findViewById(R.id.Request_List));
        myFirstListView.setAdapter(adapter);

        myFirstListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                System.out.println("试图从Home Queue传输的数据：" +requests.get(position));

                String orderInfo = requests.get(position);

                String item = orderInfo.substring(0,orderInfo.indexOf('='));
                orderInfo = orderInfo.substring(orderInfo.indexOf('=')+1);
                System.out.println("剩下的OrderInfo: "+orderInfo);

                String res = orderInfo.substring(0,orderInfo.indexOf('='));
                orderInfo = orderInfo.substring(orderInfo.indexOf('=')+1);
                System.out.println("剩下的OrderInfo: "+orderInfo);

                String dest = orderInfo.substring(0,orderInfo.indexOf('='));
                orderInfo = orderInfo.substring(orderInfo.indexOf('=')+1);
                System.out.println("剩下的OrderInfo: "+orderInfo);

                String time = orderInfo.substring(0,orderInfo.indexOf('='));
                orderInfo = orderInfo.substring(orderInfo.indexOf('=')+1);
                System.out.println("剩下的OrderInfo: "+orderInfo);

                String orderNum = orderInfo;

                System.out.println("提取出来的item"+item);
                System.out.println("提取出来的item"+res);
                System.out.println("提取出来的item"+dest);
                System.out.println("提取出来的item"+time);
                System.out.println("提取出来的item"+orderNum);



                mSelectInterface.onTitleSelect(item,res,dest,time,orderNum);

                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.content_main, new ViewRequestDetailActivity()).commit();




            }
        });
    }





    //回调Function
    titleSelectInterface mSelectInterface = new titleSelectInterface() {
        @Override
        public void onTitleSelect(String item, String res, String dest, String time, String orderNum) {

        }

    };


    public interface titleSelectInterface{
        public void onTitleSelect(String item, String res, String dest, String time, String orderNum);
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        requests = ((drawerActivity) activity).getDataArrayListFromDrawer();
        ouputList=((drawerActivity) activity).getOuputArrayListFromDrawer();
        mSelectInterface = (titleSelectInterface) activity;

        System.out.println("Orders从Server接受了");
    }




}
