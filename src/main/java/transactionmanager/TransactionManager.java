package transactionmanager;

import java.lang.reflect.Array;
import java.util.Scanner;

/**
 * This class handles user input and running commands.
 * @author Patryk Dziedzic, Aveesh Patel
 */
public class TransactionManager
{
    private static final int MIN_BALANCE_FOR_NO_FEE_IN_MONEY_MARKET = 2000;
    private static final int OF_AGE = 16;
    private static final int OVER_COLLEGE_AGE = 24;
    private AccountDatabase database = new AccountDatabase();

    /**
     * Run the O command:
     * Opens an account with the desired account type.
     * @param cmd The current input line as a String array of tokens
     */
    private void cmdO(String[] cmd) {
        try {
            String fname = cmd[Command.FNAME.getIndex()];
            String lname = cmd[Command.LNAME.getIndex()];
            Date dob = new Date(cmd[Command.DOB.getIndex()]);
            Profile holder = new Profile(fname, lname, dob);
            double balance = Double.parseDouble(cmd[Command.MONEY.getIndex()]);
            Account acct;

            if (acctIsValid(cmd, dob, balance)) {
                acct = createAccount(cmd, holder, balance);
                if (acct != null) {
                    if (acct instanceof Checking) {
                        if (database.contains((Checking) acct, true))
                            System.out.println(acct + " is already in the database.");
                        else if (database.open(acct))
                            System.out.println(acct + " opened.");
                    }
                    else if (database.contains(acct))
                        System.out.println(acct + " is already in the database.");
                    else if (database.open(acct))
                            System.out.println(acct + " opened.");
                }
            }
        }
        catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Missing data for opening an account.");
        }
        catch (NumberFormatException e) {
            System.out.println("Not a valid amount.");
        }
    }

    /**
     * Check if the account is valid
     * @param cmd
     * @param dob
     * @param balance
     * @return
     */
    private boolean acctIsValid(String[] cmd, Date dob, double balance) {
        if(isFuture(dob))
            System.out.println("DOB invalid: " + dob + " cannot be today or a future day.");
        else if(!dob.isValid())
            System.out.println("DOB invalid: " + dob + " not a valid calendar date!");
        else if(!isOfAge(dob))
            System.out.println("DOB invalid: " + dob + " under 16.");
        else if(cmd[Command.ACCT.getIndex()].equals("CC") && !isOfAge_College(dob))
            System.out.println("DOB invalid: " + dob + " over 24.");
        else if(balance <= 0)
            System.out.println("Initial deposit cannot be 0 or negative.");
        else
            return true;
        return false;
    }

    /**
     * Create an Account object with the command line parameters
     * @param cmd the command line parameters
     * @param holder the Profile holder for the Account
     * @param balance the balance for the Account
     * @return the instantiated Account object
     */
    private Account createAccount(String[] cmd, Profile holder, double balance)
    {
        Account acct = null;
        switch (cmd[Command.ACCT.getIndex()]) {
            case "C":
                acct = new Checking(holder, balance);
                break;
            case "CC":
                int code = Integer.parseInt(cmd[Command.CODE.getIndex()]);
                for (Campus campus : Campus.values()) {
                    if (campus.getCode() == code) {
                        acct = new CollegeChecking(holder, balance, campus);
                    }
                }
                if (acct == null)
                    System.out.println("Invalid campus code.");
                break;
            case "S":
                boolean isLoyal = Integer.parseInt(cmd[Command.CODE.getIndex()]) == 1;
                acct = new Savings(holder, balance, isLoyal);
                break;
            case "MM":
                if (balance < MIN_BALANCE_FOR_NO_FEE_IN_MONEY_MARKET) {
                    System.out.println("Minimum of $2000 to open a Money Market account.");
                    break;
                }
                acct = new MoneyMarket(holder, balance);
                break;
            default:
                System.out.println("Invalid account type.");
                break;
        }
        return acct;
    }

    /**
     * Run the C command:
     * Close an existing account and remove it from the database.
     * @param cmd The current input line as a String array of tokens
     */
    private void cmdC(String[] cmd)
    {
        try
        {
            String fname = cmd[Command.FNAME.getIndex()];
            String lname = cmd[Command.LNAME.getIndex()];
            Date dob = new Date(cmd[Command.DOB.getIndex()]);
            Profile holder = new Profile(fname, lname, dob);
            Account acct;

            if(isFuture(dob))
                System.out.println("DOB invalid: " + dob + " cannot be today or a future day.");
            else if(!dob.isValid())
                System.out.println("DOB invalid: " + dob + " not a valid calendar date!");
            else
            {
                acct = createAccount_Closing(cmd, holder);
                if (acct != null) {
                    if (!database.contains(acct))
                        System.out.println(acct + " is not in the database.");
                    else {
                        if (database.close(acct))
                            System.out.println(acct + " has been closed.");
                    }
                }
            }
        }
        catch(ArrayIndexOutOfBoundsException e)
        {
            System.out.println("Missing data for closing an account.");
        }
    }

    /**
     * Create an Account object with a Profile and default values
     * @param cmd the command line parameters
     * @param holder the Profile holder for the Account
     * @return the instantiated Account object
     */
    private Account createAccount_Closing(String[] cmd, Profile holder)
    {
        Account acct = null;
        switch (cmd[Command.ACCT.getIndex()]) {
            case "C":
                acct = new Checking(holder);
                break;
            case "CC":
                acct = new CollegeChecking(holder);
                break;
            case "S":
                acct = new Savings(holder);
                break;
            case "MM":
                acct = new MoneyMarket(holder);
                break;
            default:
                System.out.println("Invalid account type.");
                break;
        }
        return acct;
    }



    /**
     * Check if a Profile holder's date of birth is today or in the future
     * @param dob the date of birth to check
     * @return true if the date of birth is today or a future date.
     */
    private boolean isFuture(Date dob) {
        Date today = Date.today();
        boolean result;
        if (dob.getYear() == today.getYear()) { //year == current year
            if (dob.getMonth() == today.getMonth()) {
                result = dob.getDay() > today.getDay();
            }
            else
                result = dob.getMonth() > today.getMonth();
        }
        else
            result = dob.getYear() > today.getYear(); //year > current year

        if(today.equals(dob))
            return true;

        return result;
    }

    /**
     * Check if a Profile holder is of age
     * @param dob the date of birth to check
     * @return true if the holder is of age
     */
    private boolean isOfAge(Date dob) {
        Date today = Date.today();
        int difference = today.getYear() - dob.getYear();
        if (difference == OF_AGE) {
            if (dob.getMonth() > today.getMonth())
                return false;
            else if (dob.getMonth() < today.getMonth())
                return true;
            else
                return dob.getDay() <= today.getDay();
        }
        else
            return difference >= OF_AGE;
    }

    /**
     * Check if a Profile holder is of college age
     * @param dob the date of birth to check
     * @return true if the holder is of college age
     */
    private boolean isOfAge_College(Date dob) {
        if (!isOfAge(dob)) return false;
        Date today = Date.today();
        int difference = today.getYear() - dob.getYear();
        if (difference == OVER_COLLEGE_AGE) {
            if (dob.getMonth() > today.getMonth())
                return true;
            else if (dob.getMonth() < today.getMonth())
                return false;
            else
                return dob.getDay() > today.getDay();
        }
        else
            return today.getYear() - dob.getYear() < OVER_COLLEGE_AGE;
    }

    /**
     * Run the D command:
     * Deposit money into an existing account.
     * @param cmd The current input line as a String array of tokens
     */
    private void cmdD(String[] cmd)
    {
        try
        {
            String fname = cmd[Command.FNAME.getIndex()];
            String lname = cmd[Command.LNAME.getIndex()];
            Date dob = new Date(cmd[Command.DOB.getIndex()]);
            Profile holder = new Profile(fname, lname, dob);
            double balance = Double.parseDouble(cmd[Command.MONEY.getIndex()]);
            Account acct;

            if(balance <= 0)
                System.out.println("Deposit - amount cannot be 0 or negative.");
            else
            {
                /*acct = createAccount(cmd);
                database.deposit(acct);
                System.out.println(acct + " Deposit - balance updated.");*/ //PROBLEM HERE AS WELL
            }

        }
        catch(NumberFormatException e)
        {
            System.out.println("Not a valid amount.");
        }
    }


    /**
     * Run the W command:
     * Withdraw money from an existing account.
     * @param cmd The current input line as a String array of tokens
     */
    private void cmdW(String[] cmd)
    {


    }




    /**
     * Run the P, PI, or UB command:
     * Displays the event calendar on the console.
     * @param cmd The current input line as a String array of tokens
     */
    private void cmdP(String[] cmd) {
        if (database.getNumAcct() > 0) {
            System.out.println();
            switch (cmd[Command.COMMAND.getIndex()]) {
                case "PI":
                    System.out.println("*list of accounts with fee and monthly interest");
                    database.printFeesAndInterests();
                    break;
                case "UB":
                    System.out.println("*list of accounts with fees and interests applied.");
                    database.printUpdatedBalances();
                    break;
                default:
                    System.out.println("*Accounts sorted by account type and profile.");
                    database.printSorted();
                    break;
            }
            System.out.println("*end of list.");
            System.out.println();
        }
        else
            System.out.println("Account Database is empty!");
    }

    /**
     * Read and parse user input commands.
     */
    public void run()
    {
        System.out.println("Transaction Manager is running.");
        System.out.println();

        Scanner scanner = new Scanner(System.in);
        String currLine = scanner.nextLine();

        while (true) {
            if (!currLine.isBlank()) {
                if (currLine.charAt(0) != 'Q') {
                    String[] commands = currLine.split("\\n+");
                    for (String command : commands) {
                        String[] cmd = command.split("\\s+");

                        //We run the command corresponding to the command given.
                        //If it is invalid, we return the invalid response.
                        switch (cmd[Command.COMMAND.getIndex()])
                        {
                            case "O" -> cmdO(cmd);
                            case "C" -> cmdC(cmd);
                            case "D" -> cmdD(cmd);
                            case "W" -> cmdW(cmd);
                            case "P", "PI", "UB" -> cmdP(cmd);
                            default -> System.out.println("Invalid command!");
                        };
                    }
                }
                else
                    break;
            }
            currLine = scanner.nextLine();
        }

        System.out.println();
        System.out.println("Transaction Manager is terminated.");
    }
}