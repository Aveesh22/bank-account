package transactionmanager;

import java.util.Scanner;

/**
 * This class handles user input and running commands.
 * @author Patryk Dziedzic, Aveesh Patel
 */
public class TransactionManager
{
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
                    break;
                case "S":
                    boolean isLoyal = Integer.parseInt(cmd[Command.CODE.getIndex()]) == 1;
                    acct = new Savings(holder, balance, isLoyal);
                    break;
                case "M":
                    acct = new MoneyMarket(holder, balance);
                    break;
                default:
                    System.out.println("Invalid account type.");
                    break;
            }

            boolean result = database.open(acct);
        }
        catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Missing data for opening an account.");
        }
        catch (NumberFormatException e) {
            System.out.println("Not a valid amount.");
        }
    }


    /**
     * Run the C command:
     * Close an existing account and remove it from the database.
     * @param cmd The current input line as a String array of tokens
     */
    private void cmdC(String[] cmd) {

    }


    /**
     * Run the D command:
     * Deposit money into an existing account.
     * @param cmd The current input line as a String array of tokens
     */
    private void cmdD(String[] cmd) {

    }


    /**
     * Run the W command:
     * Withdraw money from an existing account.
     * @param cmd The current input line as a String array of tokens
     */
    private void cmdW(String[] cmd) {

    }




    /**
     * Run the P, PI, or UB command:
     * Displays the event calendar on the console.
     * @param cmd The current input line as a String array of tokens
     */
    private void cmdP(String[] cmd) {
        if (database.getNumAcct() > 0) {
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
                else break;
            }
            currLine = scanner.nextLine();
        }

        System.out.println();
        System.out.println("Transaction Manager is terminated.");
    }
}