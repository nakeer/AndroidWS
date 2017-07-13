package nakuke.com.bhisma.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import nakuke.com.bhisma.R;
import nakuke.com.bhisma.fragments.LoginFragment;

/**

 */

public class LoginActivity extends BaseActivity implements View.OnClickListener, LoginFragment.CallBacks {

    private static final int REQUEST_NARROW_LOGIN = 1;
    private static final int REQUEST_REGISTER = 2;
    private static final int REQUEST_EXTERNAL_LOGIN = 3;

    private View loginButton;
    private View registerButton;
    private View facebookLoginButton;
    private View googleLoginButton;

    @Override
    protected void onCreate(Bundle savedState) {
        super.onCreate(savedState);

        setContentView(R.layout.activity_login);

        loginButton = findViewById(R.id.activity_login_login);
        registerButton = findViewById(R.id.activity_login_register);
        facebookLoginButton = findViewById(R.id.activity_login_facebook);
        googleLoginButton = findViewById(R.id.activity_login_google);

        if(loginButton != null) {
            loginButton.setOnClickListener(this);
        }

        registerButton.setOnClickListener(this);
        facebookLoginButton.setOnClickListener(this);
        googleLoginButton.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        if(view == loginButton)
           // startActivity(new Intent(this, LoginNarrowActivity.class));
            // We use a different type of startActivity which gives the option of checking the result back
           startActivityForResult(new Intent(this, LoginNarrowActivity.class), REQUEST_NARROW_LOGIN );

        else if(view == registerButton) {
            startActivityForResult(new Intent(this, RegisterActivity.class), REQUEST_REGISTER);
        }

        else if(view == googleLoginButton) {
            doExternalLogin("Google");
        }

        else if(view == facebookLoginButton) {
            doExternalLogin("Facebook");
        }
    }

    private void doExternalLogin(String externalService) {
        Intent intent = new Intent(this, ExternalLoginActivity.class);
        intent.putExtra(ExternalLoginActivity.EXTRA_EXTERNAL_SERVICE, externalService);
        startActivityForResult(intent, REQUEST_EXTERNAL_LOGIN);
    }

    /*
    This is the method android will invoke when we kick the start activity for result method.
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode != RESULT_OK) {
            return;
        }
        if (requestCode == REQUEST_NARROW_LOGIN ||
                requestCode == REQUEST_REGISTER ||
                requestCode == REQUEST_EXTERNAL_LOGIN) {
            finishLogin();
        }
    }

    private void finishLogin() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    @Override
    public void onLoggedIn() {
        finishLogin();
    }
}
