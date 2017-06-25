package nakuke.com.bhisma.activities;

import android.content.Intent;
import android.os.Bundle;

/**
 * Created by naveen.keerthy on 6/24/17.
 */

public abstract class BaseAuthenticatedActivity extends BaseActivity {


    /*
    We no longer want to extend the extend the activities that are extended from BaseActivity. Hence we mark them as final
    Why ? : when we are not logged in we would like to replace the current activity with the Login Activity. How ever if we did this onCreate without final all the activities or any
            subclasses which have onCreate method still will be invoked and goes through the entire life cycle making use of resource
            So this method will only get invoked only when we are logged in.
            As we have made onBhismaCreate method abstract = all the sub class will have its own implementation of the method and will only work when the onCreate method works
     */
    @Override
    protected final void  onCreate(Bundle savedState) {
        super.onCreate(savedState);

        if(!application.getAuth().getUser().isLoggedIn()) {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
            return;
        }

        onBhismaCreate(savedState);
    }

    protected abstract void onBhismaCreate(Bundle savedState);
}
