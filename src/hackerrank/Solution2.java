package hackerrank;

/**
 * Created by Muneer on 23-12-2016.
 */
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution2 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int g = in.nextInt();
        for(int a0 = 0; a0 < g; a0++){
            int n = in.nextInt();
            int xor = 0;
            int[] p = new int[n];
            boolean allOnes = true;
            for(int p_i=0; p_i < n; p_i++){
                p[p_i] = in.nextInt();
                xor ^= p[p_i];
                if(p[p_i] != 1)
                    allOnes = false;
            }
            // your code goes here
            if(n == 1)
                System.out.println("W");
            else {
                if(n % 2 == 0) {
                    if(allOnes) {
                        System.out.println("W");
                    }
                    else if(xor == 0)
                        System.out.println("L");
                    else if(xor == n)
                        System.out.println("L");
                    else
                        System.out.println("W");
                } else {
                    if(allOnes)
                        System.out.println("L");
                    else if(xor == 0)
                        System.out.println("W");
                    else if(xor == n)
                        System.out.println("L");
                    else
                        System.out.println("L");
                }
            }
        }
    }
}

