package com.example.sripadmanaban.runtracker;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * The fragment that tracks runs
 * Created by Sripadmanaban on 2/26/2015.
 */
public class RunFragment extends Fragment {

    private Button mStartButton, mStopButton;

    private TextView mStartedTextView, mLatitudeTextView,
            mLongitudeTextView, mAltitudeTextView, mDurationTextView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_run, container, false);

        mStartedTextView = (TextView) view.findViewById(R.id.startedTextView);
        mLatitudeTextView = (TextView) view.findViewById(R.id.latitudeTextView);
        mLongitudeTextView = (TextView) view.findViewById(R.id.longitudeTextView);
        mAltitudeTextView = (TextView) view.findViewById(R.id.altitudeTextView);
        mDurationTextView = (TextView) view.findViewById(R.id.durationTextView);

        mStartButton = (Button) view.findViewById(R.id.run_startButton);
        mStopButton = (Button) view.findViewById(R.id.run_stopButton);

        return view;
    }
}