package gui;

import javax.swing.*;

/**
 * A panel of the ATM where the user withdraws or deposits money.
 */
public class WithdrawDepositScreen extends ParentPanel implements Clearable {
    private JLabel amountLabel;
    private JTextField amountTextField;
    private boolean isDeposit;
    private JButton processButton;

    /**
     * Constructs a WithdrawDeposit panel.
     */
    public WithdrawDepositScreen() {
        super();
        isDeposit = true;

        amountLabel = new JLabel("Amount $ ");
        amountTextField = new JTextField();
        processButton = new JButton( "Process" );
        add(amountLabel);
        add(amountTextField);
        add(processButton);
    }

    /**
     * Sets the properties (i.e. the font, position, and dimensions) for the widgets of this screen.
     */
    @Override
    void setUp(){
        super.setUp();
        setAmountLabel();
        setAmountTextField();
        setProcessButton();
    }

    /**
     * Sets whether deposit is true or not to determine what type of transaction the user wants to do.
     */
    void setIsDeposit(boolean isDeposit) { this.isDeposit = isDeposit; }

    /**
     * Set the properties for the amount text field.
     */
    private void setAmountTextField() {
        amountTextField.setFont(defaultFont);
        amountTextField.setBounds(150, 85, 195, 35);
    }

    /**
     * Set the properties for the amount label.
     */
    private void setAmountLabel() {
        amountLabel.setFont(defaultFont);
        amountLabel.setBounds(50, 85, 195, 35);
    }

    /**
     * Set the properties for the process button.
     */
    private void setProcessButton() {
        processButton.setFont(defaultFont);
        processButton.setBounds(150, 140, 195, 35);
        processButton.addActionListener(e -> processEvent());
    }

    /**
     * Deposits the entered amount of money into the account, or withdraws the entered amount out of the account.
     */
    private void processEvent() {
        double amountOfMoney;
        try {
            amountOfMoney = Double.valueOf(amountTextField.getText());
        } catch (NumberFormatException e) {
            amountOfMoney = -1;
        }
        if (amountOfMoney <= 0) {
            String content = "Please enter a valid amount";
            JOptionPane.showMessageDialog(null, content);
            clear();
        } else {
            isTransfer(amountOfMoney);
        }
    }

    /**
     * Helper method for process button method.
     */
    private void isTransfer(double amount){
        if (guiController.getAccount() == null) {
            JOptionPane.showMessageDialog(null, "You don't have a primary checking account!",
                    "Failed", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (isDeposit) {
            guiController.getAccount().transferMoneyInOut(amount);
        } else if (!guiController.getAccount().ableToWithdraw(amount)) {
            JOptionPane.showMessageDialog(null, "Transaction Failed!",
                    "Failed", JOptionPane.ERROR_MESSAGE);
            return;
        } else {
            guiController.getAccount().transferMoneyInOut(-amount);
        }
        String content = "Transfer was successful!";
        JOptionPane.showMessageDialog(null, content,
                "Success", JOptionPane.INFORMATION_MESSAGE);
        clear();
    }

    /**
     * Clears the text field.
     */
    public void clear() {
        amountTextField.setText("");
    }
}