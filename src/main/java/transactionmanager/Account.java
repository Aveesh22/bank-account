package transactionmanager;

public abstract class Account implements Comparable<Account>
{
    protected Profile holder;
    protected double balance;

    /**
     * Determines the monthly interest for the Account
     * @return the monthly interest
     */
    public abstract double monthlyInterest();

    /**
     * Determines the monthly fee for the Account
     * @return the monthly fee
     */
    public abstract double monthlyFee();

    /**
     * Get the balance of the account
     * @return the balance of the account
     */
    protected double getBalance()
    {
        return balance;
    }

    /**
     * Determines whether two accounts are the same (or equal)
     * @param acct the account to be compared
     * @return true or false depending on whether the two accounts are equal
     */
    @Override
    public boolean equals(Account acct)
    {
        /*if(holder.equals(acct.holder) && balance == acct.getBalance())
            return true;
        else
            return false;*/
    }


}
