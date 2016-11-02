package com.software.olehsalamakha.mqttclient.connections;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.software.olehsalamakha.mqttclient.ConnectionManager;
import com.software.olehsalamakha.mqttclient.MQTTApp;
import com.software.olehsalamakha.mqttclient.R;
import com.software.olehsalamakha.mqttclient.model.Connection;
import com.squareup.otto.Subscribe;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((MQTTApp)getApplication()).provideBus().register(this);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        Connection connection = new Connection("192.168.1.2", 1883, "hello");
        ConnectionManager manager = ConnectionManager.getInstance();
        manager.connect(connection, this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Subscribe
    void onConnected(ConnectionManager.ConnectedEvent event) {
        Log.d(TAG, "on connected");
    }

    @Subscribe
    void onConnectionFailure(ConnectionManager.NotConnectedEvent event) {
        Log.d(TAG, "not connected event");
    }
}
