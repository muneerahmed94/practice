package hackerrank._101Hack45;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Created by Muneer on 17-01-2017.
 */
public class Solution {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] a = new int[n];
        for(int a_i=0; a_i < n; a_i++){
            a[a_i] = in.nextInt();
        }
        if(n == 1) {
            System.out.println(n-1);
            return;
        }

        Arrays.sort(a);
        // your code goes here
        int sl = a[1];
        for(long i = 1; i <= sl; i++) {
            long count = 0;
            for(int j = 0; j < n; j++) {
                if(a[j] % i == 0)
                    count++;
            }
            if(count == n-1) {
                System.out.println(i);
                break;
            }
        }
    }
}
