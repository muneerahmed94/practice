package codechef.marchchallenge2017;

import java.util.Scanner;

/**
 * Created by Muneer on 05-03-2017.
 */
public class Bandmatr {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();
        while(T-- > 0) {
            int N = sc.nextInt();

            int noOfZeroes = 0;
            int noOfOnes = 0;
            int[][] ar = new int[N][N];
            for(int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    ar[i][j] = sc.nextInt();
                    if(ar[i][j] == 0) {
                        noOfZeroes++;
                    } else  {
                        noOfOnes++;
                    }
                }
            }

            if(noOfZeroes >= (N * N - N)) {
                System.out.println("0");
                continue;
            }

            if(noOfOnes == N) {
                System.out.println(N-1);
                continue;
            }

            for(int k = 1; k < N; k++) {
                int noOfZeroesRequired = getNoOfZeroesRequired(N, k);
                if(noOfZeroes >= noOfZeroesRequired) {
                    System.out.println(k);
                    break;
                }
            }
        }

        /*int N = 10;
        for(int k = 0; k <= N; k++) {
            System.out.println(getNoOfZeroesRequired(N, k));
        }*/
    }

    private static int getNoOfZeroesRequired(int N, int k) {
        int count = 0;
        for(int i = 1; i <= N; i++) {
            int leftCount = i - (k + 1);
            count += leftCount >= 1 ? leftCount: 0;

            int rightCount = i + (k + 1);
            rightCount = rightCount <= N ? N - rightCount + 1 : 0;
            count += rightCount;
        }
        return count;
    }
}
