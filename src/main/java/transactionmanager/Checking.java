package transactionmanager;

/**
 * This class defines a Checking account with specific
 * parameters for monthly interest and fees and inherits
 * other properties and behaviors from the Account class
 * @author Aveesh Patel, Patryk Dziedzic
 */
public class Checking extends Account
{
    public static final double MONTHLY_INTEREST = 0.01;
    public static final double NO_MONTHLY_FEE = 0.00;
    public static final double MONTHLY_FEE = 12;

    /**
     * Default constructor to initialize a Checking account instance
     */
    public Checking()
    {

    }

    /**
     * Parameterized constructor to initialize a Checking account instance
     * @param holder Profile object for the account holder
     * @param balance current balance in the account
     */
    public Checking(Profile holder, double balance)
    {
        this.holder = holder;
        this.balance = balance;
    }

    /**
     * Overrides the monthlyInterest() method to return the monthly interest
     * of a Checking account
     * @return the monthly interest of a Checking account
     */
    @Override
    public double monthlyInterest()
    {
        return MONTHLY_INTEREST;
    }

    /**
     * Overrides the monthlyFee() method to return the monthly
     * fee of a Checking account
     * @return the monthly fee of a Checking account
     */
    @Override
    public double monthlyFee()
    {
        if(getBalance() >= 1000)
            return NO_MONTHLY_FEE;
        else
            return MONTHLY_FEE;
    }

    /**
     * Overrides the compareTo() method to compare
     * two accounts
     * @param account the account to be compared to
     * @return -1, 0, or 1
     */
    @Override
    public int compareTo(Account account)
    {
        return 0;
    }

    /**
     * Overridden method which returns the textual representation of a Checking account
     * @return the textual representation of a Checking account
     */
    @Override
    public String toString()
    {
        return super.toString() + "(C)";
    }
}
