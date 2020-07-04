package bankSystem;
import users.*;
import java.io.Serializable;
import java.util.Calendar;

import gui.GuiController;

public class MainBankSystem implements Serializable {

    private static BankInformationStorage BIS;

    private static GuiController userInterface;

    private static BankManager bankManager;

    public static void closeBank(){
        ReadWrite rw = new ReadWrite();
        rw.serialize(BIS);
    }

    public static void main(String[] args) {
        ReadWrite rw = new ReadWrite();

        BIS = (BankInformationStorage) rw.deserialize();
        if (BIS == null) {
            System.out.println("Deserialization error occurred, starting up with default information");
            initializeDefaultBank();
        }
        bankManager = (BankManager)BIS.getUser("e0");
        BIS.updateMonthlyInterest(Calendar.getInstance());

        for (ATM atm: BIS.getAtmsList()){
            userInterface = new GuiController(atm);
            userInterface.startATM();
        }
    }

    /**
     * Creates a new BankInformationSystem with default information
     */
    private static void initializeDefaultBank(){
        bankManager = new BankManager("e0");
        bankManager.changePassWord("potato");
        ATM atm = new ATM();
        BIS = new BankInformationStorage();
        BIS.addATM(atm);
        BIS.addUser(bankManager);
        initializeDefaultCustomer(bankManager);
    }

    /**
     * creates a new Customer with default values and adds it to BIS
     * @param bankManager required to create the new Customer
     */
    private static void initializeDefaultCustomer(BankManager bankManager){
        User customer = bankManager.createNewUser("123456", "customer");
        bankManager.createNewAccount(((Customer)customer), "savings");
        bankManager.createNewAccount(((Customer)customer), "creditcard");
        bankManager.createNewAccount(((Customer)customer), "lineofcredit");
        bankManager.createNewAccount(((Customer)customer), "chequing");
    }


    public static BankInformationStorage getBIS() {
        return BIS;
    }

    public static GuiController getGuiController() {
        return userInterface;
    }

    public static BankManager getBankManager() { return bankManager; }
}
