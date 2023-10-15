package transactionmanager;

/**
 * This abstract class defines an Account on
 * the AccountDatabase
 * @author Aveesh Patel, Patryk Dziedzic
 */
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
     * Sets the balance of the account
     * @param balance the balance to add or subtract from the account
     */
    protected void setBalance(double balance)
    {
        this.balance = balance;
    }

    /**
     * Determines whether two accounts are the same (or equal)
     * @param acct the account to be compared
     * @return true or false depending on whether the two accounts are equal
     */
    public boolean equals(Account acct)
    {
        if(holder.equals(acct.holder))
            return true;
        else
            return false;
    }

    /**
     * Overridden method which returns the textual representation of an Account
     * @return the textual representation of an Account
     */
    @Override
    public String toString()
    {
        return holder.getFname() + " " + holder.getLname() + " " + holder.getDob();
    }


}
