package accounts;

/**
 * A debt account
 */
abstract class DebtAccount extends Account {

    /**
     * Constructs a new debt account.
     */
    public DebtAccount() {
        super();
        this.limit = 5000.0;
    }

    /**
     * Transfer money into this account. A negative amount would transfer money out, and a positive amount would
     * transfer money in.
     *
     * @param amount The amount the user would like to transfer in or out of this account (double)
     */
    @Override
    public void transferMoneyInOut(double amount) {
        this.balance -= amount;
    }

    /**
     * Determine whether or not it is possible to withdraw the given amount of money from this account.
     *
     * @param amount The amount the user wants to withdraw from this account (double)
     * @return boolean - true if able to withdraw and false if unable to withdraw
     */
    @Override
    public boolean ableToWithdraw(double amount) { return (this.getBalance() + amount <= this.getLimit()); }
}
