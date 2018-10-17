package com.appexpress.gula;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.appexpress.gula.R;
import com.google.firebase.auth.FirebaseAuth;

public class MenuFragment extends Fragment {

    GestureDetector gestureDetector;
    private LinearLayout advertise;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.slide_menu, container, false);

        advertise = (LinearLayout) rootView.findViewById(R.id.advertise);

        advertise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PostFragment fragment = new  PostFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.containerm, fragment);
                transaction.commit();
            }
        });


        return rootView;
    }
}
