package accounts;

import java.util.Calendar;

/**
 * A certificate of deposit account. With this account, users can specify how many months they would like to leave the
 * money deposited for, and if they want to withdraw money from this account before the specified number of months, a
 * penalty will be applied and their balance will be reduced. The interest rate for this account is 1% and the balance
 * for this account will always be $0 or above. If a withdrawal is made before the term ends, the penalty will be
 * calculated after the withdrawal is made. On the first of every month, the balance increases by 1%.
 */
public class CertificateOfDepositAccount extends SavingsAccount {

    /**
     * The term length for this account, in months
     * (i.e. how long the money must be left in this account before a withdrawal can be made without penalty)
     */
    private int term;

    /**
     * Constructs a certificate of deposit account.
     */
    public CertificateOfDepositAccount() {
        super();
    }

    /**
     * Increase the balance of this account by %0.1.
     */
    @Override
    public void addMonthlyInterest() {
        String toTwoDecimals = format.format(this.balance * 1.01);
        double newBalance = Double.parseDouble(toTwoDecimals);
        this.setBalance(newBalance);
    }

    /**
     * Sets the term length for this account.
     *
     * @param term The term length for this account, in months (int)
     */
    public void setTerm(int term) {
        this.term = term;
    }

    /**
     * Calculate and return the penalty for the withdrawal being made. The minimum penalty is $25, and the penalty is
     * calculated using the amount being withdrawn, the interest rate, and the term.
     *
     * @param amount The amount the user would like to withdraw (double)
     * @return double - The penalty for the withdrawal
     */
    private double calculateTotalPenalty(double amount) {
        int currMonth = Calendar.getInstance().get(Calendar.MONTH);
        int currDay = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        int startMonth = super.getDATE_OF_CREATION().get(Calendar.MONTH);
        int startDay = super.getDATE_OF_CREATION().get(Calendar.DAY_OF_MONTH);
        if (currMonth - startMonth >= term && currDay >= startDay) {
            return 0;
        }
        // return the maximum of the penalty calculated and 25, which is the minimum penalty
        return Math.max(amount * (0.01 / 12) * (term * 3), 25.0);
    }

    /**
     * Transfer money in or out of this account. A negative amount would transfer money out, and a positive amount would
     * transfer money in.
     *
     * @param amount The amount the user would like to transfer in or out of this account (double)
     */
    @Override
    public void transferMoneyInOut(double amount) {
        super.transferMoneyInOut(amount);
        if (amount < 0) {
            // Round penalty to two decimal places
            double penalty = Math.round(this.calculateTotalPenalty(-amount) * 100.0) / 100.0;
            // If the penalty is greater than the current balance, set the balance to $0
            if (penalty > super.getBalance()) {
                super.transferMoneyInOut(-super.getBalance());
            } else {
                super.transferMoneyInOut(-penalty);
            }
        }

    }

    /**
     * Returns the type of account this is.
     *
     * @return String - The type of account this is
     */
    public String getType() { return "Certificate of Deposit"; }
}
