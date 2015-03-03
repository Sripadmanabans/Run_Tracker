package com.example.sripadmanaban.runtracker;

import android.content.Context;
import android.location.Location;
import android.util.Log;

/**
 * Used to track location and also insert location in db
 * Created by Sripadmanaban on 3/3/2015.
 */
public class TrackingLocationReceiver extends LocationReceiver {

    private static final String TAG = "TrackingLocation";

    @Override
    protected void onLocationReceived(Context context, Location loc) {
        Log.d(TAG, this + ". Got location from " + loc.getProvider() + " : " +
                loc.getLatitude() + ", " + loc.getLongitude());
        RunManager.getInstance(context).insertLocation(loc);
    }
}
