package bankSystem;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.nio.channels.spi.AbstractSelectionKey;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import accounts.*;
import users.*;


public class BankInformationStorage implements Serializable {

    /**
     * Stores the total number of customers in an ArrayList
     */
    private List<Customer> customersList = new ArrayList<>();
    /**
     * Stores the total number of employees in an ArrayList
     */
    private List<Employee> employeesList = new ArrayList<>();
    /**
     * Stores the total number of atms in an ArrayList
     */
    private List<ATM> atmsList = new ArrayList<>();

    /**
     * Adds new user to either customersList or employeesList.
     * @param new_user new user that needs to be added.
     */
    public void addUser(User new_user) {
        if (new_user instanceof Employee) {
            employeesList.add((Employee)new_user);
        } else {
            customersList.add((Customer)new_user);
        }
    }

    /**
     * Checks whether the input id corresponds to any employee/customer.
     * If so, it returns the user from the corresponding list.
     *
     * @param userID the userID that needs to be compared with existing users in the list.
     * @return the user corresponding to the ID, or null.
     */
    public User getUser(String userID) {
        char id = userID.charAt(0);
        if (id == 'e') {
            for (Employee employee : employeesList) {
                if (employee.getUserID().equals(userID)) {
                    return employee;
                }
            }
        } else {
            for (Customer customer : customersList) {
                if (customer.getUserID().equals(userID)) {
                    return customer;
                }
            }
        }
        return null;
    }

    /**
     * Adds new atm to the atmsList.
     *
     * @param new_atm that needs to be added
     */
    public void addATM(ATM new_atm) {
        atmsList.add(new_atm);
    }

    /**
     * Get the list of atms.
     */
    public List<ATM> getAtmsList() {
        return atmsList;
    }


    /**
     * Checks if it is the first day of the month.
     *
     * @param calendar uses system clock to get current date
     * @return returns the bool true if it is the first day of the month if not, returns false
     */
    public boolean isFirstDayOfMonth(Calendar calendar) {
        return (calendar.get(Calendar.DAY_OF_MONTH) == 1);
    }

    /**
     * If it is the first day of the month, updates interest for all customers who
     * own a savings account in customersList.
     * @param calender takes an instance of Calendar.
     */
    public void updateMonthlyInterest(Calendar calender) {
        if (isFirstDayOfMonth(calender)) {
            for (int i = 0; i < customersList.size(); i++) {
                Customer customer = customersList.get(i);
                List<SavingsAccount> accounts = sortingUserAccounts(customer);
                customer.updateInterest(accounts);
            }
        }
    }

    /**
     * Helper method for updateMonthlyInterest, which gets the saving account of the customer.
     * @param user takes in a customer.
     */
    public List<SavingsAccount> sortingUserAccounts(User user) {
        // convert the hashMap to list
        List<SavingsAccount> result = new ArrayList<>();
        List<Account> userAccounts = new ArrayList<Account>(((Customer)user).getAllUserAccounts().values());
        for (int i = 0; i < userAccounts.size(); i++) {
            if (userAccounts.get(i) instanceof SavingsAccount) {
                result.add( (SavingsAccount)userAccounts.get(i) );
            }
        }
        return result;


    }
}