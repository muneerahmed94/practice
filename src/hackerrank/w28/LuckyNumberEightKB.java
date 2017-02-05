package hackerrank.w28;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by Muneer on 12-01-2017.
 */
public class LuckyNumberEightKB {

    final static long MOD = 1000000007L;

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        ArrayList<ArrayList<Integer>> ap8 = new ArrayList<>();
        ArrayList<ArrayList<Integer>> ap4 = new ArrayList<>();
        ArrayList<Integer> al8;
        ArrayList<Integer> al4;

        int num;
        StringBuilder sb;

        for(int i = 0; i < 10; i++) {
            al8 = new ArrayList<>();
            al4 = new ArrayList<>();
            sb = new StringBuilder(new Integer(i).toString());
            for(int j = 0; j < 10; j++) {
                sb.append(j);
                num = Integer.parseInt(sb.toString());
                if(num % 8 == 0)
                    al8.add(j);
                if(num % 4 == 0)
                    al4.add(j);
                sb.deleteCharAt(sb.length()-1);
            }
            ap8.add(al8);
            ap4.add(al4);
        }

//        for(int i = 0; i < 10; i++) {
//            System.out.println(i + ": " + ap4.get(i));
//        }
//        System.out.println("------------------------------------------");
//        for(int i = 0; i < 10; i++) {
//            System.out.println(i + ": " + ap8.get(i));
//        }
//        System.out.println("------------------------------------------");

        int n = in.nextInt();
        String s = in.next();
        int sLen = s.length();

        long[] _1d8 = new long[sLen];

        // 1 digit
        for(int i = 0; i < sLen; i++) {
            num = Character.getNumericValue(s.charAt(i));
            if(num % 8 == 0)
                _1d8[i]++;
        }

        long[] count = new long[10];
        long[] _2d4 = new long[sLen];
        long[] _2d8 = new long[sLen];
        int ways;

        num = Character.getNumericValue(s.charAt(sLen-1));
        count[num]++;

        for(int i = sLen-2; i >= 0; i--) {

            num = Character.getNumericValue(s.charAt(i));

            ways = 0;
            al4 = ap4.get(num);
            for(int x : al4) {
                ways += count[x];
            }
            _2d4[i] = ways;

            ways = 0;
            al8 = ap8.get(num);
            for(int x : al8) {
                ways += count[x];
            }
            _2d8[i] = ways;

            count[num]++;
        }

//        System.out.println("_2d4: " + Arrays.toString(_2d4));
//        System.out.println("_2d8: " + Arrays.toString(_2d8));

        long[] evenSum = new long[sLen];
        long[] oddSum = new long[sLen];

        for(int i = sLen-3; i >= 0; i--) {
            evenSum[i] = evenSum[i+1] + _2d8[i+1];
            oddSum[i] = oddSum[i+1] + _2d4[i+1];
        }

        for(int i = sLen-1; i >= 0; i--) {
            oddSum[i] = oddSum[i] - evenSum[i];
        }

//        System.out.println("oddSum: " + Arrays.toString(oddSum));
//        System.out.println("evenSum: " + Arrays.toString(evenSum));

        long[] _3d8 = new long[sLen];
        for(int i = 0; i < sLen; i++) {
            num = Character.getNumericValue(s.charAt(i));
            if(num % 2 == 0)
                _3d8[i] = evenSum[i];
            else
                _3d8[i] = oddSum[i];
        }

//        System.out.println("_3d8: " + Arrays.toString(_3d8));



        long[] _nd8 = new long[sLen];

        long pow = 2;
        for(int i = 1; i < sLen; i++) {
            _nd8[i] = m(m(pow-1) * m(_3d8[i]));
            pow = m(m(pow) * m(2));
        }

//        System.out.println("_nd8: " + Arrays.toString(_nd8));

        long res1D = 0, res2D = 0, res3D = 0, resND = 0;

        for(int i = 0; i < sLen; i++) {
            res1D = res1D + _1d8[i];
            res2D = m(m(res2D) + m(_2d8[i]));
            res3D = m(m(res3D) + m(_3d8[i]));
            resND = m(m(resND) + m(_nd8[i]));
        }

        long resVal = 0;
        resVal = resVal + res1D;
        resVal = m(m(resVal) + m(res2D));
        resVal = m(m(resVal) + m(res3D));
        resVal = m(m(resVal) + m(resND));
//        long[] res = new long[sLen];
//        for(int i = 0; i < sLen; i++) {
//            res[i] = _1d8[i] + _2d8[i] + _3d8[i] + _nd8[i];
//            resVal += res[i];
//        }

//        System.out.println("res: " + Arrays.toString(res));
        System.out.println(resVal);

    }

    private static long m(long x) {
        return x % MOD;
    }
}
