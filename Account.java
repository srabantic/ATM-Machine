package accounts;

import java.io.*;
import java.util.Calendar;
import java.util.Date;
import java.util.Stack;

/**
 * A bank account.
 */
public abstract class Account implements Serializable {

    /** The current balance in this account. */
    double balance;

    /** The date this account was created. */
    private final Calendar DATE_OF_CREATION;

    /** A stack of transactions involving this account. */
    private Stack<Transaction> transactions;

    /** The id for this account. */
    private String id;

    /** The limit for the balance. For debt accounts, this would be the maximum amount of debt you can incur, and for
     * asset accounts, this would be the minimum amount the balance can decrease to.
     */
    double limit;

    /**
     * Constructs a new account, sets balance to the default value (0.0), and sets the creation date.
     */
    public Account() {
        this.balance = 0.0;
        this.transactions = new Stack<>();

        Date date = new Date();
        this.DATE_OF_CREATION = Calendar.getInstance();
        DATE_OF_CREATION.setTime(date);
    }

    /**
     * Get the balance limit for this account.
     *
     * @return double - The balance limit
     */
    public double getLimit() {
        return limit;
    }

    /**
     * Get the date of creation for this account.
     *
     * @return Date - The date of creation
     */
    public Calendar getDATE_OF_CREATION() {
        return this.DATE_OF_CREATION;
    }

    /**
     * Get the balance from this account.
     *
     * @return double - The balance for this account
     */
    public double getBalance() {
        return this.balance;
    }

    /**
     * Sets the balance for this account.
     */
    void setBalance(double balance) { this.balance = balance; }

    /**
     * Set the id for this account
     *
     * @param id The id for this account (String)
     */
    public void setAccountID(String id) {
        this.id = id;
    }

    /**
     * Get the id for this account.
     *
     * @return String - The id for this account
     */
    public String getAccountID() {
        return this.id;
    }

    /**
     * Pay a bill using this account.
     *
     * @param amount The amount of money the user would like to pay (double)
     * @param payee The payee for this bill
     * @return boolean - true if payment successful and false if unsuccessful
     */
    public boolean payBill(double amount, String payee) {
        if (this.ableToWithdraw(amount)) {
            this.transferMoneyInOut(-amount);
            // Append to the existing outgoing.txt file
            try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("phase2/outgoing.txt", true)))) {
                out.println("Account: " + this.id + ", paid: " + amount + ", payee: " + payee);
            } catch (IOException e) {
                return false;
            }
            return true;
        }
        return false;
    }

    /**
     * Set the most recent transaction for this account.
     *
     * @param transaction The most recent transaction (Transaction)
     */
    public void setLastTransaction(Transaction transaction) {
        this.transactions.push(transaction);
    }

    /**
     * Get the most recent transaction for this account.
     *
     * @return Transaction - The most recent transaction
     */
    public Transaction getLastTransaction() {
        if (this.transactions.size() > 0) {
            return this.transactions.peek();
        } else {
            return null;
        }
    }

    /**
     * Remove and return the most recent transaction for this account.
     */
    public void removeLastTransaction() {
        if (this.transactions.size() > 0) {
            this.transactions.pop();
        }
    }

    /**
     * Transfer a given amount of money from this account to the given account.
     *
     * @param amount The amount of money the user wants to transfer to the other account (double)
     * @param transferTo The account the user wants to transfer the money to (Account)
     * @return boolean - true if transfer was successful and false if unsuccessful
     */
    public boolean transfer(double amount, Account transferTo) {
        if (this.ableToWithdraw(amount)) {
            Transaction t = new Transaction(amount, this, transferTo);
            this.transferMoneyInOut(-amount);
            transferTo.transferMoneyInOut(amount);
            this.setLastTransaction(t);
            transferTo.setLastTransaction(t);
            return true;
        }
        return false;
    }

    /**
     * A String representation of this object.
     *
     * @return String - A representation of this object
     */
    @Override
    public String toString(){
        return id + " " + balance + " " + DATE_OF_CREATION;
    }

    /**
     * Transfer money in or out of this account. A negative amount would transfer money out, and a positive amount would
     * transfer money in.
     *
     * @param amount The amount the user would like to transfer in or out of this account (double)
     */
    abstract public void transferMoneyInOut(double amount);

    /**
     * Determine whether or not it is possible to withdraw the given amount of money from this account.
     *
     * @param amount The amount the user wants to withdraw from this account (double)
     * @return boolean - true if able to withdraw and false if unable to withdraw
     */
    public abstract boolean ableToWithdraw(double amount);

    /**
     * Returns the type of account this is.
     *
     * @return String - The type of account this is
     */
    public abstract String getType();
}
