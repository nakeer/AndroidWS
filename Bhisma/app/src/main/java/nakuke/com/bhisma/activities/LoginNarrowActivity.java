package nakuke.com.bhisma.activities;

import android.os.Bundle;

import nakuke.com.bhisma.R;
import nakuke.com.bhisma.fragments.LoginFragment;

/**
 *
 */

public class LoginNarrowActivity extends BaseActivity implements LoginFragment.CallBacks {

    @Override
    protected void onCreate(Bundle savedState) {
        super.onCreate(savedState);
        setContentView(R.layout.activity_login_narrow);
    }

    @Override
    public void onLoggedIn() {
        setResult(RESULT_OK);
        finish();
    }
}
