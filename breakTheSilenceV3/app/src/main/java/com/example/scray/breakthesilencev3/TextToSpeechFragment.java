package com.example.scray.breakthesilencev3;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;


public class TextToSpeechFragment extends Fragment implements BottomNavigationView.OnNavigationItemSelectedListener {

    private BottomNavigationView bottomNavigationView;
    private  View textToSpeechView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        textToSpeechView = inflater.inflate(R.layout.fragment_text_to_speech, container, false);
        bottomNavigationView = (BottomNavigationView) textToSpeechView.findViewById(R.id.bottomNavigationItemViewTTS);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        displayScreen(R.id.navigation_communication);
        return textToSpeechView;
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        displayScreen(id);
        return true;
    }
    private void displayScreen(int id)
    {
        Fragment fragment = null;
        switch (id){

            case R.id.navigation_communication:
                fragment = new CommnicationFragment();
                break;
            case R.id.navigation_hospital:
                fragment = new HospitalFragment();
                break;
            case R.id.navigation_shopping:
                fragment = new ShoppingFragment();
                break;
            case R.id.navigation_restaurent:
                fragment = new RestaurantFragment();
                break;
            case R.id.navigation_transport:
                fragment = new TransportFragment();
                break;
        }
        if(fragment != null)
        {
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.text_to_speech_fragment,fragment);
            fragmentTransaction.commit();
        }

    }
}
