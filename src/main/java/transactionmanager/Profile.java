package transactionmanager;

/**
 * This class defines a Profile to be used for identification
 * on Accounts and all associated subclasses
 * @author Aveesh Patel, Patryk Dziedzic
 */
public class Profile implements Comparable<Profile>
{
    private String fname;
    private String lname;
    private Date dob;

    /**
     * Parameterized constructor to initialize a Profile instance
     * with a first name, last name, and date of birth
     * @param fname the first name for the Profile holder
     * @param lname the last name for the Profile holder
     * @param dob the date of birth of the Profile holder
     */
    public Profile(String fname, String lname, Date dob)
    {
        this.fname = fname;
        this.lname = lname;
        this.dob = dob;
    }

    /**
     * Compares two profiles and returns an integer value depicting the
     * result of the comparison between them on the basis of first name,
     * last name, and date of birth
     * @param profile the profile to be compared to
     * @return -1, 0, 1
     */
    @Override
    public int compareTo(Profile profile)
    {
        int lnameVal = lname.compareToIgnoreCase(profile.lname);
        int fnameVal = fname.compareToIgnoreCase(profile.fname);
        int dobVal = dob.compareTo(profile.dob);

        if (lnameVal > 0)
            return 1;
        else if (lnameVal < 0)
            return -1;
        else
        {
            if (fnameVal > 0)
                return 1;
            else if (fnameVal < 0)
                return -1;
            else {
                if (dobVal > 0)
                    return 1;
                else if (dobVal < 0)
                    return -1;
                else
                    return 0;
            }
        }
    }
}