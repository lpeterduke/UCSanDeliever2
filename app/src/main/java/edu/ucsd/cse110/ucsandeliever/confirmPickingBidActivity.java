package edu.ucsd.cse110.ucsandeliever;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class confirmPickingBidActivity extends AppCompatActivity {

    private String monFromStatus;
    private String timFromStatus;
    private String runFromStatus;

    private TextView moneyShow;
    private TextView timeShow;
    private TextView runnerShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_picking_bid);

        // get data from last page
        Intent i = getIntent();
        Bundle data = i.getExtras();
        monFromStatus = data.getString("moneyGet");
        timFromStatus = data.getString("timeGet");
        runFromStatus = data.getString("runnerGet");

        // find the textviews
        moneyShow = (TextView) findViewById(R.id.moneyText);
        timeShow = (TextView) findViewById(R.id.timeText);
        runnerShow = (TextView) findViewById(R.id.runnerText);

        // assign values to the textviews
        moneyShow.setText(monFromStatus);
        timeShow.setText(timFromStatus);
        runnerShow.setText(runFromStatus);

        //

    }

    public void confirmSystem(View view){

        String button_text;
        button_text = ((Button) view).getText().toString();
        if(button_text.equals("Yes, choose this runner")){

            // look for runner from firebase using runFromStatus
            // change that runner's status to true
            DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
            DatabaseReference usersRef = mRootRef.child("users");
            DatabaseReference runnerRef = usersRef.child(runFromStatus);
            DatabaseReference indicatorRef = runnerRef.child("runnerStatusIndicator");
            indicatorRef.setValue(true);

            //change the user's status to already pick to true
            String currUid = FirebaseAuth.getInstance().getCurrentUser().getUid();
            DatabaseReference requesterRef = usersRef.child(currUid);
            DatabaseReference alreadyPickRef = requesterRef.child("alreadyPick");
            alreadyPickRef.setValue(true);


            // to change because chat needs to get done first - Zihan
           // Intent intent = new Intent(this,Chat.class);
            //startActivity(intent);

            // now go to the requestor_contact page - he chang
            // for chatting info
            Intent k = new Intent(confirmPickingBidActivity.this, requestor_contactActivity.class);
            Bundle b = new Bundle();

            b.putString("runnerGet", runFromStatus);
            k.putExtras(b);
            startActivity(k);

        }else if(button_text.equals("No, go back to last page")){

            Intent intent = new Intent(this,orderStatus.class);
            startActivity(intent);
        }

    }

    @Override
    public void onBackPressed() {
    }

/*

    //回调Function
    titleSelectInterface aSelectInterface = new titleSelectInterface() {
        @Override
        public void onTitleSelect(String mon, String tim, String run) {

        }

    };


    public interface titleSelectInterface{
        public void onTitleSelect(String mon, String tim, String run);
    }



    public void onAttach(Activity activity) {
        monFromStatus = ((orderStatus) activity).getMoneySelected();
        timFromStatus = ((orderStatus) activity).getTimeSelected();
        runFromStatus = ((orderStatus) activity).getRunnerSelected();
    }

*/
}
