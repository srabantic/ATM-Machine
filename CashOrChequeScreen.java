package gui;


import javax.swing.*;

public class CashOrChequeScreen extends ParentPanel {

    private JButton cashButton;
    private JButton chequeButton;


    public CashOrChequeScreen() {
        super();
        cashButton = new JButton("Cash Deposit");
        chequeButton = new JButton("Cheque Deposit");
    }

    /**
     * Initialize widgets on the screen
     */
    @Override
    public void setUp(){
        super.setUp();
        setUpCashButton();
        setUpChequeButton();
    }

    /**
     * Create and set properties for cash JButton
     */
    private void setUpCashButton(){
        cashButton.setFont(defaultFont);
        cashButton.addActionListener(e -> {
            guiController.depositRestockCashScreen.changeRestockDepositButtonText("Deposit");
            guiController.pushScreen(guiController.depositRestockCashScreen);
            guiController.setContentPane(guiController.depositRestockCashScreen);
        });
        cashButton.setBounds(115, 85, 195, 35);
        add(cashButton);
    }

    /**
     * Create and set properties for cheque JButton
     */
    private void setUpChequeButton() {
        chequeButton.setFont(defaultFont);
        chequeButton.addActionListener(e -> {
            guiController.pushScreen(guiController.withdrawDepositScreen);
            guiController.setContentPane(guiController.withdrawDepositScreen);
            guiController.withdrawDepositScreen.setIsDeposit(true);
        });
        chequeButton.setBounds(115, 145, 195, 35);
        add(chequeButton);
    }

}
