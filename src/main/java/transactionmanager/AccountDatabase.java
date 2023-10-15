package transactionmanager;

/**
 * This class defines an AccountDatabase
 * for organizing Accounts
 * @author Aveesh Patel, Patryk Dziedzic
 */
public class AccountDatabase
{
    private Account[] accounts; //list of various types of accounts

    private int numAcct; //number of accounts in the array

    public static final int GROWTH_RATE = 4;

    public static final int NOT_FOUND = -1;

    /**
     * No-parameter constructor which initializes the accounts variable
     * and sets the numAcct variable to the length of the accounts variable
     */
    public AccountDatabase()
    {
        accounts = new Account[GROWTH_RATE];
        setNumAcct();
    }

    /**
     * Getter for numAcct
     * @return the number of accounts in the list
     */
    public int getNumAcct() {
        return numAcct;
    }

    /**
     * Set the numAcct variable to be the
     * length of the accounts variable
     */
    public void setNumAcct()
    {
        int i = 0;
        for(Account a : accounts)
        {
            if(a != null)
            {
                i++;
            }
        }
        this.numAcct = i;
    }

    /**
     * Search for a specific account in the array
     * @param account the account to be searched for in the accounts array
     * @return an integer representing the index of the account in the array
     */
    private int find(Account account) //search for an account in the array
    {
        for(int i = 0; i < accounts.length; i++)
        {
            if(accounts[i].equals(account))
                return i;
        }
        return -1;
    }

    /**
     * Increases the capacity of the accounts array
     * by the GROWTH_RATE
     */
    private void grow() //increase the capacity by 4
    {
        Account[] accounts1 = new Account[accounts.length + GROWTH_RATE];
        for(int i = 0; i < accounts.length; i++)
        {
            accounts1[i] = accounts[i];
        }
        accounts = accounts1;
    }

    /**
     * Returns a boolean true or false checking if the account is
     * somewhere in the array
     * @param account the account to be checked in the array
     * @return a boolean true or false depicting if the account is in the accounts array
     */
    public boolean contains(Account account) //overload if necessary
    {
        if(numAcct == 0)
            return false;

        for(Account a: accounts)
        {
            if(a != null && a.equals(account))
                return true;
        }
        return false;
    }

    /**
     * Opens a new account by adding a new account in the accounts array
     * @param account the account to be added
     * @return a boolean true or false depicting whether or not the account was added successfully
     */
    public boolean open(Account account) //add a new account
    {
        boolean addSuccess = false;

        for (int i = accounts.length - 2; i >= 0; i--)
        {
            if(accounts[i] != null && accounts[i + 1] == null)
            {
                accounts[i + 1] = account;
                addSuccess = true;
            }

            if(i == 0 && accounts[i] == null)
            {
                accounts[i] = account;
                addSuccess = true;
            }
        }

        if (accounts[accounts.length - 1] != null)
            grow();

        setNumAcct();
        return addSuccess;
    }

    /**
     * Removes an account from the accounts array
     * @param account the account to be removed
     * @return a boolean true or false depicting whether or not the account was removed successfully
     */
    public boolean close(Account account) //remove the given account
    {
        int index = find(account);
        if (index != NOT_FOUND)
        {
            int i = index;
            while (accounts[i] != null && i != numAcct)
            {
                accounts[i] = accounts[i + 1];
                i++;
            }
            setNumAcct();
            return true;
        }
        else
            return false;
    }

    /**
     * Finds an account and withdraws a specified amount of money from it
     * @param account the account to withdraw money from with specified balance to withdraw
     * @return true or false dependent on if the withdrawal was successful
     */
    public boolean withdraw(Account account) //false if insufficient fund
    {
        int index = find(account);
        if(index != -1)
        {
            double acctBalance = accounts[index].getBalance();
            double withdrawBalance = account.getBalance();
            if(withdrawBalance > acctBalance)
                return false;
            accounts[index].setBalance(acctBalance - withdrawBalance);
            return true;
        }
        else
            return false;
    }

    /**
     * Finds an account and deposits a specified amount of money into it
     * @param account the account to deposit money into with the specified balance to deposit
     */
    public void deposit(Account account)
    {
        int index = find(account);
        if(index != -1)
        {
            double acctBalance = accounts[index].getBalance();
            double depositBalance = account.getBalance();
            accounts[index].setBalance(acctBalance + depositBalance);
        }
    }

    public void printSorted() //sort by account type and profile
    {

    }
    public void printFeesAndInterests() //calculate interests/fees
    {

    }
    public void printUpdatedBalances() //apply the interests/fees
    {

    }
}