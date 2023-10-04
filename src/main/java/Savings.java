public class Savings extends Account
{
    protected boolean isLoyal;
    @Override
    public double monthlyInterest()
    {
        return 0;
    }

    @Override
    public double monthlyFee()
    {
        return 0;
    }

    @Override
    public int compareTo(Account account)
    {
        return 0;
    }
}
