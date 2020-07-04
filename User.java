package  users;

import java.io.Serializable;

public abstract class User implements Serializable {

    /**
     * Stores the userID and the password for the User who can either be a customer or an employee
     */

    private String userID;
    private String userPassword;


    /**
     * Constructs a new User
     */

    public User(String userID) {
        this.userID = userID;
        this.userPassword = getUserPassword();


    }

    /**
     * @return returns the userID
     */
    public String getUserID() {
        return this.userID;
    }

    /**
     * Changes the password for the User
     *
     * @param newPassword that is set to be the new password
     */

    public void changePassWord(String newPassword) {
        this.userPassword = newPassword;
    }

    /**
     * @return returns the password for the User
     */
    public String getUserPassword() {
        return userPassword;
    }



}

