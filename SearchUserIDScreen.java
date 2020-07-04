package gui;
import bankSystem.*;
import users.Customer;
import users.User;


import javax.swing.*;

public class SearchUserIDScreen extends ParentPanel implements Clearable{

    private JTextField userIDTextField;
    private JLabel userIDLabel;
    private JButton searchButton;

    public SearchUserIDScreen() { super(); }

    /**
     * Sets the properties (i.e. the font, position, and dimensions) for the widgets of this screen.
     */
    @Override
    public void setUp() {
        super.setUp();
        setUpeUserIDTextField();
        setUpUserIDLabel();
        setUpSearchButton();
    }

    /**
     * Create and set up userID JTextField.
     */
    private void setUpeUserIDTextField() {
        userIDTextField = new JTextField();
        userIDTextField.setFont(defaultFont);
        userIDTextField.setBounds(130, 100, 200, 35);
        add(userIDTextField);
    }

    /**
     * Create and set up userID JLabel.
     */
    private void setUpUserIDLabel() {
        userIDLabel = new JLabel("User ID:");
        userIDLabel.setFont(defaultFont);
        userIDLabel.setBounds(15, 100, 100, 25);
        add(userIDLabel);
    }

    /**
     * Create and set up search JButton.
     */
    private void setUpSearchButton() {
        searchButton = new JButton("Search");
        searchButton.setFont(defaultFont);
        searchButton.addActionListener(e -> searchForUser());
        searchButton.setBounds(150, 200, 150, 35);
        add(searchButton);
    }

    /**
     * If the input user is valid, search for user and
     *  1) Open manageUserOptionsScreen for that user, or
     *  2) Add the user to the specified account.
     * If the input user in invalid, display an ERROR_MESSAGE.
     */
    private void searchForUser() {
        String user = userIDTextField.getText();
        if (MainBankSystem.getBIS().getUser(user) == null) {
            JOptionPane.showMessageDialog(this, "This user does not exist.", "Failed",
                    JOptionPane.ERROR_MESSAGE);
        } else {
            if (guiController.peekSecondScreen() == guiController.bankManagerOptionsScreen) {
                switchtoBankManagerOptionScreen(user);
            } else if (guiController.peekSecondScreen() == guiController.accountFunctionsScreen) {
                addJointUser(user);
                clear();
            }
        }
    }

    /**
     * Helper method for searchForUser. Switches screens.
     */
    private void switchtoBankManagerOptionScreen(String user) {
        guiController.setUserForMaintenance((Customer) (MainBankSystem.getBIS().getUser(user)));
        guiController.pushScreen(guiController.manageUserOptionsScreen);
        guiController.setContentPane(guiController.manageUserOptionsScreen);
    }

    /**
     * Helper method for searchForUser. Adds an existing account to another user.
     */
    private void addJointUser(String user) {
        User searchedUser = MainBankSystem.getBIS().getUser(user);
        if (((Customer)searchedUser).getSpecificAccount(guiController.getAccount().getAccountID()) == null) {
            ((Customer)searchedUser).addJointAccount(guiController.getAccount());
            JOptionPane.showMessageDialog(this, "The user has successfully been added",
                    "Success", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "This user is already an owner of this account",
                    "Failed", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    /**
     * Clears the text field.
     */
    public void clear() {
        userIDTextField.setText("");
    }
}
