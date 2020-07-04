package accounts;

/**
 * A credit card account.
 */
public class CreditCardAccount extends DebtAccount {

    /**
     * Constructs a new credit card account.
     */
    public CreditCardAccount() {
        super();
    }

    /**
     * Pay a bill using this account.
     *
     * @param amount The amount of money the user would like to pay (double)
     * @param payee The payee for this bill
     * @return boolean - true if payment successful and false if unsuccessful
     */
    public boolean payBill(double amount, String payee) { return false; }

    /**
     * Transfer a given amount of money from this account to the given account.
     *
     * @param amount The amount of money the user wants to transfer to the other account (double)
     * @param transferTo The account the user wants to transfer the money to (Account)
     * @return boolean - true if transfer was successful and false if unsuccessful
     */
    @Override
    public boolean transfer(double amount, Account transferTo) { return false; }

    /**
     * Returns the type of account this is.
     *
     * @return String - The type of account this is
     */
    public String getType() { return "Credit Card"; }
}
