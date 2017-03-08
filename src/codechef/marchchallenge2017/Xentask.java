package codechef.marchchallenge2017;

import java.util.Scanner;

/**
 * Created by Muneer on 05-03-2017.
 */
public class Xentask {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for(int it = 0; it < T; it++) {
            int N = sc.nextInt();

            int[] x = new int[N];
            for(int i = 0; i < N; i++) {
                x[i] = sc.nextInt();
            }

            int[] y = new int[N];
            for(int i = 0; i < N; i++) {
                y[i] = sc.nextInt();
            }

            long xFirstSum = 0, yFirstSum = 0;
            int[] xFirstSumAr = x, yFirstSumAr = y;
            for(int i = 0; i < N; i++) {
                xFirstSum += xFirstSumAr[i];
                xFirstSumAr = xFirstSumAr == x ? y : x;
                yFirstSum += yFirstSumAr[i];
                yFirstSumAr = yFirstSumAr == y ? x : y;
            }

            long res = xFirstSum < yFirstSum ? xFirstSum : yFirstSum;
            System.out.println(res);
        }
    }
}
