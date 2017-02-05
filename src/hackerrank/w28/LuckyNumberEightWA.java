package hackerrank.w28;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Muneer on 12-01-2017.
 */
public class LuckyNumberEightWA {

    final static long MOD = 1000000007L;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        ArrayList<ArrayList<Integer>> append = new ArrayList<>();
        ArrayList<Integer> al;

        int num;
        StringBuilder sb;

        for (int i = 0; i < 100; i++) {
            al = new ArrayList<>();
            sb = new StringBuilder(new Integer(i).toString());
            for (int j = 0; j < 10; j++) {
                sb.append(j);
                num = Integer.parseInt(sb.toString());
                if (num % 8 == 0)
                    al.add(j);
                sb.deleteCharAt(sb.length() - 1);
            }
            append.add(al);
        }

        /*for(int i = 0; i < 100; i++)
            System.out.println(i + ": " + append.get(i));*/

        int n = in.nextInt();
        String s = in.next();
        int sLen = s.length();

        int ai, aj, aiplus1;


        int row, col;

        int[][] m = new int[10][sLen];


        for (int idx = sLen - 2; idx >= 0; idx--) {

            row = Character.getNumericValue(s.charAt(idx + 1));

            for (int i = 0; i < 10; i++) {
                m[i][idx] = m[i][idx + 1];
            }

            m[row][idx] += 1;

        }


        /*for(int i = 0; i < 10; i++) {
            for(int j = 0; j < sLen; j++) {
                System.out.print(m[i][j] + " ");
            }
            System.out.println();
        }*/


        long pow = 1, ways;
        long res = 0;

        for (int i = 0; i < sLen; i++) {
            ai = s.charAt(i);
            if (ai % 8 == 0)
                res++;
        }

        // 2 digit
        for (int i = 0; i < sLen; i++) {
            num = Character.getNumericValue(s.charAt(i));
            al = append.get(num);

            ways = 0;
            for (int x : al) {
                ways += m[x][i];
            }

            res += ways;
        }

        pow = 1;

        //3 digit
        for (int i = 0; i < sLen; i++) {
            for (int j = i + 1; j < sLen; j++) {
                num = Integer.parseInt(s.charAt(i) + "" + s.charAt(j));
                al = append.get(num);

                ways = 0;
                for (int x : al) {
                    ways += m[x][j];
                }

                res += (pow) * ways;
            }
            pow *= 2;
        }

        res = res % MOD;
        System.out.println(res);

    }
}

