package com.software.olehsalamakha.mqttclient;

import android.content.Context;
import android.util.Log;

import com.software.olehsalamakha.mqttclient.model.Connection;
import com.squareup.otto.Bus;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttException;


/**
 * Created by olehsalamakha on 11/2/16.
 */

public class ConnectionManager {
    private static final String TAG = "ConnectionManager";
    private static  ConnectionManager sConnectionManager;
    private MqttAndroidClient mAndroidClient;
    private  boolean mIsConnected;

    public static ConnectionManager getInstance() {
        synchronized (ConnectionManager.class) {
            if (sConnectionManager == null) {
                sConnectionManager = new ConnectionManager();
            }
        }
        return sConnectionManager;
    }

    public void connect(Connection connection, Context context) {
        final Bus bus = ((MQTTApp)context.getApplicationContext()).provideBus();
        bus.register(this);
        String uri = "tcp://" + connection.getAddress()+":"+String.valueOf(connection.getPort());
        mAndroidClient = new MqttAndroidClient(context, uri, connection.getName());

        try {
            IMqttToken token = mAndroidClient.connect();
            token.setActionCallback(new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    Log.d(TAG, "OnSuccess");
                    mIsConnected = true;
                    bus.post(new ConnectedEvent());
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    mIsConnected = false;
                    bus.post(new NotConnectedEvent());
                    Log.d(TAG, "OnFailure");
                }
            });
        } catch (MqttException e) {
            mIsConnected = false;
            Log.e(TAG, e.getMessage().toString());
            e.printStackTrace();
            bus.post(new NotConnectedEvent());
        }
    }


    public class ConnectedEvent {

    }

    public class NotConnectedEvent {

    }

    private  boolean isConnected() {
        return mIsConnected;
    }

}
