package gui;

import users.*;

import javax.swing.*;

/**
 * A panel of the ATM where the customer's options are displayed.
 */
public class CustomerOptionsScreen extends HelpButtonScreen {
    private JButton accountsButton;
    private JButton quickDepositButton;
    private JButton quickWithdrawButton;
    private JButton changePasswordButton;

    /**
     * Constructs a new CustomerOptions panel.
     */
    public CustomerOptionsScreen() {
        super();

        accountsButton = new JButton("Accounts");
        quickDepositButton = new JButton("Quick Deposit");
        quickWithdrawButton = new JButton("Quick Withdraw");
        changePasswordButton = new JButton("Change Password");

        add(accountsButton);
        add(quickDepositButton);
        add(quickWithdrawButton);
        add(changePasswordButton);
    }

    /**
     * Sets the properties (i.e. the font, position, and dimensions) for the widgets of this screen.
     */
    @Override
    void setUp() {
        super.setUp();
        setAccountsButton();
        setQuickDepositButton();
        setQuickWithdrawButton();
        setChangePasswordButton();
        setHelpButton();
    }

    /**
     * Set the properties for the account button.
     */
    private void setAccountsButton() {
        accountsButton.addActionListener( e -> switchToSelectAccountScreen());
        accountsButton.setFont(defaultFont);
        accountsButton.setBounds(115, 37, 195, 35);
    }

    /**
     * Switches screen to selectAccountScreen.
     */
    private void switchToSelectAccountScreen() {
        guiController.selectAccountScreen.updateAccountsComboBox();
        guiController.pushScreen(guiController.selectAccountScreen);
        guiController.setContentPane(guiController.selectAccountScreen);
    }

    /**
     * Set the properties for the quick deposit button.
     */
    private void setQuickDepositButton() {
        quickDepositButton.addActionListener( e -> switchToCashOrChequeScreen());
        quickDepositButton.setFont(defaultFont);
        quickDepositButton.setBounds(115, 87, 195, 35);
    }

    /**
     * Switches screen to CashOrChequeScreen. Sets account being worked on.
     */
    private void switchToCashOrChequeScreen() {
        User customer = guiController.getUser();
        guiController.setAccount(((Customer)customer).getPrimaryAccount());
        guiController.pushScreen(guiController.cashOrChequeScreen);
        guiController.setContentPane(guiController.cashOrChequeScreen);
    }

    /**
     * Set the properties for the quick withdraw button.
     */
    private void setQuickWithdrawButton() {
        quickWithdrawButton.addActionListener( e -> switchToWithdrawDepositScreen());
        quickWithdrawButton.setFont(defaultFont);
        quickWithdrawButton.setBounds(115, 137, 195, 35);
    }

    /**
     * Switches screen to WithdrawDepositScreen. Sets account being worked on.
     */
    private void switchToWithdrawDepositScreen() {
        User customer = guiController.getUser();
        guiController.setAccount(((Customer)customer).getPrimaryAccount());
        guiController.withdrawDepositScreen.setIsDeposit(false);
        guiController.pushScreen(guiController.withdrawDepositScreen);
        guiController.setContentPane(guiController.withdrawDepositScreen);
    }

    /**
     * Set the properties for the change password button.
     */
    private void setChangePasswordButton() {
        changePasswordButton.addActionListener( e -> switchToSetPasswordScreen());
        changePasswordButton.setFont(defaultFont);
        changePasswordButton.setBounds(115, 187, 195, 35);
    }

    /**
     * Switches screen to SetPasswordScreen.
     */
    private void switchToSetPasswordScreen() {
            guiController.pushScreen(guiController.setPasswordScreen);
            guiController.setContentPane(guiController.setPasswordScreen);
    }

    /**
     * Set the properties for the help button.
     */
    private void setHelpButton() {
        helpButton.addActionListener(e ->
                JOptionPane.showMessageDialog(null, getMessage("customerOptionsScreenHelp.txt"),
                        "Help", JOptionPane.INFORMATION_MESSAGE));
    }
}