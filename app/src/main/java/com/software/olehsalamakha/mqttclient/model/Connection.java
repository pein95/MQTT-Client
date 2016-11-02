package com.software.olehsalamakha.mqttclient.model;

public class Connection {
    private String  mName;
    private String  mAddress;
    private String  mUserName;
    private String  mPassword;
    private int     mPort;
    private boolean mIsTLSEnabled;


    public Connection(String address, int port, String name) {
        mName = name;
        mAddress = address;
        mPort = port;
    }

    public Connection(String address, int port, String name,
                      String userName, String password) {
        this(address, port, name);
        mUserName = userName;
        mPassword = password;
    }

    public Connection(String address, int port, String name,
                      String userName, String password,
                      boolean isTLSEnabled) {
        this(address, port, name, userName, password);
        mIsTLSEnabled = isTLSEnabled;
    }

    public String getAddress() {
        return mAddress;
    }

    public int getPort() {
        return mPort;
    }

    public String getUserName() {
        return mUserName;
    }

    public String getPassword() {
        return mPassword;
    }

    public boolean isTLSEnabled() {
        return mIsTLSEnabled;
    }

    public String getName() {
        return mName;
    }

}
