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
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class ViewRequestDetailActivity extends Fragment implements Button.OnClickListener, drawerActivity.OnMainListener{

    private Button btnClick;
    private String orderNumberInterested;
    DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
    DatabaseReference ordersRef = mRootRef.child("orders");


    private TextView userName;
    private TextView res;
    private TextView dest;
    private TextView item;
    private TextView time;


    private String  userNameFromHome;
    private String resFromHome;
    private String destFromHomw;
    private String itemFromHome;
    private String timeFromHome;


    View myView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        myView = inflater.inflate(R.layout.activity_view_request_detail, container, false);

        //userName = (TextView) myView.findViewById(R.id.View_User);
        res = (TextView) myView.findViewById(R.id.View_Res);
        dest = (TextView) myView.findViewById(R.id.View_dest);
        item = (TextView) myView.findViewById(R.id.View_item);
        time = (TextView) myView.findViewById(R.id.View_time);



        //裁剪从Drawer传输过来的数据
        

        res.setText(resFromHome);
        dest.setText(destFromHomw);
        item.setText(itemFromHome);
        time.setText(timeFromHome);
        //userName.setText(orderNumberInterested);



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

       // orderNumberInterested = str;

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        itemFromHome = ((drawerActivity) activity).getItemSelected();
        resFromHome= ((drawerActivity) activity).getResSelected();
        destFromHomw = ((drawerActivity) activity).getDestSelected();
        timeFromHome = ((drawerActivity) activity).getTimeSelected();
        orderNumberInterested = ((drawerActivity) activity).getOrderNumSelected();

    }
}