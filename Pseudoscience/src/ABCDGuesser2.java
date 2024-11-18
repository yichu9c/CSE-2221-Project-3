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
public final class ABCDGuesser2 {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private ABCDGuesser2() {
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
     * Computes Margin of Error.
     *
     * @param mu
     *            mu
     * @param bestEstimate
     * @return a positive double entered by the user
     */

    private static double marginOfError(double mu, double bestEstimate) {
        final int percentFactor = 100;

        return Math.abs(bestEstimate - mu) / mu * percentFactor;
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

        double currentEstimate = 0;

        /*
         * Computes what combination of charms gets closest to mu by going
         * through each combination of charms
         */

        for (int i = 0; i < charms.length; i++) {
            a = Math.pow(w, charms[i]);

            for (int p = 0; p < charms.length; p++) {
                b = Math.pow(x, charms[p]);

                for (int l = 0; l < charms.length; l++) {
                    c = Math.pow(y, charms[l]);

                    for (int k = 0; k < charms.length; k++) {

                        d = Math.pow(z, charms[k]);

                        currentEstimate = a * b * c * d;

                        if (Math.abs(mu - currentEstimate) < Math
                                .abs(mu - bestEstimate)) {
                            bestEstimate = a * b * c * d;
                            valueAtA = charms[i];
                            valueAtB = charms[p];
                            valueAtC = charms[l];
                            valueAtD = charms[k];
                        }
                    }
                }
            }
        }

        out.println(
                "The margin of error is " + marginOfError(mu, bestEstimate));

        out.println("A is " + valueAtA + " B is " + valueAtB + " C is "
                + valueAtC + " D is " + valueAtD);

        in.close();
        out.close();
    }
}
