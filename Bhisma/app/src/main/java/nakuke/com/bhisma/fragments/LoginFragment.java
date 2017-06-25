package nakuke.com.bhisma.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import nakuke.com.bhisma.R;

public class LoginFragment extends BaseFragment {
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
        return view;

    }

}
