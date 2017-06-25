package nakuke.com.bhisma.fragments;


import android.app.Fragment;
import android.os.Bundle;

import nakuke.com.bhisma.infrastructure.MyApplication;

public abstract class BaseFragment extends Fragment {
    protected MyApplication application;

    //we wont be able to use protected here in fragments as the onCreate in Fragment class is public already
    // and we cannot downgrade the modifier in the extended classes.
    @Override
    public void onCreate(Bundle savedState) {
        super.onCreate(savedState);

        //Here if we notice we are not getting the getApplicaton directly, indstead we are getting it by calling the parent activity
        //of the Fragment. So this explains the concept of fragments getting attached to activity.
        application = (MyApplication) getActivity().getApplication();
    }
}
