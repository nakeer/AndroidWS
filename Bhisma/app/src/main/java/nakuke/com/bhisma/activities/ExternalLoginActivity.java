package nakuke.com.bhisma.activities;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;

import nakuke.com.bhisma.R;

/**
 * Created by naveen.keerthy on 7/13/17.
 */

public class ExternalLoginActivity extends BaseActivity implements View.OnClickListener {

    //this will the param for the intent that starts the extra activity
    public static final String EXTRA_EXTERNAL_SERVICE = "EXTRA_EXTERNAL_SERVICE";

    private Button testButton;
    private WebView webView;
    @Override
    protected void onCreate(Bundle savedState) {
        super.onCreate(savedState);
        setContentView(R.layout.activity_external_login);

        testButton = (Button) findViewById(R.id.activity_external_login_testButton);
        webView = (WebView) findViewById(R.id.activity_external_login_webView);

        testButton.setOnClickListener(this);
        testButton.setText("Login with: " + getIntent().getStringExtra(EXTRA_EXTERNAL_SERVICE));
    }

    @Override
    public void onClick(View view) {
        if(view == testButton) {
            application.getAuth().getUser().setLoggedIn(true);
            setResult(RESULT_OK);
            finish();
        }
    }
}