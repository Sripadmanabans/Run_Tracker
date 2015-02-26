package com.example.sripadmanaban.runtracker;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;

/**
 * Used throughout the app for the tracking
 * Created by Sripadmanaban on 2/26/2015.
 */
public class RunManager {
    private static final String TAG = "RunManager";

    public static final String ACTION_LOCATION =
            "com.sripadmanaban.runtracker.ACTION_LOCATION";

    private static RunManager sRunManager;
    private Context mAppContext;
    private LocationManager mLocationManager;

    // The private constructor forces users to use RunManager.getInstance(context)
    private RunManager(Context appContext) {
        mAppContext = appContext;
        mLocationManager = (LocationManager) mAppContext
                .getSystemService(Context.LOCATION_SERVICE);
    }

    public static RunManager getInstance(Context context) {
        if(sRunManager == null) {
            // Use the application context to avoid leaking activities
            sRunManager = new RunManager(context);
        }
        return sRunManager;
    }

    private PendingIntent getLocationPendingIntent(boolean shouldCreate) {
        Intent broadcast = new Intent(ACTION_LOCATION);
        int flags = shouldCreate ? 0 : PendingIntent.FLAG_NO_CREATE;
        return PendingIntent.getBroadcast(mAppContext, 0, broadcast, flags);
    }

    public void startLocationUpdate() {
        String provider = LocationManager.GPS_PROVIDER;

        // Start updates from the location manager
        PendingIntent pi = getLocationPendingIntent(true);
        mLocationManager.requestLocationUpdates(provider, 0, 0, pi);
    }

    public void StopLocationUpdate() {
        PendingIntent pi = getLocationPendingIntent(false);
        if(pi != null) {
            mLocationManager.removeUpdates(pi);
            pi.cancel();
        }
    }

    public boolean isTrackingRun() {
        return getLocationPendingIntent(false) != null;
    }
}