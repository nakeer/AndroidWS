package nakuke.com.bhisma.infrastructure;

/*
This is a DTO (Data trasnfer object) class. Which means this contains info about the data but nothing related to behaviour.
 */
public class User {

    private int id;
    private String userName;
    private String displayName;
    private String avatarUrl;
    private boolean isLoggedIn;
    private boolean hasPassword;
    private String email;

    public int getId() {
        return id;
    }

    public void setInd(int id) {
        this.id  = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        isLoggedIn = loggedIn;
    }

    public boolean isHasPassword() {
        return hasPassword;
    }

    public void setHasPassword(boolean hasPassword) {
        this.hasPassword = hasPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
