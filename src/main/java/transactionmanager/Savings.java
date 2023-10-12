package transactionmanager;

public class Savings extends Account
{
    public static final double IS_LOYAL_MONTHLY_INTEREST = 0.0425;
    public static final double NOT_LOYAL_MONTHLY_INTEREST = 0.04;
    public static final int NO_MONTHLY_FEE = 0;
    public static final int MONTHLY_FEE = 25;
    public static final int MIN_BALANCE_FOR_NO_FEE = 500;
    protected boolean isLoyal;

    /**
     * Default constructor
     */
    public Savings() {}

    /**
     * Parameterized constructor
     * @param holder Profile object for the account holder
     * @param balance current balance in the account
     * @param isLoyal true if the account holder is a loyal customer
     */
    public Savings(Profile holder, double balance, boolean isLoyal) {
        this.holder = holder;
        this.balance = balance;
        this.isLoyal = isLoyal;
    }

    @Override
    public double monthlyInterest()
    {
        if(isLoyal)
            return IS_LOYAL_MONTHLY_INTEREST;
        else
            return NOT_LOYAL_MONTHLY_INTEREST;
    }

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
