package users;

import accounts.*;
import bankSystem.*;

public class BankManager extends BillRestocker {

    /**
     * Stores the total number of users.
     */
    private int totalNumUsers;

    /**
     * Construct a new BankManager.
     */
    public BankManager(String userID) {
        super(userID);
        this.totalNumUsers = 0;
    }

    /**
     * Create and return a new User.
     * @param password the new User's password.
     * @return the new user.
     */
    public User createNewUser(String password, String type) {
        UserFactory uf = new UserFactory();
        User u = uf.createNewUser(Integer.toString(totalNumUsers), type);
        u.changePassWord(password);
        MainBankSystem.getBIS().addUser(u);
        totalNumUsers++;
        return u;
    }

    /**
     * Crate a new Account for a Customer, as long as the Customer has less than 10 existing Accounts.
     * Return the account created. If no account was created, return null.
     * @param customer the Customer the Account is being created for.
     * @param type the type of Account being created.
     * @return the account that was created.
     */
    public Account createNewAccount(Customer customer, String type) {
        if (customer.getNumOfAccount() < 10) {
            AccountFactory af = new AccountFactory();
            Account a = af.createAccount(customer, type);
            customer.storeUserAccounts(a.getAccountID(), a);
            return a;
        }
        return null;
    }

    /**
     * Reverse a transaction, if possible. Return null otherwise.
     * Returns the transaction if it is reversible. Return null otherwise.
     * @param toReverse the account whose last transaction is to be reversed.
     */
    public Transaction reverseTransaction(Account toReverse) {
        Transaction t = toReverse.getLastTransaction();
        if (t != null && t.getReversible()) {
            reverseTransactionHelper(t, t.getPayee(), -t.getAmount());
            reverseTransactionHelper(t, t.getPayer(), t.getAmount());
            return t;
        }
        return null;
    }

    /**
     * Helper method for reverseTransaction(). Transfers money and removes most recent transaction, if the most recent
     * transaction is the one being reversed, for an account.
     * @param trans the transaction being reversed.
     * @param acc the account being operated on.
     * @param amount the amount of money being transferred (negative to transfer out, positive to transfer in).
     */
    private void reverseTransactionHelper(Transaction trans, Account acc, double amount) {
        acc.transferMoneyInOut(amount);
        if (acc.getLastTransaction() != null && acc.getLastTransaction() == trans) {
            acc.removeLastTransaction();
        }
    }
}