package users;

import bankSystem.ATM;

public class BillRestocker extends Employee {

    /**
     * Construct a new BillRestocker.
     */
    public BillRestocker(String userID) {
        super(userID);
    }

    /**
     * Refill the number of a specific type of bill in an ATM.
     * @param atm the ATM being restocked.
     * @param amountFives the number of $5 bills being restocked.
     * @param amountTens the number of $10 bills being restocked.
     * @param amountTwenties the number of $20 bills being restocked.
     * @param amountFifties the number of $50 bills being restocked.
     */
    public void increaseBills(ATM atm, int amountFives, int amountTens, int amountTwenties, int amountFifties) {
        atm.addBills(5, amountFives);
        atm.addBills(10, amountTens);
        atm.addBills(20, amountTwenties);
        atm.addBills(50, amountFifties);
    }

}