package gui;

import accounts.Account;
import bankSystem.ATM;
import users.BillRestocker;

import javax.swing.*;

/**
 * A panel of the ATM where the user enters how much of each bill they would like to deposit or restock, depending on
 * the type of user.
 */
public class DepositRestockCashScreen extends ParentPanel implements Clearable {

    /** The label for the $5 input. */
    private JLabel fiveLabel;

    /** The label for the $10 input. */
    private JLabel tenLabel;

    /** The label for the $20 input. */
    private JLabel twentyLabel;

    /** The label for the $50 input. */
    private JLabel fiftyLabel;

    /** The text field for the $5 input. */
    private JTextField fiveTextField;

    /** The text field for the $10 input. */
    private JTextField tenTextField;

    /** The text field for the $20 input. */
    private JTextField twentyTextField;

    /** The text field for the $50 input. */
    private JTextField fiftyTextField;

    /** The restock/deposit button. */
    private JButton restockDepositButton;

    /** The number of $5 bills inputted. */
    private Integer fives;

    /** The number of $10 bills inputted. */
    private Integer tens;

    /** The number of $20 bills inputted. */
    private Integer twenties;

    /** The number of $50 bills inputted. */
    private Integer fifties;

    /**
     * Constructs a new DepositInfoATM panel.
     */
    public DepositRestockCashScreen() {
        super();

        fiveLabel = new JLabel("$5");
        tenLabel = new JLabel("$10");
        twentyLabel = new JLabel("$20");
        fiftyLabel = new JLabel("$50");

        fiveTextField = new JTextField("0");
        tenTextField = new JTextField("0");
        twentyTextField = new JTextField("0");
        fiftyTextField = new JTextField("0");

        restockDepositButton = new JButton("Restock");

        add(fiveLabel);
        add(tenLabel);
        add(twentyLabel);
        add(fiftyLabel);
        add(fiveTextField);
        add(tenTextField);
        add(twentyTextField);
        add(fiftyTextField);
        add(restockDepositButton);
    }

    /**
     * Sets the properties (i.e. the font, position, and dimensions) for the widgets of this screen.
     */
    @Override
    void setUp() {
        super.setUp();
        setFiveLabel();
        setTenLabel();
        setTwentyLabel();
        setFiftyLabel();

        setFiveTextField();
        setTenTextField();
        setTwentyTextField();
        setFiftyTextField();

        setRestockDepositButton();
    }

    /**
     * Set the properties for the five label.
     */
    private void setFiveLabel() {
        fiveLabel.setFont(defaultFont);
        fiveLabel.setBounds(165, 5, 40, 35);
    }

    /**
     * Set the properties for the ten label.
     */
    private void setTenLabel() {
        tenLabel.setFont(defaultFont);
        tenLabel.setBounds(155, 55, 40, 35);
    }

    /**
     * Set the properties for the twenty label.
     */
    private void setTwentyLabel() {
        twentyLabel.setFont(defaultFont);
        twentyLabel.setBounds(155, 105, 40, 35);
    }

    /**
     * Set the properties for the fifty label.
     */
    private void setFiftyLabel() {
        fiftyLabel.setFont(defaultFont);
        fiftyLabel.setBounds(155, 155, 40, 35);
    }

    /**
     * Set the properties for the five text field.
     */
    private void setFiveTextField() {
        fiveTextField.setFont(defaultFont);
        fiveTextField.setBounds(195, 5, 80, 35);
    }

    /**
     * Set the properties for the ten text field.
     */
    private void setTenTextField() {
        tenTextField.setFont(defaultFont);
        tenTextField.setBounds(195, 55, 80, 35);
    }

    /**
     * Set the properties for the twenty text field.
     */
    private void setTwentyTextField() {
        twentyTextField.setFont(defaultFont);
        twentyTextField.setBounds(195, 105, 80, 35);
    }

    /**
     * Set the properties for the fifty text field.
     */
    private void setFiftyTextField() {
        fiftyTextField.setFont(defaultFont);
        fiftyTextField.setBounds(195, 155, 80, 35);
    }

    /**
     * Set the properties for the restock/deposit button.
     */
    private void setRestockDepositButton() {
        restockDepositButton.addActionListener(e -> restockDepositEvent());
        restockDepositButton.setFont(defaultFont);
        restockDepositButton.setBounds(165, 205, 110, 35);
    }

    /**
     * Changes the current text on the RestockDeposit button to what is passed in.
     *
     * @param newText The new text on the button (String)
     */
    void changeRestockDepositButtonText(String newText) { restockDepositButton.setText(newText); }

    /**
     * Checks if the inputted text in the JTextFields is valid input.
     *
     * @return boolean - true if the input is invalid and false otherwise
     */
    private boolean invalidInput() {
        try {
            fives = Integer.parseInt(fiveTextField.getText());
            tens = Integer.parseInt(tenTextField.getText());
            twenties = Integer.parseInt(twentyTextField.getText());
            fifties = Integer.parseInt(fiftyTextField.getText());
            return (fives < 0 || tens < 0 || twenties < 0 || fifties < 0);
        } catch (NumberFormatException e) {
            return true;
        }
    }

    /**
     * Adds the bills to the ATM.
     */
    private void restockDepositEvent() {
        if (invalidInput()) {
            JOptionPane.showMessageDialog(null, "Non-negative integers only",
                    "Failed", JOptionPane.ERROR_MESSAGE);
            return;
        }
        ATM atm = guiController.getATM();
        addBills(atm);
        if ( restockDepositButton.getText().equals("Deposit") ) {
            depositIntoCustomerAccount();
        } else {
            JOptionPane.showMessageDialog(null, "Successfully restocked bills!");
        }
        clear();
    }

    /**
     * Helper method for restockDepositEvent. Deposits the specified amount into the specified account.
     */
    private void depositIntoCustomerAccount() {
        Account account = guiController.getAccount();
        int amount = fives*5 + tens*10 + twenties*20 + fifties*50;
        account.transferMoneyInOut(amount);
        JOptionPane.showMessageDialog(null, "$" + amount + ".00 has been deposited.",
                "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Have the bill restocker restock the bills in the ATM
     *
     * @param atm The ATM to restock (ATM)
     */
    private void addBills(ATM atm) {
        BillRestocker billRestocker = (BillRestocker)guiController.getUser();
        billRestocker.increaseBills(atm, fives, tens, twenties, fifties);
    }

    /**
     * Clears the text fields after successfully depositing/restocking cash
     */
    public void clear() {
        fiveTextField.setText("0");
        tenTextField.setText("0");
        twentyTextField.setText("0");
        fiftyTextField.setText("0");
    }
}
