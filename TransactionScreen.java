package gui;
import accounts.*;
import users.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 *  A panel of the ATM where the the user is able transfer money and pay  bills
 */

public class TransactionScreen extends ParentPanel implements Clearable {

    private JLabel transactionLabel;
    private JTextField transactionTextField;
    private JLabel amountLabel;
    private JTextField amountTextField;
    private JButton transactionButton;
    private boolean isTransfer;
    private JComboBox accountsComboBox;

    /**
     * constructs a new Transaction Panel
     */

    public TransactionScreen() {
        super();
        this.setSize(450, 325);
        this.setLayout(null);

        transactionLabel = new JLabel();
        transactionTextField = new JTextField();
        amountLabel = new JLabel("Enter Amount $");
        amountTextField = new JTextField();
        transactionButton = new JButton("Perform Transaction");
        accountsComboBox = new JComboBox();

        add(transactionLabel);
        add(transactionTextField);
        add(amountLabel);
        add(amountTextField);
        add(transactionButton);
        add(accountsComboBox);

        accountsComboBox.setVisible(false);
    }

    /**
     * Sets the properties (i.e. the font, position, and dimensions) for the widgets of this screen
     */

    @Override
    public void setUp() {
        super.setUp();
        setTransactionLabel();
        setTransactionTextField();
        setAmountLabel();
        setAmountTextField();
        setTransactionButton();
        createAccountsComboBox();
    }

    private void createAccountsComboBox() {
        accountsComboBox.setFont(defaultFont);
        accountsComboBox.insertItemAt("", 0);
        accountsComboBox.setBounds(180, 87, 195, 35);
        accountsComboBox.setVisible(true);
    }

    /**
     * Adds the user accounts to the JComboBox
     */
    private void updateComboBox() {
        clearComboBox();
        try {
            User user = guiController.getUser();
            List<Account> userAccounts = new ArrayList<Account>(((Customer)user).getAllUserAccounts().keySet());
            for (int i = 0; i < userAccounts.size(); i++) {
                if (! guiController.getAccount().getAccountID().equals(userAccounts.get(i)) ) {
                    accountsComboBox.addItem(userAccounts.get(i));
                }
            }
        } catch (NullPointerException e) {
            return;
        }
        accountsComboBox.setVisible(true);
    }

    /**
     * Clears the first element from the accounts combo box
     */
    private void clearComboBox() {
        int size = accountsComboBox.getItemCount();
        for (int i = 1; i < size; i++) {
            accountsComboBox.removeItemAt(1);
        }
    }

    /**
     * Sets the properties of the transaction label
     */
    private void setTransactionLabel(){
        if (this.isTransfer){
            transactionLabel.setText("Select an account");
            transactionLabel.setBounds(20, 87, 195, 35);
        }
        else{
            transactionLabel.setText("Payee");
            transactionLabel.setBounds(120, 87, 195, 35);
        }
        transactionLabel.setFont(defaultFont);
    }

    /**
     * Sets the properties of the transaction text field
     */
    private void setTransactionTextField(){
        if(this.isTransfer){
            transactionTextField.setVisible(false);
            updateComboBox();
        }
        else{
            accountsComboBox.setVisible(false);
            transactionTextField.setVisible(true);
            transactionTextField.setFont(defaultFont);
            transactionTextField.setBounds(180, 87, 195, 35);
        }
    }

    /**
     * Sets isTransfer to true or false
     * @param isTransfer whether the transfer money was selected or not from the
     * AccountsOptionsScreen
     */
    public void setIsTransfer(boolean isTransfer) {
        this.isTransfer = isTransfer;
        setTransactionLabel();
        setTransactionTextField();
    }

    /**
     * Sets the properties of the amount label
     */
    private void setAmountLabel(){
        amountLabel.setFont(defaultFont);
        amountLabel.setBounds(40, 137, 250, 35);
    }

    /**
     * Sets the properties of the amount text field
     */

    private void setAmountTextField(){
        amountTextField.setFont(defaultFont);
        amountTextField.setBounds(180, 137, 195, 35);
    }

    /**
     * Sets the properties of the transaction button
     */
    private void setTransactionButton(){
        transactionButton.addActionListener( e -> {
            if (this.isTransfer){
                transferEvent();}
            else{
                payBillEvent();} });

        transactionButton.setFont(defaultFont);
        transactionButton.setBounds(110, 190, 220, 35);
    }

    /**
     * Decides whether transferring money into a different accounts is possible or not
     * and performs the action of transferring money
     */
    private void transferEvent(){
        if (accountsComboBox.getSelectedIndex() == 0){
            JOptionPane.showMessageDialog(this, "Please select an account",
                    "Failed", JOptionPane.ERROR_MESSAGE);
        }
        else{
            User user = guiController.getUser();
            Account account = ((Customer)user).getSpecificAccount((String)accountsComboBox.getSelectedItem());
            // performs the transaction of transferring money
            transferPerformed(account, Double.parseDouble(amountTextField.getText()));
        }
    }

    /**
     * Transfer the money into the specified payee account with the specified amount
     * @param payee the account where the money is being transferred to
     * @param amount the amount that is being transferred
     */
    private void transferPerformed(Object payee, double amount) {
        Account account = guiController.getAccount();
        String content;
        String message = "Failed";
        int jOptionMessage = JOptionPane.ERROR_MESSAGE;
        if (account.transfer(amount, (Account) payee)) {
            content = "Money has been transferred!";
            message = "Success";
            jOptionMessage = JOptionPane.INFORMATION_MESSAGE;

        } else {
            content = "Please make sure you have enough balance to transfer the amount selected";
        }
        JOptionPane.showMessageDialog(this, content, message, jOptionMessage);
        clear();
    }

    /**
     * Decides whether bill payment is possible
     * and performs the action of bill payment
     */
    private void payBillEvent() {
        double amount;
        // this try, catch block is executed if there was not any amount entered
        try {
            amount = Double.parseDouble(amountTextField.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid amount",
                    "Failed", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String payee = transactionTextField.getText();
        if (transactionLabel.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Please select a payee",
                    "Failed", JOptionPane.ERROR_MESSAGE);
        } else if (amount <= 0) {
            JOptionPane.showMessageDialog(this, "Please select an amount greater than 0",
                    "Failed", JOptionPane.ERROR_MESSAGE);
        } else {
            // performs the action of paying bills
            payBillPerformed(payee, amount);

        }
    }

    /**
     * Performs the bill payment to the specified payee with the specified amount
     * @param payee where the payment is going to be paid
     * @param amount the amount that will be paid
     */
    private void payBillPerformed(String payee, double amount){
        Account account = guiController.getAccount();
        String content;
        String message = "Failed";
        int jOptionMessage = JOptionPane.ERROR_MESSAGE;
        if (account.payBill(amount, payee)) {
            content = "Bill Payment was successful!";
            message = "Success";
            jOptionMessage = JOptionPane.INFORMATION_MESSAGE;

        } else {
            content = "Please make sure you have enough balance to transfer the amount selected";
        }
        JOptionPane.showMessageDialog(this, content, message, jOptionMessage);
        clear();
    }

    /**
     * Clears the text fields and combo box
     */
    public void clear() {
        amountTextField.setText("");
        transactionTextField.setText("");
        if (isTransfer) {
            createAccountsComboBox();
        }
    }

}