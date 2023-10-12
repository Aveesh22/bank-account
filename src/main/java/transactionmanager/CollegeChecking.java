package transactionmanager;

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

   @Override
    public double monthlyFee()
    {
        return NO_MONTHLY_FEE;
    }
}
