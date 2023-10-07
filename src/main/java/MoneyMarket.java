public class MoneyMarket extends Savings
{
    private int withdrawal; //number of withdrawals

    public MoneyMarket(Profile holder, double balance)
    {
        if(balance < 2000)
            System.out.println("Minimum of $2000 to open a Money Market account."); //PLACEHOLDER UNSURE IF THIS IS RIGHT
        else
        {
            this.holder = holder;
            this.balance = balance;
            isLoyal = true;
        }
    }

    @Override
    public double monthlyFee()
    {
        int fee = 0;
        if(getBalance() < 2000)
            fee = 25;
        else
            fee = 0;

        if(withdrawal > 3)
            fee += 10;

        return fee;
    }

    @Override
    public double monthlyInterest()
    {
        recheckLoyalStatus();
        if(isLoyal)
            return .0475;
        else
            return .045;
    }

    public void recheckLoyalStatus()
    {
        if(getBalance() >= 2000)
            isLoyal = true;
        else
            isLoyal = false;
    }
}
