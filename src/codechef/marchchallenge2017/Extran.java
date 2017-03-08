package codechef.marchchallenge2017;

import java.util.HashSet;
import java.util.Scanner;

/**
 * Created by Muneer on 05-03-2017.
 */
public class Extran {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for(int it = 0; it < T; it++) {
            int N = sc.nextInt(), num;
            boolean done = false;
            HashSet<Integer> hashSet = new HashSet<>();
            for(int i = 0; i < N; i++) {
                num = sc.nextInt();
                if(hashSet.contains(num)) {
                    System.out.println(num);
                    done = true;
                }
                hashSet.add(num);
            }
            if(!done) {
                for(int x: hashSet) {
                    if(!(hashSet.contains(x-1) || hashSet.contains(x+1))) {
                        System.out.println(x);
                        break;
                    }
                }
            }
        }
    }
}
