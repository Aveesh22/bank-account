package transactionmanager;

/**
 * This class defines a College Checking account with specific parameters
 * for monthly fees and inherits other properties and behaviors from
 * the Account and Checking classes
 * @author Aveesh Patel, Patryk Dziedzic
 */
public class CollegeChecking extends Checking
{
    private Campus campus; //campus code
    public static final double NO_MONTHLY_FEE = 0.00;

    /**
     * Parameterized constructor
     * @param holder Profile object for the account holder
     * @param balance current balance in the account
     */
    public CollegeChecking(Profile holder, double balance, Campus campus) {
        this.holder = holder;
        this.balance = balance;
        this.campus = campus;
    }

    /**
     * Returns the monthly fee for the College Checking account
     * @return a double depicting the monthly fee for the College Checking account
     */
    @Override
    public double monthlyFee()
    {
        return NO_MONTHLY_FEE;
    }
}
