package com.example.sripadmanaban.runtracker;

import android.app.Fragment;

public class RunActivity extends SingleFragmentActivity {
    @Override
    public Fragment createFragment() {
        return new RunFragment();
    }
}
