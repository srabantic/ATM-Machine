package gui;

import users.*;
import accounts.*;
import bankSystem.*;
import javax.swing.*;
import java.awt.*;
import java.util.Stack;

/**
 * A class that keeps track of the JFrame, all the ATM panels, the order of the screens, the current user,
 * current account, and ATM.
 */
public class GuiController {

    /** A stack keeping track of the order of the ATM panels. */
    private Stack<JPanel> screens;

    /** The user that is currently logged in. */
    private Customer user;

    /** The user that the employee is operating on. */
    private Customer userForMaintenance;

    /** The current account. */
    private Account account;

    /** The ATM that is using this GUI. */
    private ATM atm;

    /** The frame displaying the panels. */
    JFrame frame;

    /** The ATM screens. */
    AccountFunctionsScreen accountFunctionsScreen;
    BackButtonScreen backButtonScreen;
    CashOrChequeScreen cashOrChequeScreen;
    CreateAccountScreen createAccountScreen;
    CreateUserScreen createUserScreen;
    CustomerOptionsScreen customerOptionsScreen;
    DepositRestockCashScreen depositRestockCashScreen;
    BankManagerOptionsScreen bankManagerOptionsScreen;
    LoginScreen loginScreen;
    ManageUserOptionsScreen manageUserOptionsScreen;
    SearchUserIDScreen searchUserIDScreen;
    SelectAccountScreen selectAccountScreen;
    SetPasswordScreen setPasswordScreen;
    TransactionScreen transactionScreen;
    WithdrawDepositScreen withdrawDepositScreen;

    /**
     * Constructs a new GuiStarter.
     *
     * @param atm The ATM using this graphical user interface.
     */
    public GuiController(ATM atm){
        this.screens = new Stack<>();
        this.atm = atm;
        this.frame = new JFrame("ATM");
        frame.setSize(450, 325);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * Starts the ATM with the login screen.
     */
    public void startATM() {
        instantiateScreens();
        setUpScreens();
        screens.push(loginScreen);
        setContentPane(loginScreen);
    }

    /**
     * Instantiate all screens.
     */
    private void instantiateScreens() {
        accountFunctionsScreen = new AccountFunctionsScreen();
        backButtonScreen = new BackButtonScreen();
        cashOrChequeScreen = new CashOrChequeScreen();
        createAccountScreen = new CreateAccountScreen();
        createUserScreen = new CreateUserScreen();
        customerOptionsScreen = new CustomerOptionsScreen();
        depositRestockCashScreen = new DepositRestockCashScreen();
        bankManagerOptionsScreen = new BankManagerOptionsScreen();
        loginScreen = new LoginScreen();
        manageUserOptionsScreen = new ManageUserOptionsScreen();
        searchUserIDScreen = new SearchUserIDScreen();
        selectAccountScreen = new SelectAccountScreen();
        setPasswordScreen = new SetPasswordScreen();
        transactionScreen = new TransactionScreen();
        withdrawDepositScreen = new WithdrawDepositScreen();
    }

    /**
     * Call setUp on all screens.
     */
    private void setUpScreens() {
        accountFunctionsScreen.setUp();
        backButtonScreen.setUp();
        cashOrChequeScreen.setUp();
        createAccountScreen.setUp();
        createUserScreen.setUp();
        customerOptionsScreen.setUp();
        depositRestockCashScreen.setUp();
        bankManagerOptionsScreen.setUp();
        loginScreen.setUp();
        manageUserOptionsScreen.setUp();
        searchUserIDScreen.setUp();
        selectAccountScreen.setUp();
        setPasswordScreen.setUp();
        transactionScreen.setUp();
        withdrawDepositScreen.setUp();
    }

    /**
     * Sets the content pane for the frame.
     *
     * @param panel The panel to set for the content pane (JPanel)
     */
    public void setContentPane(JPanel panel) {
        frame.setContentPane(panel);
        updateBackButtonText();
        if (frame.getContentPane() != loginScreen) {
            frame.add(backButtonScreen, BorderLayout.SOUTH);
        }
        frame.repaint();
        frame.revalidate();
        frame.setVisible(true);
    }

    /**
     * Changes the current text on the back button to "Back" or "Logout" depending on the current content pane.
     */
    private void updateBackButtonText() {
        Container currentPanel = frame.getContentPane();
        if (currentPanel == customerOptionsScreen || currentPanel == bankManagerOptionsScreen||
           (getUser() != null && getUser() instanceof BillRestocker && !(getUser() instanceof BankManager))) {
            backButtonScreen.setButtonText("Logout");
        } else {
            backButtonScreen.setButtonText("Back");
        }
    }

    /**
     * Returns the ATM that is using this GUI.
     *
     * @return ATM - The ATM using this GUI
     */
    public ATM getATM() { return this.atm; }

    /**
     * Returns the user that is currently logged in.
     *
     * @return User - The current user
     */
    public Customer getUser(){ return this.user; }

    /**
     * Sets the logged in user.
     *
     * @param user The user that is logged in (User)
     */
    public void setUser(Customer user) { this.user = user; }

    /**
     * Returns the user that an employee is operating on.
     *
     * @return the user that an employee is operating on.
     */
    public Customer getUserForMaintenance() { return this.userForMaintenance; }

    /**
     * Sets the user than an employee is operating on.
     * @param user the user that an employee is operating on.
     */
    public void setUserForMaintenance(Customer user) { this.userForMaintenance = user; }
    /**
     * Returns the current account.
     *
     * @return Account - The current account
     */
    public Account getAccount() { return this.account; }

    /**
     * Sets the current account.
     *
     * @param account The current account (Account)
     */
    public void setAccount(Account account) { this.account = account; }

    /**
     * Pushes the given JPanel onto the screens stack.
     *
     * @param screen The JPanel that will be added to the stack (JPanel)
     */
    public void pushScreen(JPanel screen){ screens.push(screen); }

    /**
     * Pops a screen off of the screens stack.
     */
    public JPanel popScreen(){ return screens.pop(); }

    /**
     * Returns the topmost screen in the screens stack without popping it off.
     *
     * @return JPanel - The topmost screen in the screens stack
     */
    public JPanel peekScreen() { return screens.peek(); }

    /**
     * Returns the second screen from the top of the screens stack without popping it off.
     *
     * @return JPanel -  The second screen from the top of the screens stack
     */
    public JPanel peekSecondScreen() {
        JPanel top = screens.pop();
        JPanel toReturn = screens.peek();
        screens.push(top);
        return toReturn;
    }
}