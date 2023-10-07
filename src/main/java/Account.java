public abstract class Account implements Comparable<Account>
{
    protected Profile holder;
    protected double balance;
    public abstract double monthlyInterest();
    public abstract double monthlyFee();

    protected double getBalance()
    {
        return balance;
    }

    @Override
    public boolean equals(Account acct)
    {
        /*if(holder.equals(acct.holder) && balance == acct.getBalance())
            return true;
        else
            return false;*/
    }


}
