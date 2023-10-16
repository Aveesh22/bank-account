package transactionmanager;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * This class tests the Date class isValid() method
 * with 5 invalid cases and 2 valid cases.
 * @author Aveesh Patel, Patryk Dziedzic
 */
public class DateTest {

    /**
     * Test the days in February (non-leap year).
     */
    @Test
    public void testDaysInFeb_NonLeap() {
        Date date = new Date("2/29/2023");
        assertFalse(date.isValid());
    }

    /**
     * Test the days in February (leap year).
     */
    @Test
    public void testDaysInFeb_Leap() {
        Date date = new Date("2/29/2024");
        assertTrue(date.isValid());
    }


    /**
     * Test months out of the range of months in a year.
     */
    @Test
    public void testMonth_OutOfRange() {
        Date date = new Date("13/29/2023");
        assertFalse(date.isValid());
        date = new Date("0/29/2023");
        assertFalse(date.isValid());
    }

    /**
     * Test days that are out of range for all months
     */
    @Test
    public void testDay_OutOfRange_31() {
        Date date = new Date("1/32/2023");
        assertFalse(date.isValid());
        date = new Date("1/0/2023");
        assertFalse(date.isValid());
    }

    /**
     * Test a day that is out of range for a 30-day month
     */
    @Test
    public void testDay_OutOfRange_30() {
        Date date = new Date("4/31/2023");
        assertFalse(date.isValid());
    }

    /**
     * Test a year that is <= 1900
     */
    @Test
    public void testYearNotInRange() {
        Date date = new Date("12/31/1900");
        assertFalse(date.isValid());
    }

    /**
     * Test a year that is > 1900
     */
    @Test
    public void testYearInRange() {
        Date date = new Date("1/1/1901");
        assertTrue(date.isValid());
    }
}