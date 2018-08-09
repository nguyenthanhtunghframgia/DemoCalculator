package com.example.nguyenthanhtungh.myapplication.ui.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.nguyenthanhtungh.myapplication.R;
import com.example.nguyenthanhtungh.myapplication.ui.ui.fragment.MainFragment;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getName();
    private static final String TAG_MAIN_FRAGMENT = MainFragment.class.getName();

    private MainFragment mainFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG,"onCreate");
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            addMainFragment();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG,"onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG,"onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG,"onStop");
    }

    private void addMainFragment() {
        if (mainFragment == null) {
            mainFragment = new MainFragment();
        }
        getSupportFragmentManager().beginTransaction()
                .add(mainFragment,TAG_MAIN_FRAGMENT)
                .replace(R.id.activity_main,mainFragment)
                .commit();
    }

}
