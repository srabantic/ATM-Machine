package accounts;

import java.io.Serializable;
import java.util.Date;

public class Transaction implements Serializable {

    private Date date;
    private double amount;
    private Account payer;

    // payee == null if and only if transaction is a bill payment
    private Account payee;


    public Transaction(double amount, Account payer, Account payee) {
        this.amount = amount;
        this.payer = payer;
        this.payee = payee;
        this.date = new Date();
    }

    /**
     * returns payee's account
     * @return returns payee's account
     */
    public Account getPayee(){
        return payee;
    }

    /**
     * returns payer's account
     * @return returns payer's account
     */
    public Account getPayer() {
        return payer;
    }

    /**
     * returns amount of the transaction
     * @return returns amount of the transaction
     */
    public double getAmount() {
        return amount;
    }

    /**
     * @return returns whether the given transaction is reversible
     */
    public boolean getReversible() {
        return !(payee==null || payee instanceof CreditCardAccount);
    }

    @Override
    public String toString(){
        String payeeID;
        if (payee == null) {
            payeeID = "Bill Payment";
        } else payeeID = payee.getAccountID();
        return "From: " + payer.getAccountID() + ", To: " + payeeID +
                ", Amount: " + amount + ", Date: " + date;
    }
}
