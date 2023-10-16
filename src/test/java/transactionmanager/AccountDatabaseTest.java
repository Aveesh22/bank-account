package transactionmanager;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * This class tests the AccountDatabase class close() method
 * with 1 false case and 1 true case.
 * @author Aveesh Patel, Patryk Dziedzic
 */
public class AccountDatabaseTest {

    /**
     * Try to close an account that is not in the database.
     */
    @Test
    public void testNotInDatabase() {
        AccountDatabase database = new AccountDatabase();
        Profile sampleHolder = new Profile("John", "Doe", new Date("2/19/2000"));
        Account sampleAcct = new Checking(sampleHolder, 599.99);
        database.open(sampleAcct);

        Profile holder = new Profile("April", "March", new Date("1/15/1987"));
        Account acct = new Checking(holder);
        boolean result = true;
        try {
            database.close(acct);
        }
        catch (NullPointerException e) {
            result = false;
        }
        assertFalse(result);
    }

    /**
     * Try to close an account that is in the database.
     */
    @Test
    public void testInDatabase() {
        AccountDatabase database = new AccountDatabase();
        Profile holder = new Profile("April", "March", new Date("1/15/1987"));
        Account acct = new Checking(holder, 300.00);
        database.open(acct);
        assertTrue(database.close(acct));
    }


}