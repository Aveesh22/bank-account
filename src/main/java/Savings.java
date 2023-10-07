public class Savings extends Account
{
    protected boolean isLoyal;
    @Override
    public double monthlyInterest()
    {
        if(isLoyal)
            return .0425;
        else
            return .04;
    }

    @Override
    public double monthlyFee()
    {
        if(getBalance() >= 500)
            return 0;
        else
            return 25;
    }

    @Override
    public int compareTo(Account account)
    {
        return 0;
    }
}
