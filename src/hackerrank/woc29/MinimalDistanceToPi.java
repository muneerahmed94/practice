package hackerrank.woc29;

import java.util.Scanner;

/**
 * Created by Muneer on 25-02-2017.
 */
public class MinimalDistanceToPi {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        long min = in.nextLong();
        long max = in.nextLong();
        // your code goes here
        double pi = Math.PI;
        long n = 0, d = 0;
        double difference = Double.MAX_VALUE, tempDiff;

        for (long i = min; i <= max; i++) {
            double d1 = pi * i;
            long left = (new Double(d1)).longValue() - 10;
            long right = (new Double(d1)).longValue() + 10;
            for (long j = left; j <= right; j++) {
                tempDiff = Math.abs((double) j / i - pi);
                if (tempDiff < difference) {
                    difference = tempDiff;
                    n = j;
                    d = i;
                }
            }

        }
        System.out.println(n + "/" + d);
    }
}
