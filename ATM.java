package bankSystem;

import java.io.IOException;
import java.io.Serializable;
import accounts.Account;
import java.util.HashMap;

@SuppressWarnings("CatchMayIgnoreException")
public class ATM implements Serializable {

    /**
     * Stores number of 5, 10, 20, and 50 dollar bills in this ATM.
     */
    private HashMap<Integer, Integer> numOfBills;


    public ATM() {
        numOfBills = new HashMap<>();
        numOfBills.put(5, 0);
        numOfBills.put(10, 0);
        numOfBills.put(20, 0);
        numOfBills.put(50, 0);
    }

    /**
     * Add more bills to this ATM.
     *
     * @param typeOfBill value of bills being added.
     * @param quantity   number of bills being added.
     */
    public void addBills(int typeOfBill, int quantity) {
        int newQuantity = numOfBills.get(typeOfBill) + quantity;
        numOfBills.put(typeOfBill, newQuantity);
    }

    /**
     * Calculates and returns the total amount of money in this ATM.
     *
     * @return the total amount of money in this ATM.
     */
    public int totalATMBalance() {
        return numOfBills.get(5) * 5 + numOfBills.get(10) * 10 + numOfBills.get(20) * 20 + numOfBills.get(50) * 50;
    }

    /**
     * Withdraw bills from this ATM, if possible.
     *
     * @param account   the account being withdrawn from.
     * @param requested the amount of money being withdrawn.
     * @return message indicating whether or not the withdrawal was successful.
     */
    public String withdraw(Account account, int requested) {
        if (possibleToWithdraw(requested)) {
            int fiftiesToWithdraw = withdrawalCalculator(50, requested);
            int remainingQuantity = requested - 50 * fiftiesToWithdraw;

            int twentiesToWithdraw = withdrawalCalculator(20, remainingQuantity);
            remainingQuantity -= 20 * twentiesToWithdraw;

            int tensToWithdraw = withdrawalCalculator(10, remainingQuantity);
            remainingQuantity -= 10 * tensToWithdraw;

            int fivesToWithdraw = withdrawalCalculator(5, remainingQuantity);

            if (remainingQuantity - 5 * fivesToWithdraw == 0) {
                account.transferMoneyInOut(-requested);
                numOfBills.put(50, numOfBills.get(50 - fiftiesToWithdraw));
                numOfBills.put(20, numOfBills.get(20 - twentiesToWithdraw));
                numOfBills.put(10, numOfBills.get(10 - tensToWithdraw));
                numOfBills.put(5, numOfBills.get(5 - fivesToWithdraw));
                checkLowBalance();
                return requested + " has successfully been withdrawn.";
            }
        }
        return requested + " has not been successfully withdrawn.";
    }


    /**
     * Determine whether or not it is possible with withdraw a requested amount of money from this ATM.
     *
     * @param quantity the amount of money being requested.
     * @return whether or not it is possible to withdrawn the requested amount of money.
     */
    private boolean possibleToWithdraw(int quantity) {
        return quantity % 5 == 0 && quantity <= totalATMBalance();
    }


    /**
     * Calculate the number of a certain type of bill to withdraw.
     *
     * @param typeOfBill the value of the certain type of bill.
     * @param requested  the amount of money being withdrawn.
     * @return int - the number of the type of bill to withdraw
     */
    private int withdrawalCalculator(int typeOfBill, int requested) {
        int maxNum = requested / typeOfBill;
        if (maxNum > numOfBills.get(typeOfBill)) {
            return numOfBills.get(typeOfBill);
        } else {
            return maxNum;
        }
    }


    /**
     * Check if the ATM is low on any bills.
     */
    @SuppressWarnings("CatchMayIgnoreException")
    public void checkLowBalance() {
        for (int n : numOfBills.keySet()) {
            if (numOfBills.get(n) < 20) {
                try {
                    sendLowBalanceAlert(n);
                } catch (IOException ioe) {}
            }
        }
    }


    /**
     * Write to a alerts.txt when the ATM is low on a certain type of bill.
     *
     * @param billType the type of bill the ATM is low on.
     * @throws IOException
     */
    private void sendLowBalanceAlert(int billType) throws IOException {
        ReadWrite rw = new ReadWrite();
        rw.writeToFile("phase2/alerts.txt", "The ATM has less than twenty $" + billType +
                " bills remaining. Please restock.\n");
    }

}
