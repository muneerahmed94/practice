package hackerrank.w28;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Created by Muneer on 11-01-2017.
 */
public class LuckyNumberEight {

    final static long MOD = 1000000007L;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

//        System.out.println("done");

        ArrayList<ArrayList<Integer>> append = new ArrayList<>();
        ArrayList<Integer> al;

        int num;
        StringBuilder sb;

        for(int i = 0; i < 100; i++) {
            al = new ArrayList<>();
            sb = new StringBuilder(new Integer(i).toString());
            for(int j = 0; j < 10; j++) {
                sb.append(j);
                num = Integer.parseInt(sb.toString());
                if(num % 8 == 0)
                    al.add(j);
                sb.deleteCharAt(sb.length()-1);
            }
            append.add(al);
        }

        HashMap<Integer, ArrayList<String>> append2 = new HashMap<Integer, ArrayList<String>>();
        ArrayList<String> al2;
        String str1, str2, str3;

        for(int i = 0; i < 10; i++) {
            al2 = new ArrayList<String>();
            str1 = new Integer(i).toString();

            for(int j = 0; j < 100; j++) {
                str2 = new Integer(j).toString();
                if(str2.length() < 2)
                    str2 = "0" + str2;
                str3 = str1 + str2;
                if(Integer.parseInt(str3) % 8 == 0) {
                    al2.add(str2);
                }
            }
            append2.put(i, al2);
        }

//        for(int i = 0; i < 10; i++)
//            System.out.println(i + ": " + append2.get(i));

        /*for(int i = 0; i < 100; i++)
            System.out.println(i + ": " + append.get(i));*/

        int n = in.nextInt();
        String s = in.next();
        int sLen = s.length();

        int ai;


        int row;

        int[][] m = new int[100][sLen];


        for(int idx = sLen-2; idx >= 0; idx--) {

            row = Character.getNumericValue(s.charAt(idx+1));

            for(int i = 0; i < 10; i++) {
                m[i][idx] = m[i][idx+1];
            }

            m[row][idx] += 1;

        }

        int val, after;
        int[][][] map = new int[sLen][10][10];

        for(int i = sLen-3; i >= 0; i--) {

            // copy
            for(int j = 0; j < 10; j++) {
                for(int k = 0; k < 10; k++) {
                    map[i][j][k] = map[i+1][j][k];
                }
            }

            for(int j = 0; j < 10; j++) {
                int sum = 0;
                for(int k = 0; k < 10; k++) {
                    if(map[i+1][j][k] > 0) {
                        sum = map[i+1][j][k];
                        break;
                    }
                }
                num = Character.getNumericValue(s.charAt(i+1));
                map[i][j][num] = sum;
            }



            val = Character.getNumericValue(s.charAt(i+2));
            after = Character.getNumericValue(s.charAt(i+1));

            map[i][val][after]++;

//            System.out.println("***********************************************");
//            for(int j = 0; j < 10; j++) {
//                for(int k = 0; k < 10; k++) {
//                    System.out.print(map[i][j][k] + " ");
//                }
//                System.out.println();
//            }
//            System.out.println("***********************************************");
        }

//        for(int i = 0; i < sLen; i++) {
//            System.out.println("--------------------------------------------------------------------------------");
//            System.out.println("At " + s.charAt(i) + " of index: " + i);
//            for(int j = 0; j < 10; j++) {
//
//                for(int k = 0; k < 10; k++) {
//
//                    System.out.println("No of " + j + "s after " + k + ": " + map[i][j][k]);
//
//                }
//
//            }
//
//        }



        /*for(int i = 0; i < 10; i++) {
            for(int j = 0; j < sLen; j++) {
                System.out.print(m[i][j] + " ");
            }
            System.out.println();
        }*/


        long pow = 1, ways;
        long res = 0;

        // 1 digit
        for(int i = 0; i < sLen; i++) {
            ai = s.charAt(i);
            if(ai % 8 == 0)
                res++;
        }

        // 2 digit
        for(int i = 0; i < sLen; i++) {
            num = Character.getNumericValue(s.charAt(i));
            al = append.get(num);

            ways = 0;
            for(int x : al) {
                ways += m[x][i];
            }

            res = m(m(res) + m(ways));
        }

        pow = 1;

        //3 or more digit
        for(int i = 0; i < sLen; i++) {
            al2 = append2.get(Character.getNumericValue(s.charAt(i)));

            ways = 0;
            if(al2 != null) {
                for(String x : al2) {
                    val = Character.getNumericValue(x.charAt(1));
                    after = Character.getNumericValue(x.charAt(0));
                    ways += map[i][val][after];
                }

                res = m(m(res) + m(m(pow) * m(ways)));
            }

            pow = ((pow%MOD) * 2) % MOD;
        }

        res = res % MOD;
        System.out.println(res);

    }

    private static long m(long x) {
        return x % MOD;
    }
}
