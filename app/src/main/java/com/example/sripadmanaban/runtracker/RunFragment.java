package com.example.sripadmanaban.runtracker;

import android.app.Fragment;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.location.Location;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * The fragment that tracks runs
 * Created by Sripadmanaban on 2/26/2015.
 */
public class RunFragment extends Fragment {

    private RunManager mRunManager;

    private Button mStartButton, mStopButton;

    private TextView mStartedTextView, mLatitudeTextView,
            mLongitudeTextView, mAltitudeTextView, mDurationTextView;

    private Run mRun;

    private Location mLastLocation;
    
    private BroadcastReceiver mLocationReceiver = new LocationReceiver() {
        @Override
        protected void onLocationReceived(Context context, Location loc) {
            mLastLocation = loc;
            if(isVisible()) {
                updateUI();
            }
        }

        @Override
        protected void onProviderEnabledChanged(boolean enabled) {
            int toastText = enabled ? R.string.gps_enabled : R.string.gps_disabled;
            Toast.makeText(getActivity(), toastText, Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        mRunManager = RunManager.getInstance(getActivity());
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
        mStartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRun = mRunManager.startNewRun();
                updateUI();
            }
        });

        mStopButton = (Button) view.findViewById(R.id.run_stopButton);
        mStopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRunManager.stopRun();
                updateUI();
            }
        });

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        getActivity().registerReceiver(mLocationReceiver,
                new IntentFilter(RunManager.ACTION_LOCATION));
    }

    @Override
    public void onStop() {
        super.onStop();
        getActivity().unregisterReceiver(mLocationReceiver);
    }

    private void updateUI() {
        boolean started = mRunManager.isTrackingRun();

        if(mRun != null) {
            mStartedTextView.setText(mRun.getStartDate().toString());
        }

        int durationSeconds = 0;
        if(mRun != null && mLastLocation != null) {
            durationSeconds = mRun.getDurationSeconds(mLastLocation.getTime());
            mLatitudeTextView.setText(Double.toString(mLastLocation.getLatitude()));
            mLongitudeTextView.setText(Double.toString(mLastLocation.getLongitude()));
            mAltitudeTextView.setText(Double.toString(mLastLocation.getAltitude()));
        }
        mDurationTextView.setText(Run.formatDuration(durationSeconds));

        mStartButton.setEnabled(!started);
        mStopButton.setEnabled(started);
    }
}
