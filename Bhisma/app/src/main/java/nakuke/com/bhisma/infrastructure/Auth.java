package nakuke.com.bhisma.infrastructure;

import android.content.Context;

public class Auth {
    //Contxt is like a God object, its like a way to access android API. All activities has a context so they are able to access it
    // Similarly Application class can also have a Context but has less priviliges than that of Context in activity
    // we use it for load resources, inflating etc
    private final Context context;
    private User user;

    public Auth(Context context) {
        this.context = context;
        user = new User();
    }

// Dont want the setter for user.
    public User getUser() {
        return user;
    }
}
