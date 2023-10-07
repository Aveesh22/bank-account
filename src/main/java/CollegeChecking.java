public class CollegeChecking extends Checking
{
    private Campus campus; //campus code
    public static final double NO_MONTHLY_FEE = 0.00;

   @Override
    public double monthlyFee()
    {
        return NO_MONTHLY_FEE;
    }
}
