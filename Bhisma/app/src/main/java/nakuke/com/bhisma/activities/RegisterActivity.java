package nakuke.com.bhisma.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import nakuke.com.bhisma.R;

/**
 * Created by naveen.keerthy on 7/13/17.
 */

public class RegisterActivity extends BaseActivity implements View.OnClickListener {

    private EditText userNameText;
    private EditText emailText;
    private EditText passwordText;
    //Reason for taking it as button is, we want to hide the text on the button and show progressbar. Easier with Button
    private Button registerButton;
    //Similarly its easy with View to hide and show, so we consider it with generalType as View
    private View progressBar;


    @Override
    protected void onCreate(Bundle savedState) {
        super.onCreate(savedState);
        setContentView(R.layout.activity_register);

        userNameText = (EditText) findViewById(R.id.activity_register_userName);
        emailText = (EditText) findViewById(R.id.activity_register_email);
        passwordText = (EditText) findViewById(R.id.activity_register_password);
        registerButton = (Button) findViewById(R.id.activity_register_registerButton);
        progressBar = findViewById(R.id.activity_register_progressBar);

        registerButton.setOnClickListener(this);

        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View view) {
        if(view == registerButton) {

            application.getAuth().getUser().setLoggedIn(true);
            setResult(RESULT_OK);
            finish();
        }
    }
}
