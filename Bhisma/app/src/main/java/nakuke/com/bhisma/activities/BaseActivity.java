package nakuke.com.bhisma.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import nakuke.com.bhisma.infrastructure.MyApplication;

/**
This is marked as abstract, as we are never going to instantiate it directly
 Instead we are going to instantiate it with the subclasses  or its going to be subclassed and instantiated
 */

public abstract class BaseActivity extends AppCompatActivity {

    protected MyApplication application;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        application = (MyApplication) getApplication();
    }
}
