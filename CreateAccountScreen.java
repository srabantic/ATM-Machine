package gui;

import javax.swing.*;

import accounts.Account;
import accounts.CertificateOfDepositAccount;
import bankSystem.*;
import users.*;

public class CreateAccountScreen extends ParentPanel implements Clearable {

    private JComboBox<String> accountTypesComboBox;
    private JLabel accountTypesLabel;
    private JLabel numOfAccountsLabel;
    private JButton createAccountButton;

    public CreateAccountScreen() { super(); }

    /**
     * Sets the properties (i.e. the font, position, and dimensions) for the widgets of this screen.
     */
    @Override
    public void setUp() {
        super.setUp();
        setUpAccountTypesLabel();
        setUpNumOfAccountsLabel();
        setUpCreateAccountButton();
        setUpAccountTypesComboBox();
    }

    /**
     * Create and set properties for the accountTypes JComboBox.
     */
    private void setUpAccountTypesComboBox() {
        accountTypesComboBox = new JComboBox<>();
        accountTypesComboBox.setFont(defaultFont);
        accountTypesComboBox.insertItemAt("", 0);
        accountTypesComboBox.addItem("Certificate of Deposit");
        accountTypesComboBox.addItem("Chequing");
        accountTypesComboBox.addItem("Credit Card");
        accountTypesComboBox.addItem("High Risk Investment");
        accountTypesComboBox.addItem("Line of Credit");
        accountTypesComboBox.addItem("Savings");
        accountTypesComboBox.setBounds(15, 120, 400, 25 );
        add(accountTypesComboBox);
    }

    /**
     * Create and set properties for the accountTypes JLabel.
     */
    private void setUpAccountTypesLabel() {
        accountTypesLabel = new JLabel("Account Types:");
        accountTypesLabel.setFont(defaultFont);
        accountTypesLabel.setBounds(15, 90, 200, 25);
        add(accountTypesLabel);
    }

    /**
     * Create and set properties for the numOfAccounts JLabel.
     */
    private void setUpNumOfAccountsLabel() {
        numOfAccountsLabel = new JLabel();
        numOfAccountsLabel.setFont(defaultFont);
        numOfAccountsLabel.setBounds(15, 15, 450, 25);
        numOfAccountsLabel.setText("Existing number of accounts: ");
        add(numOfAccountsLabel);
    }

    /**
     * Create and set properties for the createAccount JButton.
     */
    private void setUpCreateAccountButton() {
        createAccountButton = new JButton("Create");
        createAccountButton.setFont(defaultFont);
        createAccountButton.addActionListener(e -> createAccount());
        createAccountButton.setBounds(150, 215, 150, 35);
        add(createAccountButton);
    }

    /**
     * Updates the numOfAccounts JLabel.
     */
    void updateNumOfAccountsLabel() {
        User user = guiController.getUserForMaintenance();
        String numAccounts = Integer.toString(((Customer)user).getNumOfAccount());
        numOfAccountsLabel.setText("Existing number of accounts: " + numAccounts);
    }

    /**
     * Creates an account of the selected type for the specified user, if a type is selected.
     * If not, display an ERROR_MESSAGE.
     */
    private void createAccount() {
        if (accountTypesComboBox.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this, "Please select an account type.", "Failed",
                    JOptionPane.ERROR_MESSAGE);
        } else {
            createAccountHelper();
            updateNumOfAccountsLabel();
            JOptionPane.showMessageDialog(this, "Account created.", "Success",
                    JOptionPane.INFORMATION_MESSAGE);
            clear();
        }
    }

    /**
     * Helper methpd for createAccount. Create an account of the selected type for the specified user.
     */
    private void createAccountHelper() {
        Customer customer = (Customer)guiController.getUserForMaintenance();
        String type = ((String)accountTypesComboBox.getSelectedItem()).replaceAll("\\s+", "");
        Account newAccount = MainBankSystem.getBankManager().createNewAccount(customer, type);
        if (newAccount.getType().equals("Certificate of Deposit")) {
            setTermForAccount(newAccount);
        }
    }

    /**
     * Sets the term for the new certificate of deposit account.
     */
    private void setTermForAccount(Account acc) {
        boolean invalidTerm = true;
        while (invalidTerm) {
            String[] termLengths = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};
            String term = (String) JOptionPane.showInputDialog(null, "Select the term length:",
                    "Set Term Length", JOptionPane.PLAIN_MESSAGE, null, termLengths, "6");
            if (term != null && term.length() > 0) {
                ((CertificateOfDepositAccount) acc).setTerm(Integer.parseInt(term));
                invalidTerm = false;
            } else {
                JOptionPane.showMessageDialog(null, "Please select a term length", "Failed",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }


    /**
     * Clears and updates the account types combo box.
     */
    public void clear() {
        accountTypesComboBox.setSelectedIndex(0);
        updateNumOfAccountsLabel();
    }

}