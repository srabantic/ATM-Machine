package accounts;

/**
 * A "high risk" investment account where on the first of every month, the current balance has a 24% of dropping
 * to $0, and a 1% of a tenfold increase. The user is allowed to transfer money in and out of this account, unless
 * transferring money out would cause the balance to drop below $0.
 */
public class HighRiskInvestmentAccount extends SavingsAccount {

    /**
     * Construct a high risk investment account.
     */
    public HighRiskInvestmentAccount() {
        super();
    }

    /**
     * Increase the balance of this account tenfold, decrease the balance to $0, or do nothing.
     */
    @Override
    public void addMonthlyInterest() {
        double num = Math.random();
        if (num < 0.01) {
            String toTwoDecimals = format.format(this.balance * 10.0);
            double newBalance = Double.parseDouble(toTwoDecimals);
            this.setBalance(newBalance);
        } else if (num < 0.24) {
            super.transferMoneyInOut(-this.getBalance());
        }
    }

    /**
     * Returns the type of account this is.
     *
     * @return String - The type of account this is
     */
    public String getType() { return "High Risk Investment"; }
}
