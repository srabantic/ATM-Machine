package gui;
import users.*;
import javax.swing.*;
import java.util.IllegalFormatException;

/**
 * A panel of the ATM  where an user is able to change their existing password
 */
public class SetPasswordScreen extends ParentPanel implements Clearable {

    private JLabel newPasswordLabel;
    private JTextField newPasswordTextField;
    private JLabel retypeLabel;
    private JTextField retypeTextField;
    private JButton setNewPasswordButton;

    /**
     * Constructs a new SetPassword panel
     */
    public SetPasswordScreen() {

        super();
        this.setSize(450, 325);
        this.setLayout(null);

        newPasswordLabel = new JLabel("Enter New Password");
        newPasswordTextField = new JTextField();
        retypeLabel = new JLabel("Confirm New Password");
        retypeTextField = new JTextField();
        setNewPasswordButton = new JButton("Set Password");

        add(newPasswordLabel);
        add(newPasswordTextField);
        add(retypeLabel);
        add(retypeTextField);
        add(setNewPasswordButton);
    }

    /**
     * Sets the properties (i.e. the font, position, and dimensions) for the widgets of this screen.
     */

    @Override
    void setUp() {
        super.setUp();
        setNewPasswordLabel();
        setNewPasswordTextField();
        setRetypeLabel();
        setRetypeTextField();
        setNewPasswordButton();
    }

    /**
     * Sets the properties of the set new password's JLabel
     */

    private void setNewPasswordLabel(){
        newPasswordLabel.setFont(defaultFont);
        newPasswordLabel.setBounds(50, 87, 195, 35);

    }

    /**
     * Sets the properties of the set new password's textField
     */

    private void setNewPasswordTextField(){
        newPasswordTextField.setFont(defaultFont);
        newPasswordTextField.setBounds(240, 87, 135, 35);

    }

    /**
     * Sets the properties of the retype new password's JLabel
     */

    private void setRetypeLabel(){
        retypeLabel.setFont(defaultFont);
        retypeLabel.setBounds(30, 137, 250, 35);

    }

    /**
     * Sets the properties of the retype new password's textField
     */

    private void setRetypeTextField(){
        retypeTextField.setFont(defaultFont);
        retypeTextField.setBounds(240, 137, 135, 35);

    }

    /**
     * Sets the properties of the set new password button
     */

    private void setNewPasswordButton(){
        setNewPasswordButton.addActionListener( e -> setPasswordEvent());
        setNewPasswordButton.setFont(defaultFont);
        setNewPasswordButton.setBounds(120, 190, 200, 35);

    }

    /**
     * Checks if the password input is less than 6 characters
     * Raises exception if the password length is less than 6 characters
     * @param inputPassword the new password that the user wants to set
     * @return whether the password length is 6 characters or not
     */

    private boolean invalidCharacterException(String inputPassword) {
        try {
            return (inputPassword.length() < 6);

        } catch (IllegalFormatException e) {
            return true;
        }
    }

    /**
     * Checks if the new password and retyped new password matches and raises exception otherwise
     * @param inPassword1 the new password that the user wants to set
     * @param inputPassword2 the retyped new password that the user wants to set
     * @return whether the new password entry matches with the retyped password entry
     */

    private boolean passwordsNotMatchedException(String inPassword1, String inputPassword2) {
        try {
            return (! inPassword1.equals(inputPassword2));

        } catch (IllegalFormatException e) {
            return true;
        }
    }

    /**
     * A helper method of setNewPassWordButton which sets the new password for the
     * existing user
     */

    private void setPasswordEvent() {
        String inputPassword1 = newPasswordTextField.getText();
        String inputPassword2= retypeTextField.getText();

        // executed when the password length is less than 6 characters
        if (invalidCharacterException(inputPassword1)) {
            JOptionPane.showMessageDialog(this, "The password must be at least 6 characters"
                    , "Failed", JOptionPane.ERROR_MESSAGE);

        } else if (passwordsNotMatchedException(inputPassword1, inputPassword2)) {
            JOptionPane.showMessageDialog(this, "passwords do not match"
                    , "Failed", JOptionPane.ERROR_MESSAGE);
        }
        else if(inputPassword1.length() >= 6) {
            // performs the action of changing the user password to the new password
            correctPasswordEvent(inputPassword1);
        }

        clear();
    }

    /**
     * A helper method for setPasswordEvent
     * Changes the user's password to the new password that is inserted
     * @param inputPassword1
     */

    private void correctPasswordEvent(String inputPassword1) {
        newPasswordTextField.repaint();
        User user = guiController.getUser();
        if (user != null) {
            if (!(guiController.getUser() instanceof BankManager) || guiController.getUserForMaintenance() == null) {
                // change the password for the bank manager and other employees
                guiController.getUser().changePassWord(inputPassword1);
            } else {
                // change the password for the customer
                guiController.getUserForMaintenance().changePassWord(inputPassword1);
            }

        }
        JOptionPane.showMessageDialog(this, "Your password has been changed",
                "Success", JOptionPane.INFORMATION_MESSAGE);
    }


    /**
     * Clears the text fields
     */
    public void clear() {
        newPasswordTextField.setText("");
        retypeTextField.setText("");
    }
}