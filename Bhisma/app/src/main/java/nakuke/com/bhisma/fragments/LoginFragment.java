package nakuke.com.bhisma.fragments;


import android.app.Activity;
import android.os.Bundle;
import android.telecom.Call;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import nakuke.com.bhisma.R;
import nakuke.com.bhisma.activities.LoginActivity;

public class LoginFragment extends BaseFragment implements View.OnClickListener {

    private Button loginButton;
    private CallBacks callBacks;
    /*
    Here we cannot just start with overloading oncreate method, as you notice there is no setContentView method in onCreate.
    *** So there is anoter method android will be invoke to get the view object that will be reference for the Fragment object
     */


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup root, Bundle savedState) {
        /*
        Basically we are seeing the exact opposite of an activity here,
        Once an activity is invoked, it triggers onCreate method and setsContentView inside onCreate itself, while framents are not
        android is going to request that we return an inflated View object (view object can be anything, we can creaete a view without
        a layout at all
        */

        /*
        Here the first param is going to be a layout param describing the view in XML,
        second param is ViewGroup root :  inorder to do some sizing calculations, the layout inflater needs to have a reference to
        the viewgroup that is inavitably going to become the View object. So it needs to konw the dimensions of the Root object. Where
         its going to be placed.
         false : is attached to root - What does  this mean. So after the inflater is done with inflating the xml file, then
         do we want to automtically add this view to the ViewGroup (root) ?
         By why dont we want to do it here ? as here the View returned by the OnCreateView method is automatically added, so if this is true
         the view is going to be attached twice and we get an error
         */

        /*
        INFLATE : it takes the XML object and turns it into an Java Object
        we have been using this inflatore all the time but its always hidden under setContentView method of activity
         */
        View view = inflater.inflate(R.layout.fragment_login, root, false);

        loginButton = (Button) view.findViewById(R.id.fragment_login_loginButton);
        loginButton.setOnClickListener(this);
        return view;

    }

    @Override
    public void onClick(View view) {
        if(view == loginButton) {

            /*
            We need to set somewhere over the chain that the user has logged in after clicking
            the login button, user is logged in. For now when we are not actually authenticating
            we can still click the login button without any creds. And make sure that User Boolean vallue of
            is LoggedIn is set to true. If we dont do this after we click the Login Button -> goes to Main Activity
            -> BaseAuthenticatedActivity and will take you back to Login Activity as user is not set to Loggedin
             */

            application.getAuth().getUser().setLoggedIn(true); // This is still a static set true, means once the app is closed we need to set it again but thats fine for now

            //Notify parent activity
            callBacks.onLoggedIn();
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        callBacks = (CallBacks) activity;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        callBacks = null;
    }


    public interface CallBacks {
        void onLoggedIn();
    }
}
