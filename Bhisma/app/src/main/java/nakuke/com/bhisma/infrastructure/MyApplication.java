package nakuke.com.bhisma.infrastructure;

import android.app.Application;

/*
        Not named as Applicatoin as we extend it from Application class
        Acts as a bag of Singleton classes, Single source of point wehere you can find all the classes which were already created.
        Done for code cleanliness purpose, removing the excessive usage of
        Application is a context
 */

public class MyApplication extends Application{

    private Auth auth;

    /*
    THis is the entrypoint of the application, This is created even before any activity is launched or evern instantialted.

     */

    @Override
    public void onCreate() {
        super.onCreate();
        auth = new Auth(this);
    }

    public Auth getAuth() {
        return auth;
    }

}
