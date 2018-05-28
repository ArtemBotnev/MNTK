package ru.artembotnev.mntk;

import android.app.Application;

import io.realm.Realm;

public class MntkApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
    }
}
