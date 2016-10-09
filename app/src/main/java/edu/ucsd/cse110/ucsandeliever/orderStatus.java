package edu.ucsd.cse110.ucsandeliever;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.view.View;


/**
 * Created by Administrator on 2016/10/8.
 */

public class orderStatus extends Fragment{

    View myView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.orderstatus_layout, container, false);
        return myView;
    }
}
