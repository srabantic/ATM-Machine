package accounts;

import java.text.DecimalFormat;


/**
 * A savings account.
 */
public class SavingsAccount extends AssetAccount {

    /** To set the balance to two decimal places. */
    DecimalFormat format;

    /**
     * Constructs a new savings account
     */
    public SavingsAccount() {
        super();
        this.limit = 0.0;
        format = new DecimalFormat("##.00");
    }

    /**
     * Increase the balance of this account by %0.1.
     */
    public void addMonthlyInterest() {
        String toTwoDecimals = format.format(this.balance * 1.001);
        double newBalance = Double.parseDouble(toTwoDecimals);
        this.setBalance(newBalance);
    }

    /**
     * Determine whether or not it is possible to withdraw the given amount of money from this account.
     *
     * @param amount The amount the user wants to withdraw from this account (double)
     * @return boolean - true if able to withdraw and false if unable to withdraw
     */

    @Override
    public boolean ableToWithdraw(double amount) {
        return (this.getBalance() - amount >= this.getLimit());
    }

    /**
     * Returns the type of account this is.
     *
     * @return String - The type of account this is
     */
    public String getType() { return "Savings"; }
}
