package com.rent_it_app.rent_it.views;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rent_it_app.rent_it.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class LentItemFragment extends Fragment {


    public LentItemFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_available_item, container, false);
    }

}
