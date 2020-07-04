package gui;

import javax.swing.*;

/**
 * A panel of the ATM where the manage user's options are displayed.
 */

public class ManageUserOptionsScreen extends ParentPanel {
    private JButton reverseTransactionButton;
    private JButton createAccountButton;
    private JButton resetPasswordButton;

    /**
     * Constructs a new ManageUserOptions panel.
     */
    public ManageUserOptionsScreen() {
        super();

        reverseTransactionButton = new JButton( "Reverse Transaction" );
        createAccountButton = new JButton( "Create Account" );
        resetPasswordButton = new JButton( "Reset Password" );

        add(reverseTransactionButton);
        add(createAccountButton);
        add(resetPasswordButton);
    }

    /**
     * Sets the properties (i.e. the font, position, and dimensions) for the widgets of this screen.
     */
    @Override
    void setUp() {
        super.setUp();
        setReverseTransaction();
        setCreateAccount();
        setResetPassword();
    }

    /**
     * Set the properties for the reverse transaction button.
     */
    private void setReverseTransaction() {
        reverseTransactionButton.addActionListener( e -> {
            guiController.pushScreen(guiController.selectAccountScreen);
            guiController.setContentPane(guiController.selectAccountScreen); });

        reverseTransactionButton.setFont(defaultFont);
        reverseTransactionButton.setBounds(115, 35, 220, 35);
    }

    /**
     * Set the properties for the create account button.
     */
    private void setCreateAccount() {
        createAccountButton.addActionListener( e -> {
            guiController.createAccountScreen.updateNumOfAccountsLabel();
            guiController.pushScreen(guiController.createAccountScreen);
            guiController.setContentPane(guiController.createAccountScreen); });

        createAccountButton.setFont(defaultFont);
        createAccountButton.setBounds(115, 85, 220, 35);
    }

    /**
     * Set the properties for the reset password button.
     */
    private void setResetPassword() {
        resetPasswordButton.addActionListener( e -> {
            guiController.pushScreen(guiController.setPasswordScreen);
            guiController.setContentPane(guiController.setPasswordScreen); });

        resetPasswordButton.setFont(defaultFont);
        resetPasswordButton.setBounds(115, 135, 220, 35);
    }
}
