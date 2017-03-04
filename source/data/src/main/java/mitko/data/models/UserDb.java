package mitko.data.models;

/**
 * Created by dimki on 03.03.2017 г..
 */

public class UserDb {
    public final int MIN_USERNAME_LENGTH = 4;
    public final String USERNAME_LENGTH_MESSAGE = String.format("Usename must be at least %s symbols long", MIN_USERNAME_LENGTH);
    private String username;
    private String password;
    private String phoneNumber;

    public UserDb(String username, String password, String phoneNumber) {
        this.username = username;
        this.password = password;
        this.phoneNumber = phoneNumber;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        if (username.length() < MIN_USERNAME_LENGTH) {
            throw new IllegalArgumentException(USERNAME_LENGTH_MESSAGE);
        }
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
