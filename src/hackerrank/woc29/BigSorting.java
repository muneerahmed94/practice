package hackerrank.woc29;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.*;

/**
 * Created by Muneer on 21-02-2017.
 */
public class BigSorting {
    public static void main(String[] args) {
        MyScanner in = new MyScanner();
        int n = in.nextInt();
        List<BigInteger> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(new BigInteger(in.next()));
        }
        // your code goes here
        Collections.sort(list);

        StringBuilder sb = new StringBuilder();
        for (BigInteger s : list) {
            sb.append(s);
            sb.append("\n");
        }

        System.out.println(sb);
    }

    //-----------PrintWriter for faster output---------------------------------
    public static PrintWriter out;

    //-----------MyScanner class for faster input----------
    public static class MyScanner {
        BufferedReader br;
        StringTokenizer st;

        public MyScanner() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }

        String nextLine() {
            String str = "";
            try {
                str = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }

    }
    //--------------------------------------------------------
}
