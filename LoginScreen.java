package gui;

import bankSystem.MainBankSystem;
import users.*;

import javax.swing.*;

public class LoginScreen extends HelpButtonScreen implements Clearable {

    private JButton loginButton;
    private JCheckBox employeeCheckBox;
    private JLabel welcomeLabel;
    private JLabel userIDLabel;
    private JLabel passwordLabel;
    private JLabel employeeLoginLabel;
    private JTextField userIDField;
    private JPasswordField passwordField;

    public LoginScreen () {
        super();
    }

    /**
     * Instantiates the JComponents in this class.
     */
    private void instantiateComponents() {
        welcomeLabel = new JLabel("Welcome to the CSC 207 Family Bank");
        userIDLabel = new JLabel("User ID:");
        userIDField = new JTextField();
        passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField();
        employeeCheckBox = new JCheckBox();
        employeeLoginLabel = new JLabel("Employee");
        loginButton = new JButton("Login");
    }

    /**
     * Initialize widgets on the screen
     */
    @Override
    public void setUp(){
        super.setUp();
        instantiateComponents();
        setComponentFont();
        setComponentActions();
        setComponentLocations();
        addAllComponents();
    }

    /**
     * sets the actionListeners of components in this screen
     */
    private void setComponentFont(){
        loginButton.setFont(defaultFont);
        welcomeLabel.setFont(defaultFont);
        userIDLabel.setFont(defaultFont);
        passwordLabel.setFont(defaultFont);
        employeeLoginLabel.setFont(defaultFont);
    }

    /**
     * sets the actionListeners of components in this screen
     */
    private void setComponentActions(){
        helpButton.addActionListener(e ->
                JOptionPane.showMessageDialog(null, getMessage("loginScreenHelp.txt"),
                        "Help", JOptionPane.INFORMATION_MESSAGE));
        loginButton.addActionListener(e -> loginButtonOnClick());
    }

    /**
     * sets the actions of the login button
     */
    private void loginButtonOnClick(){
        User user = MainBankSystem.getBIS().getUser(userIDField.getText());
        guiController.setUserForMaintenance(null);
        String password = new String(passwordField.getPassword());
        if (user == null){
            failedLogin();
        }else if (!employeeCheckBox.isSelected()){
            customerLogin(user, password);
        } else{
            employeeLogin(user, password);
        }
    }

    /**
     * validates employee login based on user inputs
     * @param user user corresponding to the input in the text field
     * @param password password as input into the password field
     */
    private void employeeLogin(User user, String password){
        if (user instanceof Employee && password.equals(user.getUserPassword())){
            guiController.setUser((Customer) user);
            if (user instanceof BankManager) {
                guiController.pushScreen(guiController.bankManagerOptionsScreen);
                guiController.setContentPane(guiController.bankManagerOptionsScreen);
            } else if (user instanceof BillRestocker) {
                guiController.depositRestockCashScreen.changeRestockDepositButtonText("Restock");
                guiController.pushScreen(guiController.depositRestockCashScreen);
                guiController.setContentPane(guiController.depositRestockCashScreen);
            }
        } else failedLogin();
        clear();
    }

    /**
     * validates customer login based on user inputs
     * @param user user corresponding to the input in the text field
     * @param password password as input into the password field
     */
    private void customerLogin(User user, String password){
        if (password.equals(user.getUserPassword())){
            guiController.setUser((Customer) user);
            guiController.pushScreen(guiController.customerOptionsScreen);
            guiController.setContentPane(guiController.customerOptionsScreen);
            clear();
        } else failedLogin();
    }

    /**
     * creates error message on failed login attempt
     */
    private void failedLogin(){
        JOptionPane.showMessageDialog(this,
                "Invalid userID or password, please try again.",
                "Failed",
                JOptionPane.ERROR_MESSAGE);
        clear();
    }

    /**
     * sets the locations of all components in this screen
     */
    private void setComponentLocations(){
        welcomeLabel.setBounds(40, 30, 370, 35);
        loginButton.setBounds(115, 180, 195, 35);
        userIDLabel.setBounds(50, 95, 80, 35);
        userIDField.setBounds(155, 95, 195, 35);
        passwordLabel.setBounds(50, 130, 195, 35);
        passwordField.setBounds(155, 130, 195, 35);
        employeeCheckBox.setBounds(115, 230, 20, 35);
        employeeLoginLabel.setBounds(140, 230, 195, 35);
    }

    /**
     * adds all components to this screen
     */
    private void addAllComponents(){
        add(loginButton);
        add(employeeCheckBox);
        add(welcomeLabel);
        add(userIDLabel);
        add(userIDField);
        add(passwordLabel);
        add(passwordField);
        add(employeeLoginLabel);
    }

    /**
     * Clears the checkbox and text fields.
     */
    public void clear() {
        employeeCheckBox.setSelected(false);
        passwordField.setText("");
        userIDField.setText("");
    }

}
