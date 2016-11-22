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
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import android.widget.Spinner;
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
    private static ArrayList<String> ouputList = new ArrayList<>();
    Spinner spinner;

    private static ArrayAdapter<String> adapter;
    View myView;
    @Nullable


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        myView = inflater.inflate(R.layout.home_screen_layout, container, false);

        spinner = (Spinner) myView.findViewById(R.id.homeSort);
        ArrayAdapter<CharSequence> adapterSort = ArrayAdapter.createFromResource(getActivity(),R.array.SortingOptions,android.R.layout.simple_spinner_item);
        adapterSort.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(adapterSort);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                System.out.println("点击的是： "+position);

                if(position == 1){
                    homeActivity.sortList(1);
                }else if(position == 2){
                    homeActivity.sortList(2);
                }else if(position == 3){
                    homeActivity.sortList(3);
                }else if(position == 4){
                    homeActivity.sortList(4);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        return myView;
    }


    public static void sortList(int order){
        Collections.sort(ouputList, new Sorter(order));
        adapter.notifyDataSetChanged();
    }

    static class Sorter implements Comparator<Object>{

        int order = 0;
        Sorter(int order){
            this.order=order;
        }
        @Override
        public int compare(Object o1, Object o2) {

            System.out.println("sorting 信号："+order);

            if (order == 1){
                if(o1.toString().compareTo(o2.toString())==0) return 0;
                else if (o1.toString().compareTo(o2.toString())<0) {return -1;}
                else return (1);
            }else if (order == 2){
                //用地点

                String dest1 = o1.toString().substring(o1.toString().indexOf("From: ")+6,o1.toString().indexOf("Deliver to: "));
                String dest2 = o2.toString().substring(o2.toString().indexOf("From: ")+6,o2.toString().indexOf("Deliver to: "));

                System.out.println("如果想用地点截取1："+dest1);
                System.out.println("如果想用地点截取2："+dest2);

                if(dest1.compareTo(dest2)==0) return 0;
                else if (dest1.compareTo(dest2)<0)  return -1;
                else return 1;
            }else if (order == 3){
                //用目的地

                String dest1 = o1.toString().substring(o1.toString().indexOf("Deliver to: ")+12,o1.toString().indexOf("Need it by"));
                String dest2 = o2.toString().substring(o2.toString().indexOf("Deliver to: ")+12,o2.toString().indexOf("Need it by"));

                System.out.println("如果想用地点截取1："+dest1);
                System.out.println("如果想用地点截取2："+dest2);

                if(dest1.compareTo(dest2)==0) return 0;
                else if (dest1.compareTo(dest2)<0)  return -1;
                else return 1;
            }else if (order == 4){
                //用时间
                String dest1 = o1.toString().substring(o1.toString().indexOf("Need it by the time at: ")+24);
                String dest2 = o2.toString().substring(o2.toString().indexOf("Need it by the time at: ")+24);
                System.out.println("如果想用地点截取1："+dest1);
                System.out.println("如果想用地点截取2："+dest2);
                if(dest1.compareTo(dest2)==0) return 0;
                else if (dest1.compareTo(dest2)<0)  return -1;
                else return 1;
            }
            return 0;
        }
    }

    @Override
    public void onStart() {
        super.onStart();



        adapter = new ArrayAdapter<String>(getActivity(),
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

                String requestor = orderInfo.substring(0,orderInfo.indexOf('='));
                orderInfo = orderInfo.substring(orderInfo.indexOf('=')+1);
                System.out.println("剩下的OrderInfo: "+orderInfo);

                String orderNum = orderInfo;

                System.out.println("提取出来的item"+item);
                System.out.println("提取出来的item"+res);
                System.out.println("提取出来的item"+dest);
                System.out.println("提取出来的item"+time);
                System.out.println("提取出来的item"+orderNum);
                System.out.println("提取出来的item"+requestor);



                mSelectInterface.onTitleSelect(item,res,dest,time,orderNum,requestor);



                FirebaseAuth auth = FirebaseAuth.getInstance();
                String uid = null;
                if(auth !=null)
                {
                    uid = auth.getCurrentUser().getUid().toString();
                }

                if(uid.contentEquals(requestor)){


                    Toast.makeText(getActivity(),"This is Your Own Order",Toast.LENGTH_SHORT).show();
                    System.out.println("Your Own Order");
                }else {


                    FragmentManager fragmentManager = getFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.content_main, new ViewRequestDetailActivity()).commit();

                }


            }
        });
    }





    //回调Function
    titleSelectInterface mSelectInterface = new titleSelectInterface() {
        @Override
        public void onTitleSelect(String item, String res, String dest, String time, String orderNum, String requestorId) {

        }

    };


    public interface titleSelectInterface{
        public void onTitleSelect(String item, String res, String dest, String time, String orderNum,String requestorId);
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
