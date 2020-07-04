package bankSystem;

import accounts.*;
import users.*;

/**
 * A class for creating chequing, savings, credit card, and line of credit accounts.
 */
@SuppressWarnings("SpellCheckingInspection")
public class AccountFactory {

    /**
     * Create and return an account based on the given type.
     *
     * @param type The type of account that must be created (String)
     * @return Account - The account that was created
     */
    public Account createAccount(Customer c, String type) {
        String accountID = c.getUserID() + "-" + c.getNumOfAccount() + "-";
        Account a;
        switch (type.toLowerCase()) {
            case "chequing" :
                accountID += "Chequing";
                a = new ChequingAccount();
                break;
            case "savings" :
                accountID += "Savings";
                a = new SavingsAccount();
                break;
            case "creditcard" :
                accountID += "CreditCard";
                a = new CreditCardAccount();
                break;
            case "lineofcredit" :
                accountID += "LineOfCredit";
                a = new LineOfCreditAccount();
                break;
            case "certificateofdeposit" :
                accountID += "CertificateOfDeposit";
                a = new CertificateOfDepositAccount();
                break;
            case "highriskinvestment" :
                accountID += "HighRiskInvestment";
                a = new HighRiskInvestmentAccount();
                break;
            default : return null;
        }

        a.setAccountID(accountID);
        return a;
    }
}