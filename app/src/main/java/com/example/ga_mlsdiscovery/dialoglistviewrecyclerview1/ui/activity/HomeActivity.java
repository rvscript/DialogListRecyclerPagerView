package com.example.ga_mlsdiscovery.dialoglistviewrecyclerview1.ui.activity;

import android.app.FragmentTransaction;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;

import com.example.ga_mlsdiscovery.dialoglistviewrecyclerview1.R;
import com.example.ga_mlsdiscovery.dialoglistviewrecyclerview1.ui.menu_list_view.Fragment1;

public class HomeActivity extends AppCompatActivity {
    private FrameLayout mFragmentContainer;
    private FragmentManager fm;
    private FragmentTransaction ft;
    Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        fm = getSupportFragmentManager();

        mFragmentContainer = findViewById(R.id.fragment_container);
        fragment = fm.findFragmentById(R.id.fragment_container);

        startFragment(fragment, savedInstanceState);
    }

    private void startFragment(Fragment fragment, Bundle sis){
        if(sis != null){
            return;
        }

        if(fragment == null){
            fragment = new Fragment1();
            fm.beginTransaction()
                    .add(R.id.fragment_container, fragment)
                    .commit();
        }
    }
}
