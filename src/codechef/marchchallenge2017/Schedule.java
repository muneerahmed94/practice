package codechef.marchchallenge2017;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Muneer on 05-03-2017.
 */
public class Schedule {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();
        while (T-- > 0) {
            int n = sc.nextInt();
            int k = sc.nextInt();

            char[] ar = sc.next().toCharArray();

            int[] arr = new int[ar.length];
            for (int i = 0; i < arr.length; i++) {
                arr[i] = i;
            }

            if(n == 1) {
                System.out.println("1");
                continue;
            }

            int res = getMaxContinuousLength(ar);

            for(int r = 1; r <= k; r++) {
                List<List<Integer>> subsetsList = new ArrayList<>();
                printCombination(arr, n, r, subsetsList);

                int tRes = process(ar, subsetsList);
                res = tRes < res ? tRes : res;
            }


            System.out.println(res);
        }
    }

    private static int process(char[] ar, List<List<Integer>> subsetsList) {

        int minLen = Integer.MAX_VALUE;

        for(List<Integer> subset: subsetsList) {
            char[] tAr = Arrays.copyOf(ar, ar.length);
            for(Integer i: subset) {
                tAr[i] = tAr[i] == '1' ? '0' : '1';
            }
            int tMin = getMaxContinuousLength(tAr);
            if(tMin < minLen) {
                minLen = tMin;
            }
        }

        return minLen;
    }

    private static int getMaxContinuousLength(char[] ar) {
        int maxOnesLen = Integer.MIN_VALUE, maxZeroesLength = Integer.MIN_VALUE, tMaxLen;
        for(int i = 0; i < ar.length; i++) {
            int j = i+1;
            while(j < ar.length && ar[i] == ar[j]) {
                j++;
            }
            tMaxLen = j-i;
            if(ar[i] == '1' && tMaxLen > maxOnesLen) {
                maxOnesLen = tMaxLen;
            }
            if(ar[i] == '0' && tMaxLen > maxZeroesLength) {
                maxZeroesLength = tMaxLen;
            }
        }
        return maxOnesLen > maxZeroesLength ? maxOnesLen : maxZeroesLength;
    }

    static void combinationUtil(int arr[], int data[], int start, int end, int index, int r, List<List<Integer>> subsetsList) {
        if (index == r) {
            List<Integer> subset = new ArrayList<>();
            for (int j = 0; j < r; j++)
                subset.add(data[j]);
            subsetsList.add(subset);
            return;
        }

        for (int i = start; i <= end && end - i + 1 >= r - index; i++) {
            data[index] = arr[i];
            combinationUtil(arr, data, i + 1, end, index + 1, r, subsetsList);
        }
    }

    static void printCombination(int arr[], int n, int r, List<List<Integer>> subsetsList) {
        int data[] = new int[r];
        combinationUtil(arr, data, 0, n - 1, 0, r, subsetsList);
    }
}
