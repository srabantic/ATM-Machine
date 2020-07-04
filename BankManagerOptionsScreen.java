package gui;
import bankSystem.MainBankSystem;
import javax.swing.*;

/**
 * A panel of the ATM where the employee's options are displayed.
 */
public class BankManagerOptionsScreen extends HelpButtonScreen {

    private JButton restockButton;
    private JButton createUserButton;
    private JButton manageUserButton;
    private JButton closeBankButton;

    /**
     * Constructs a new EmployeeOptions Panel
     */
    public BankManagerOptionsScreen() {
        super();
        this.setSize(450, 325);
        this.setLayout(null);

        restockButton = new JButton("Restock ATM");
        createUserButton = new JButton("Create New User");
        manageUserButton = new JButton("Manage Users");
        closeBankButton = new JButton("Close Bank");

        add(restockButton);
        add(createUserButton);
        add(manageUserButton);
        add(closeBankButton);
    }

    /**
     * Sets the properties (i.e. the font, position, and dimensions) for the widgets of this screen.
     *
     */
    @Override
    void setUp() {
        super.setUp();
        restockButton();
        closeBankEButton();
        createUserButton();
        manageUserButton();
        helpButton();
    }

    /**
     * Sets the properties of the Restock button
     */
    private void restockButton(){
        restockButton.addActionListener( e -> { guiController.pushScreen(guiController.depositRestockCashScreen);
            guiController.setContentPane(guiController.depositRestockCashScreen); });
        restockButton.setFont(defaultFont);
        restockButton.setBounds(115, 37, 195, 35);
    }

    /**
     * Sets the properties of the Close Bank button
     */
    private void closeBankEButton(){
        closeBankButton.addActionListener( e -> {MainBankSystem.closeBank();
            String content = "The bank has been closed for tonight";
            JOptionPane.showMessageDialog(null, content);
            System.exit(0); });
        closeBankButton.setFont(defaultFont);
        closeBankButton.setBounds(115, 187, 195, 35);
    }

    /**
     * Sets the properties of the Create User button
     */
    private void createUserButton(){
        createUserButton.addActionListener( e -> { guiController.pushScreen(guiController.createUserScreen);
            guiController.setContentPane(guiController.createUserScreen); });
        createUserButton.setFont(defaultFont);
        createUserButton.setBounds(115, 87, 195, 35);
    }

    /**
     * Sets the properties of the Manage User button
     */
    private void manageUserButton(){
        manageUserButton.addActionListener( e -> { guiController.pushScreen(guiController.searchUserIDScreen);
            guiController.setContentPane(guiController.searchUserIDScreen); });
        manageUserButton.setFont(defaultFont);
        manageUserButton.setBounds(115, 137, 195, 35);
    }

    /**
     * Sets the properties of the help button
     */
    private void helpButton() {
        helpButton.addActionListener(e ->
                JOptionPane.showMessageDialog(null, getMessage("bankManagerOptionsScreenHelp.txt"),
                        "Help", JOptionPane.INFORMATION_MESSAGE));
    }
}
