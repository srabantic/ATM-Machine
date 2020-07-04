package accounts;

/**
 * A line of credit account
 */
public class LineOfCreditAccount extends DebtAccount {

    /**
     * Constructs a new line of credit account.
     */
    public LineOfCreditAccount() {
        super();
    }

    /**
     * Returns the type of account this is.
     *
     * @return String - The type of account this is
     */
    public String getType() { return "Line of Credit"; }
}
