import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.utilities.FormatChecker;

/**
 * Does Pseudoscience program.
 *
 * @author Yihone
 *
 */
public final class ABCDGuesser1 {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private ABCDGuesser1() {
    }

    /**
     * Repeatedly asks the user for a positive double until the user enters one.
     * Returns the positive integer.
     *
     * @param in
     *            the input stream
     * @param out
     *            the output stream
     * @return a positive double entered by the user
     */
    private static double getPositiveDouble(SimpleReader in, SimpleWriter out) {

        boolean flag = false;
        String n = new String();

        while (!flag) {
            out.println("Give me a positive double ");
            n = in.nextLine();

            //Must be able to parse, double must greater than 0
            if (FormatChecker.canParseDouble(n) && Double.parseDouble(n) > 0) {
                flag = true;
            }
        }

        return Double.parseDouble(n);
    }

    /**
     * Repeatedly asks the user for a positive double not equal to 1 until the
     * user enters one. Returns the positive integer.
     *
     * @param in
     *            the input stream
     * @param out
     *            the output stream
     * @return a positive double entered by the user
     */
    private static double getPositiveDoubleNotOne(SimpleReader in,
            SimpleWriter out) {

        boolean flag = false;
        String n = new String();

        while (!flag) {
            out.println("Give me a positive double not equal to one");
            n = in.nextLine();

            //Must be able to parse, double must greater than 0, double must not equal 1
            if (FormatChecker.canParseDouble(n) && Double.parseDouble(n) > 0
                    && Double.parseDouble(n) != 1.0) {
                flag = true;
            }
        }

        return Double.parseDouble(n);
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {

        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();

        final double[] charms = new double[] { -5, -4, -3, -2, -1, -1.0 / 2.0,
                -1.0 / 3.0, -1.0 / 4.0, 0, 1.0 / 4.0, 1.0 / 3.0, 1.0 / 2.0, 1,
                2, 3, 4, 5 };

        double mu = getPositiveDouble(in, out);

        double w = getPositiveDoubleNotOne(in, out);
        double x = getPositiveDoubleNotOne(in, out);
        double y = getPositiveDoubleNotOne(in, out);
        double z = getPositiveDoubleNotOne(in, out);

        double a = 0, b = 0, c = 0, d = 0;

        double valueAtA = 0, valueAtB = 0, valueAtC = 0, valueAtD = 0;

        double bestEstimate = 0;

        int i = 0;
        int p = 0;
        int l = 0;
        int k = 0;

        /*
         * Computes what combination of charms gets closest to mew by going
         * through each combination of charms
         */

        while (i < charms.length) {
            a = (Math.pow(w, charms[i]));

            while (p < charms.length) {
                b = (Math.pow(x, charms[p]));

                while (l < charms.length) {
                    c = (Math.pow(y, charms[l]));

                    while (k < charms.length) {
                        d = (Math.pow(z, charms[k]));

                        double currentEstimate = a * b * c * d;

                        if (Math.abs(mu - currentEstimate) < Math
                                .abs(mu - bestEstimate)) {
                            bestEstimate = a * b * c * d;
                            valueAtA = charms[i];
                            valueAtB = charms[p];
                            valueAtC = charms[l];
                            valueAtD = charms[k];
                        }
                        k++;

                    }
                    k = 0;
                    l++;

                }
                l = 0;
                p++;

            }
            p = 0;
            i++;

        }
        i = 0;

        final int percentFactor = 100;
        double marginOfError = (Math.abs(bestEstimate - mu) / mu
                * percentFactor);
        out.println(marginOfError);

        out.println("A is " + valueAtA + " B is " + valueAtB + " C is "
                + valueAtC + " D is " + valueAtD);

        in.close();
        out.close();
    }

}
