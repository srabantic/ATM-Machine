package gui;

import javax.swing.*;
import java.util.HashMap;
import accounts.*;
import bankSystem.MainBankSystem;
import users.*;

public class SelectAccountScreen extends HelpButtonScreen implements Clearable {

    private JComboBox accountsComboBox;
    private JLabel accountsLabel;
    private JLabel infoLabel;
    private JButton selectButton;

    public SelectAccountScreen() { super(); }

    /**
     * Sets the properties (i.e. the font, position, and dimensions) for the widgets of this screen.
     */
    @Override
    public void setUp() {
        super.setUp();
        setUpAccountsComboBox();
        setUpAccountsLabel();
        setUpInfoLabel();
        setUpSelectButton();
        setUpHelpButton();
    }

    /**
     * Updates the contents of the accounts combo box.
     */
    void updateAccountsComboBox() {
        clearComboBox();
        Customer customer = (Customer)guiController.getUser();
        HashMap<String, Account> userAccounts = customer.getAllUserAccounts();
        for (String account : userAccounts.keySet()) {
            accountsComboBox.addItem(account);
        }
    }

    /**
     * Clears the contents of the accounts combo box.
     */
    private void clearComboBox() {
        int size = accountsComboBox.getItemCount();
        for (int i = 1; i < size; i++) {
            accountsComboBox.removeItemAt(1);
        }
    }

    /**
     * Create and set properties for accounts JComboBox.
     */
    private void setUpAccountsComboBox() {
        accountsComboBox = new JComboBox();
        accountsComboBox.setFont(defaultFont);
        accountsComboBox.insertItemAt("", 0);
        accountsComboBox.addActionListener(e -> showInfo());
        accountsComboBox.setBounds(15, 50, 400, 25);
        add(accountsComboBox);
    }

    /**
     * Create and set properties for accounts JLabel.
     */
    private void setUpAccountsLabel() {
        accountsLabel = new JLabel("Accounts:");
        accountsLabel.setFont(defaultFont);
        accountsLabel.setBounds(15, 15, 200, 25);
        add(accountsLabel);
    }

    /**
     * Create and set properties for info JLabel.
     */
    private void setUpInfoLabel() {
        infoLabel = new JLabel("");
        infoLabel.setFont(defaultFont);
        infoLabel.setBounds(50, 80, 350, 75);
        add(infoLabel);
    }

    /**
     * Create and set properties for select JButton.
     */
    private void setUpSelectButton() {
        selectButton = new JButton("Select");
        selectButton.setFont(defaultFont);
        selectButton.addActionListener(e -> selectAccount());
        selectButton.setBounds(150, 220, 150, 35);
        add(selectButton);
    }

    /**
     * Set properties for the help JButton.
     */
    private void setUpHelpButton() {
        helpButton.addActionListener(e ->
                JOptionPane.showMessageDialog(null, getMessage("selectAccountScreenHelp.txt"),
                        "Help", JOptionPane.INFORMATION_MESSAGE));
    }
    /**
     * Shows information about the chosen account.
     */
    private void showInfo() {
        Account account = getAccount();
        if (accountsComboBox.getSelectedIndex() == 0) {
            infoLabel.setText("");
        } else if (guiController.peekSecondScreen() == guiController.customerOptionsScreen) {
            showAccountInfo(account);
            } else if (guiController.peekSecondScreen() == guiController.manageUserOptionsScreen) {
            showTransactionInfo(account);
        }
    }

    /**
     * Helper method for showInfo. Displays the account type and balance of the selected account.
     */
    private void showAccountInfo(Account account) {
        String type = account.getType();
        String balance = Double.toString(account.getBalance());
        infoLabel.setText("<html>Account Type: " + type + "<br/>Balance: " + balance + "</html>");
    }

    /**
     * Helper method for showInfo. Displays the amount, payer, and payee of the selected account's last transaction.
     */
    private void showTransactionInfo(Account account) {
        Transaction trans = account.getLastTransaction();
        if (trans != null) {
            infoLabel.setText("<html>Last Transaction" + "<br/>Amount: " + trans.getAmount() + "<br/>Payer: " +
                    trans.getPayer().getAccountID() + "<br/>Payee" + trans.getPayee().getAccountID() + "<html/>");
        } else {
            JOptionPane.showMessageDialog(null, "No transactions found for this account.");
        }
    }

    /**
     * Return the selected Account.
     * @return the selected Account.
     */
    private Account getAccount() {
        String accountID = (String)accountsComboBox.getSelectedItem();
        User customer = guiController.getUser();
        return ((Customer)customer).getSpecificAccount(accountID);
    }
    /**
     * Directs user to Account Functions Screen, if an account is selected.
     * If not, display an ERROR_MESSAGE.
     */
    private void selectAccount() {
        if (accountsComboBox.getSelectedIndex() <= 0) {
            JOptionPane.showMessageDialog(this, "Please select an account.", "Failed",
                    JOptionPane.ERROR_MESSAGE);
        } else {
            if (guiController.peekSecondScreen() == guiController.customerOptionsScreen) {
                switchToAccountFunctionsScreen();
            } else if (guiController.peekSecondScreen() == guiController.manageUserOptionsScreen) {
                reverseTransaction();
                clear();
            }
        }
    }

    /**
     * Helper method for selectAccount. Switches screens.
     */
    private void switchToAccountFunctionsScreen() {
        User user = guiController.getUser();
        Customer customer = ((Customer)user);
        guiController.setAccount(customer.getSpecificAccount((String)accountsComboBox.getSelectedItem()));
        guiController.pushScreen(guiController.accountFunctionsScreen);
        guiController.setContentPane(guiController.accountFunctionsScreen);
        enableAccFuncButtons();
    }

    /**
     * Helper method for selectAccount. The last transaction of the selected account is reversed.
     */
    private void reverseTransaction() {
        MainBankSystem.getBankManager().reverseTransaction(getAccount());
        JOptionPane.showMessageDialog(this, "The transaction has been reversed.",
                "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Enable/disable the buttons in AccountFunctionsScreen depending on what type of account is being accessed.
     */
    private void enableAccFuncButtons() {
        Account account = getAccount();
        switch(account.getType().toLowerCase()) {
            case "chequing" : enableAccFuncButtonsHelper(
                    true, true, true, true, true);
            case "savings" : enableAccFuncButtonsHelper(
                    true, true, true, true, true);
            case "creditcard" : enableAccFuncButtonsHelper(
                    true, true, false, false, true);
            case "lineofcredit" : enableAccFuncButtonsHelper(
                    true, true, true, true, true);
            case "certificateofdeposit" : enableAccFuncButtonsHelper(
                    true, true, true, true, true);
            case "highriskinvestment" : enableAccFuncButtonsHelper(
                    true, true, true, true, true);
        }
    }

    /**
     * Helper method for enableAccFuncButtons(). Enable/Disables the buttons in AccountFunctionsScreen.
     * @param canWithdraw whether or not the account can withdraw.
     * @param canDeposit whether or not the account can deposit.
     * @param canTransfer whether or not the account can transfer.
     * @param canPayBills whether or not the account can pay bills.
     * @param canJoin whether or not the account can be joint.
     */
    private void enableAccFuncButtonsHelper(boolean canWithdraw, boolean canDeposit,
                                            boolean canTransfer, boolean canPayBills, boolean canJoin) {
        JButton withdraw = guiController.accountFunctionsScreen.getWithdrawButton();
        JButton deposit = guiController.accountFunctionsScreen.getDepositButton();
        JButton transfer = guiController.accountFunctionsScreen.getTransferButton();
        JButton payBills = guiController.accountFunctionsScreen.getPayBillsButton();
        JButton join = guiController.accountFunctionsScreen.getAddJointAccountHolderButton();
        withdraw.setEnabled(canWithdraw);
        deposit.setEnabled(canDeposit);
        transfer.setEnabled(canTransfer);
        payBills.setEnabled(canPayBills);
        join.setEnabled(canJoin);
    }

    /**
     * Clears the combo box and info labels.
     */
    public void clear() {
        accountsComboBox.setSelectedIndex(0);
        infoLabel.setText("");
        updateAccountsComboBox();
    }
}