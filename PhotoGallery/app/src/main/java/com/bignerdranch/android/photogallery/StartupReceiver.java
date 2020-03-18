package com.bignerdranch.android.photogallery;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class StartupReceiver extends BroadcastReceiver {
    private static final String TAG_LOG = "StartupReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i(TAG_LOG, "Receive broadcast intent: " + intent.getAction());

        boolean isOn = QueryPreference.isAlarmOn(context);
        PollService.setServiceAlarm(context, isOn);

        Log.d(TAG_LOG, "Preference isAlarmOn value: " + isOn);
    }
}
