package com.tests.yatfinalandroid.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.FrameLayout;

import com.tests.yatfinalandroid.R;
import com.tests.yatfinalandroid.fragments.MyBooksFragment;

public class MainActivity extends AppCompatActivity {
    public Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("My books");
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        MyBooksFragment myBooksFragment = new MyBooksFragment();
        ft.replace(R.id.frame_layout, myBooksFragment);
        ft.commit();
    }
}