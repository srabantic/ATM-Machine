package bankSystem;

import users.*;
public class UserFactory {

    /**
     * Create and return a user based on the given type.
     * @return the new User.
     */
    public User createNewUser(String userID, String type) {
        switch(type.toLowerCase()) {
            case "customer" : return new Customer(userID);
            case "billrestocker" :
                userID = "e" + userID;
                return new BillRestocker(userID);
            default: return null;
        }
    }

}