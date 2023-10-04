public class AccountDatabase
{
    private Account[] accounts; //list of various types of accounts
    private int numAcct; //number of accounts in the array

    public static final int GROWTH_RATE = 4;

    public static final int NOT_FOUND = -1;

    public void setNumAccts()
    {
        int i = 0;
        for(Account a : accounts)
        {
            if(a != null)
            {
                i++
            }
        }
        this.numAcct = i;
    }


    private int find(Account account) //search for an account in the array
    {
        for(int i = 0; i < accounts.length; i++)
        {
            if(accounts[i].equals(account))
                return i;
        }
        return -1;
    }

    private void grow() //increase the capacity by 4
    {
        Account[] accounts1 = new Account[accounts.length + GROWTH_RATE];
        for(int i = 0; i < accounts.length; i++)
        {
            accounts1[i] = accounts[i];
        }
        accounts = accounts1;
    }

    public boolean contains(Account account) //overload if necessary
    {
        for(Account a: accounts)
        {
            if(a.equals(account))
                return true;
        }
        return false;
    }

    public boolean open(Account account) //add a new account
    {

    }

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
            setNumAccts();
            return true;
        }
        else
            return false;
    }
    public boolean withdraw(Account account) //false if insufficient fund
    {

    }
    public void deposit(Account account)
    {
        

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