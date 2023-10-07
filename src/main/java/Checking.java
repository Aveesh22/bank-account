public class Checking extends Account
{
    public static final double MONTHLY_INTEREST = 0.01;
    public static final double NO_MONTHLY_FEE = 0.00;
    public static final double MONTHLY_FEE = 12;

    @Override
    public double monthlyInterest()
    {
        return MONTHLY_INTEREST;
    }

    @Override
    public double monthlyFee()
    {
        if(getBalance() >= 1000)
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
