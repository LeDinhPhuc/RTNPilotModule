package com.x.multicdn;

import android.util.Log;

import androidx.annotation.Nullable;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import javax.annotation.Nonnull;

import com.xproject.pilot.PilotClient;

public class MultiCdnModule extends ReactContextBaseJavaModule implements DownloadTracker.TrackerListener {
    private static ReactApplicationContext _reactContext;
    private DownloadTracker tracker;
    MultiCdnModule(ReactApplicationContext context) {
        super(context);
        _reactContext = context;
    }

    @Nonnull
    @Override
    public String getName() {
        return "MultiCDN";
    }

    @ReactMethod
    public void initPilot(String token) {
        PilotClient.initialize(_reactContext, token);
    }

    @ReactMethod(isBlockingSynchronousMethod = true)
    public String generateUrl(String originUrl, String propertyId) {
        String pilotUrl = PilotClient.generateUrl(originUrl, propertyId);
        Log.e("Sigma", "New URL: " + pilotUrl);
        return pilotUrl;
    }

    @ReactMethod
    public void getLog(Promise promise) {
        Log.e("Sigma Player", "OnTracker: Call from thread");
        WritableMap params = Arguments.createMap();
        // String log = MultiCDN.getTracker();
        // params.putString("log", log);
        // Log.e("Sigma Player", "OnTracker: " + log);
        promise.resolve(params);
    }

    public void onTracker(String log) {
        Log.e("Sigma Player", "OnTracker: Call from thread");
        _reactContext.runOnJSQueueThread(new Runnable() {
            @Override
            public void run() {
                WritableMap params = Arguments.createMap();
                params.putString("log", log);
                Log.e("Sigma Player", "OnTracker: " + log);
                sendEvent(_reactContext, "downloadTracker", params);
            }
        });
    }

    private void sendEvent(ReactContext context,
            String eventName,
            @Nullable WritableMap params) {
        context
                .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
                .emit(eventName, params);
    }
}
