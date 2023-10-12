package transactionmanager;

public class Profile implements Comparable<Profile>
{
    private String fname;
    private String lname;
    private Date dob;

    /** Parameterized constructor taking in first name, last name, and date of birth
     */
    public Profile(String fname, String lname, Date dob)
    {
        this.fname = fname;
        this.lname = lname;
        this.dob = dob;
    }

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