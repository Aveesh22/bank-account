public class MoneyMarket extends Savings
{
    public static final int MIN_BALANCE_FOR_NO_FEE = 2000;
    public static final int MAX_WITHDRAWAL_FEE = 10;
    public static final int NUM_MAX_WITHDRAWALS = 3;
    public static final double IS_LOYAL_MONTHLY_INTEREST = 0.0475;
    public static final double NOT_LOYAL_MONTHLY_INTEREST = 0.045;

    private int withdrawal; //number of withdrawals


    public MoneyMarket(Profile holder, double balance)
    {
        if(balance < MIN_BALANCE_FOR_NO_FEE)
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
        if(getBalance() < MIN_BALANCE_FOR_NO_FEE)
            fee = MONTHLY_FEE;
        else
            fee = NO_MONTHLY_FEE;

        if(withdrawal > NUM_MAX_WITHDRAWALS)
            fee += MAX_WITHDRAWAL_FEE;

        return fee;
    }

    @Override
    public double monthlyInterest()
    {
        recheckLoyalStatus();
        if(isLoyal)
            return IS_LOYAL_MONTHLY_INTEREST;
        else
            return NOT_LOYAL_MONTHLY_INTEREST;
    }

    public void recheckLoyalStatus()
    {
        if(getBalance() >= MIN_BALANCE_FOR_NO_FEE)
            isLoyal = true;
        else
            isLoyal = false;
    }
}
