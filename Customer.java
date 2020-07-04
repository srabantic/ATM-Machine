package users;

import accounts.*;

import java.util.HashMap;
import java.util.List;

public class Customer extends User {

    private Account primaryAccount;
    private int numOfCheckingAccount;
    private int numOfAccount = 0;
    private HashMap<String, Account> userAccounts;


    /**
     * Constructs a Customer
     *
     * @param customerID that is assigned by the BankManager
     */

    public Customer(String customerID) {
        super(customerID);
        this.userAccounts = new HashMap<>();
        this.numOfCheckingAccount = 0;

    }

    public void setPrimaryAccount(Account account){
        this.primaryAccount = account;
    }


    /**
     * @return returns the primary account
     */

    public Account getPrimaryAccount() {
        return primaryAccount;
    }

    /**
     * @return returns the total number of accounts an user own
     */
    public int getNumOfAccount() {
        return numOfAccount;
    }

    /**
     * Stores all the  accounts an user own and sets the account ID
     * @param accountID the account ID of the account that is being stored
     * @param account that is being stored
     */

    public void storeUserAccounts(String accountID, Account account) {
        // increment the number of accounts
        this.numOfAccount += 1;
        // stores the account
        userAccounts.put(accountID, account);

        //sets the account ID
        // all account IDs are unique
        account.setAccountID(accountID);
        // checks whether it is the first chequing account created
        if (account.getType() == "chequing"){
            this.numOfCheckingAccount +=1;
            if (numOfCheckingAccount == 1);{ setPrimaryAccount(account);}
        }

    }


    /**
     * Returns the account for a specific account ID
     *
     * @return the account for a specific account ID
     */

    public Account getSpecificAccount(String key) {
        if (userAccounts.containsKey(key)) {
            return userAccounts.get(key);
        }
        else {
            return null;
        }
    }

    /**
     * Returns all the accounts for a user
     *
     * @return HashMap - All the accounts for the user.
     */

    public HashMap getAllUserAccounts() {
        return userAccounts;
    }

    /**
     * Sets the monthly interest for the user's savings accounts
     *
     * @param accounts A list of the user's savings account
     */

    public void updateInterest(List<SavingsAccount> accounts) {
        for (SavingsAccount account : accounts) {
            account.addMonthlyInterest();
        }
    }

    /**
     * Adds the joint account to the list of this user's accounts.
     *
     * @param account The joint account being added
     */

    public void addJointAccount( Account account ) {
        this.numOfAccount++;
        userAccounts.put(account.getAccountID(), account);
    }
}
