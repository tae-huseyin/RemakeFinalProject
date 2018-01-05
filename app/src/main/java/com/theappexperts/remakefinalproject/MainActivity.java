package com.theappexperts.remakefinalproject;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.theappexperts.remakefinalproject.views.recipelist.ListFragment;

public class MainActivity extends AppCompatActivity {

    //Crashlytics.getInstance().crash(); // Force a crashj

    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager = getSupportFragmentManager();

        fragmentManager.beginTransaction()
                    .add(R.id.frag_container, new ListFragment())
                    .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                    .commit();

    }
}
