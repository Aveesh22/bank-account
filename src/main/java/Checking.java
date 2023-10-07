public class Checking extends Account
{
    @Override
    public double monthlyInterest()
    {
        return 0.01;
    }

    @Override
    public double monthlyFee()
    {
        if(getBalance() >= 1000)
            return 0;
        else
            return 12;
    }

    @Override
    public int compareTo(Account account)
    {
        return 0;
    }
}
