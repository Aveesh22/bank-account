package transactionmanager;

/**
 * This class defines a Savings account with specific parameters
 * for monthly interest and fees and inherits other
 * properties and behaviors from the Account class
 * @author Aveesh Patel, Patryk Dziedzic
 */
public class Savings extends Account
{
    public static final double IS_LOYAL_MONTHLY_INTEREST = 0.0425;
    public static final double NOT_LOYAL_MONTHLY_INTEREST = 0.04;
    public static final int NO_MONTHLY_FEE = 0;
    public static final int MONTHLY_FEE = 25;
    public static final int MIN_BALANCE_FOR_NO_FEE = 500;
    protected boolean isLoyal;

    /**
     * Default, no-parameter constructor which initializes a Savings account
     */
    public Savings()
    {

    }

    /**
     * Parameterized constructor which initializes a Savings account
     * @param holder Profile object for the account holder
     * @param balance current balance in the account
     * @param isLoyal true if the account holder is a loyal customer
     */
    public Savings(Profile holder, double balance, boolean isLoyal)
    {
        this.holder = holder;
        this.balance = balance;
        this.isLoyal = isLoyal;
    }

    /**
     * Determines the monthly interest for the Savings account
     * @return the monthly interest for the Savings account
     */
    @Override
    public double monthlyInterest()
    {
        if(isLoyal)
            return IS_LOYAL_MONTHLY_INTEREST;
        else
            return NOT_LOYAL_MONTHLY_INTEREST;
    }

    /**
     * Determines the monthly fee for the Savings account
     * @return the monthly fee for the account
     */
    @Override
    public double monthlyFee()
    {
        if(getBalance() >= MIN_BALANCE_FOR_NO_FEE)
            return NO_MONTHLY_FEE;
        else
            return MONTHLY_FEE;
    }

    @Override
    public int compareTo(Account account)
    {
        return 0;
    }
}
