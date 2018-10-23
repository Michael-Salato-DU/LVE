package du.a188project1.bestdamapp;

import android.app.Application;

import io.realm.Realm;

public class BestDamApp extends Application {
    public void onCreate() {
        super.onCreate();

        Realm.init(this);
    }
}
