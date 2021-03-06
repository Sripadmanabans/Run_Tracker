package com.example.sripadmanaban.runtracker;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.util.Log;

/**
 * The receiver that receiver that takes care of the intents that come in.
 * Created by Sripadmanaban on 2/26/2015.
 */
public class LocationReceiver extends BroadcastReceiver {

    private static final String TAG = "LocationReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        // If you got a location extra, use it
        Location loc = intent
                .getParcelableExtra(LocationManager.KEY_LOCATION_CHANGED);
        if(loc != null) {
            onLocationReceived(context, loc);
            return;
        }
        // If you get here, something else has happened
        if(intent.hasExtra(LocationManager.KEY_PROVIDER_ENABLED)) {
            boolean enabled = intent
                    .getBooleanExtra(LocationManager.KEY_PROVIDER_ENABLED, false);
            onProviderEnabledChanged(enabled);
        }
    }

    protected void onLocationReceived(Context context, Location loc) {
        Log.d(TAG, this + ". Got location from " + loc.getProvider() + " : " +
                loc.getLatitude() + ", " + loc.getLongitude());
    }

    protected void onProviderEnabledChanged(boolean enabled) {
        Log.d(TAG, "Provider " + (enabled ? "enabled" : "disabled"));
    }
}