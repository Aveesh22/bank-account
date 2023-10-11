public class Profile implements Comparable<Profile>
{
    private String fname;
    private String lname;
    private Date dob;

    @Override
    public int compareTo(Profile profile)
    {
        int lnameVal = lname.compareToIgnoreCase(profile.lname);
        int fnameVal = fname.compareToIgnoreCase(profile.fname);
        int dobVal = dob.compareTo(profile.dob);


    }
}