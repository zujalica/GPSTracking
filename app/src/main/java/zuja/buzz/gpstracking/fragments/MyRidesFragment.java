package zuja.buzz.gpstracking.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import zuja.buzz.gpstracking.R;

public class MyRidesFragment extends Fragment {

    public final static String TAG = "MY_RIDES_FRAGMENT_TAG";

    public MyRidesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_rides, container, false);
    }

}
