package hackerrank.hourrank17;

/**
 * Created by Muneer on 02-02-2017.
 */

import java.util.*;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int k = in.nextInt();
        int m = Integer.MIN_VALUE;
        int[] height = new int[n];
        for(int height_i=0; height_i < n; height_i++){
            height[height_i] = in.nextInt();
            if(m < height[height_i])
                m = height[height_i];
        }
        if(m - k > 0)
            System.out.println(m - k);

        else
            System.out.println(0);

    }
}
