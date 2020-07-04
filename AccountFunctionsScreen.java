package gui;

import javax.swing.*;

public class AccountFunctionsScreen extends ParentPanel {

    private JButton depositButton;
    private JButton withdrawButton;
    private JButton transferButton;
    private JButton payBillsButton;
    private JButton addJointAccountHolderButton;


    public AccountFunctionsScreen() {
        super();
        depositButton = new JButton("Deposit");
        withdrawButton = new JButton("Withdraw");
        transferButton = new JButton("Transfer");
        payBillsButton = new JButton("Pay Bills");
        addJointAccountHolderButton = new JButton("Add Account Holder");
    }

    /**
     * Initialize widgets on the screen
     */
    @Override
    public void setUp() {
        super.setUp();
        setUpDepositButton();
        setUpWithdrawButton();
        setUpTransferButton();
        setUpPayBillsButton();
        setUpJointAccountButton();
    }

    /**
     * Create and set properties for jointAccount JButton
     */
    private void setUpJointAccountButton(){
        addJointAccountHolderButton.setFont(defaultFont);
        addJointAccountHolderButton.setBounds(110, 200, 210, 35);
        addJointAccountHolderButton.addActionListener(e -> {
            guiController.pushScreen(guiController.searchUserIDScreen);
            guiController.setContentPane(guiController.searchUserIDScreen);
        });
        add(addJointAccountHolderButton);
    }

    /**
     * Create and set properties for deposit JButton
     */
    private void setUpDepositButton(){
        depositButton.setFont(defaultFont);
        depositButton.setBounds(110, 20, 210, 35);
        depositButton.addActionListener(e -> {
            guiController.pushScreen(guiController.cashOrChequeScreen);
            guiController.setContentPane(guiController.cashOrChequeScreen);
        });
        add(depositButton);
    }

    /**
     * Create and set properties for withdraw JButton
     */
    private void setUpWithdrawButton(){
        withdrawButton.setFont(defaultFont);
        withdrawButton.setBounds(110, 65, 210, 35);
        withdrawButton.addActionListener(e -> {
            guiController.pushScreen(guiController.withdrawDepositScreen);
            guiController.setContentPane(guiController.withdrawDepositScreen);
            guiController.withdrawDepositScreen.setIsDeposit(false);
        });
        add(withdrawButton);
    }

    /**
     * Create and set properties for transfer JButton
     */
    private void setUpTransferButton(){
        transferButton.setFont(defaultFont);
        transferButton.setBounds(110, 110, 210, 35);
        transferButton.addActionListener(e -> {
            guiController.pushScreen(guiController.transactionScreen);
            guiController.setContentPane(guiController.transactionScreen);
            guiController.transactionScreen.setIsTransfer(true);
        });
        add(transferButton);
    }

    /**
     * Create and set properties for payBills JButton
     */
    private void setUpPayBillsButton(){
        payBillsButton.setFont(defaultFont);
        payBillsButton.setBounds(110, 155, 210, 35);
        payBillsButton.addActionListener(e -> {
            guiController.pushScreen(guiController.transactionScreen);
            guiController.setContentPane(guiController.transactionScreen);
            guiController.transactionScreen.setIsTransfer(false);
        });
        add(payBillsButton);
    }

    /**
     * returns deposit JButton
     * @return returns deposit JButton
     */
    public JButton getDepositButton() { return depositButton; }

    /**
     * returns withdraw JButton
     * @return returns withdraw JButton
     */
    public JButton getWithdrawButton() { return withdrawButton; }

    /**
     * returns transfer JButton
     * @return returns transfer JButton
     */
    public JButton getTransferButton() { return transferButton; }

    /**
     * returns payBills JButton
     * @return returns payBills JButton
     */
    public JButton getPayBillsButton() { return payBillsButton; }

    /**
     * returns addJointAccountHolder JButton
     * @return returns addJointAccountHolder JButton
     */
    public JButton getAddJointAccountHolderButton() { return addJointAccountHolderButton; }
}