package gui;

import bankSystem.*;
import javax.swing.*;
import users.*;

/**
 * A panel of the ATM where the user is created.
 */
public class CreateUserScreen extends ParentPanel implements Clearable {
    private JLabel selectUserLabel;
    private String[] typeOfUsers = {"Customer", "Bill Restocker"};
    private JComboBox<String> cmbUserList = new JComboBox<>(typeOfUsers);
    private JLabel typePasswordLabel;
    private JTextField typePasswordTextField;
    private JLabel reTypePasswordLabel;
    private JTextField reTypePasswordTextField;
    private JButton createButton;

    /**
     * Constructs a new CreateUser panel.
     */
    public CreateUserScreen() {
        super();

        selectUserLabel = new JLabel("Select User ");
        typePasswordLabel = new JLabel("Type Password ");
        reTypePasswordLabel = new JLabel("Retype Password ");

        typePasswordTextField = new JTextField();
        reTypePasswordTextField = new JTextField();

        createButton = new JButton( "Create" );

        add(selectUserLabel);
        add(cmbUserList);
        add(typePasswordLabel);
        add(typePasswordTextField);
        add(reTypePasswordLabel);
        add(reTypePasswordTextField);
        add(createButton);
    }

    /**
     * Sets the properties (i.e. the font, position, and dimensions) for the widgets of this screen.
     */
    @Override
    void setUp(){
        super.setUp();
        setSelectUserLabel();
        createUsersComboBox();
        setPasswordLabel();
        setPasswordTextField();
        setRetypePasswordLabel();
        setRetypePasswordTextField();
        setCreateButton();
    }

    /**
     * Set the properties for the create button.
     */
    private void setCreateButton() {
        createButton.addActionListener( e ->  setCreate());
        createButton.setFont(defaultFont);
        createButton.setBounds(50, 200, 195, 35);
    }

    /**
     * Set the properties for the retype password text field.
     */
    private void setRetypePasswordTextField() {
        reTypePasswordTextField.setFont(defaultFont);
        reTypePasswordTextField.setBounds(215, 140, 195, 35);
    }

    /**
     * Set the properties for the retype password label.
     */
    private void setRetypePasswordLabel() {
        reTypePasswordLabel.setFont(defaultFont);
        reTypePasswordLabel.setBounds(50, 140, 195, 35);
    }

    /**
     * Set the properties for the password text field.
     */
    private void setPasswordTextField() {
        typePasswordTextField.setFont(defaultFont);
        typePasswordTextField.setBounds(215, 80, 195, 35);
    }

    /**
     * Set the properties for the password label.
     */
    private void setPasswordLabel() {
        typePasswordLabel.setFont(defaultFont);
        typePasswordLabel.setBounds(50, 80, 195, 35);
    }

    /**
     * Set the properties for the combo box for types of users.
     */
    private void createUsersComboBox() {
        cmbUserList.setFont(defaultFont);
        cmbUserList.setBounds(215, 20, 195, 35);
        cmbUserList.setSelectedIndex(0);
    }

    /**
     * Set the properties for the select user label.
     */
    private void setSelectUserLabel() {
        selectUserLabel.setFont(defaultFont);
        selectUserLabel.setBounds(50, 20, 195, 35);
    }

    /**
     * Shows the appropriate user selected.
     */
    private User selectUser() {
        BankInformationStorage BIS = MainBankSystem.getBIS();
        String selectedItem = (String)cmbUserList.getSelectedItem();
        User user;
        if (selectedItem.equals("Bill Restocker")){
            user = MainBankSystem.getBankManager().createNewUser(typePasswordTextField.getText(), "BillRestocker");
        } else {
            user = MainBankSystem.getBankManager().createNewUser(typePasswordTextField.getText(), "Customer");
        }
        return user;
    }

    /**
     * Creates password for the new user.
     */
    private void setCreate() {
        String typePassword = typePasswordTextField.getText();
        String reTypePassword = reTypePasswordTextField.getText();

        if (typePassword.length() <6){
            JOptionPane.showMessageDialog(null, "Please make sure your password is at least 6 characters",
                    "Failed", JOptionPane.ERROR_MESSAGE);
        }

        else if (! typePassword.equals(reTypePassword)){
            JOptionPane.showMessageDialog(null, "Passwords do not match",
                    "Failed", JOptionPane.ERROR);
        }

        else if (typePassword.equals(reTypePassword)) {
            User user = selectUser();
            String content = "User has been successfully created. \nUserID: " + user.getUserID();
            JOptionPane.showMessageDialog(null, content, "Success", JOptionPane.INFORMATION_MESSAGE);
        }
        clear();
    }

    /**
     * Clears the combo box and text fields.
     */
    public void clear() {
        cmbUserList.setSelectedIndex(0);
        typePasswordTextField.setText("");
        reTypePasswordTextField.setText("");
    }
}
