package com.software.olehsalamakha.mqttclient;

import android.app.Application;

import com.squareup.otto.Bus;

/**
 * Created by olehsalamakha on 11/2/16.
 */

public class MQTTApp extends Application {

    Bus bus;

    @Override
    public void onCreate() {
        super.onCreate();
        bus = new Bus();
    }

    public Bus provideBus() {
        return bus;
    }


}
