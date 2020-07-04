package accounts;

/**
 * An asset account
 */
abstract public class AssetAccount extends Account {

    /**
     * Constructs a new asset account.
     */
    public AssetAccount() {
        super();
    }

    /**
     * Transfer money in or out of this account. A negative amount would transfer money out, and a positive amount would
     * transfer money in.
     *
     * @param amount The amount the user would like to transfer in or out of this account (double)
     */
    @Override
    public void transferMoneyInOut(double amount) {
        this.balance += amount;
    }
}