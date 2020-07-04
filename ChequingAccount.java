package accounts;

/**
 * A chequing account.
 */
@SuppressWarnings("SpellCheckingInspection")
public class ChequingAccount extends AssetAccount {

    /**
     * Constructs a new chequing account.
     */
    public ChequingAccount() {
        super();
        this.limit = -100.0;
    }

    /**
     * Determine whether or not it is possible to withdraw the given amount of money from this account.
     *
     * @param amount The amount the user wants to withdraw from this account (double)
     * @return boolean - true if able to withdraw and false if unable to withdraw
     */
    @Override
    public boolean ableToWithdraw(double amount) {
        if (this.getBalance() < 0) {
            return false;
        }
        return (this.getBalance() - amount >= this.getLimit());
    }

    /**
     * Returns the type of account this is.
     *
     * @return String - The type of account this is
     */
    public String getType() { return "Chequing"; }
}
