package com.junkuvo.singlechat;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class App extends Application {

    private Realm realm;
    private static App app;

    @Override
    public void onCreate() {
        super.onCreate();

        // realmの初期化
        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .schemaVersion(1).build();
        Realm.setDefaultConfiguration(config);
        realm = Realm.getDefaultInstance();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        realm.close();
    }

    public Realm getRealm() {
        return realm;
    }

    public static App getAppInstance() {
        return app;
    }
}
