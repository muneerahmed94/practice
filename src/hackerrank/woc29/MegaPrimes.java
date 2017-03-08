package hackerrank.woc29;

import java.util.*;
import java.lang.*;

class MegaPrimes {
    static final HashSet<Integer> primesSet = new HashSet<Integer>();

    static final HashMap<Character, String> promote = new HashMap<Character, String>();
    static final HashMap<Character, String> next = new HashMap<Character, String>();

    public static void main(String[] args) throws java.lang.Exception {

        Scanner sc = new Scanner(System.in);

        long first = sc.nextLong();
        long last = sc.nextLong();



        primesSet.add(2);
        primesSet.add(3);
        primesSet.add(5);
        primesSet.add(7);

        promote.put('0', "2");
        promote.put('1', "2");
        promote.put('2', "3");
        promote.put('3', "5");
        promote.put('4', "5");
        promote.put('5', "7");
        promote.put('6', "7");
        promote.put('7', "2");
        promote.put('8', "2");
        promote.put('9', "2");

        next.put('2', "3");
        next.put('3', "5");
        next.put('5', "7");

        String next = preprocess(String.valueOf(first));
        long num = Long.parseLong(next);

        ArrayList<Long> reqList = new ArrayList<>();
        long lastVal = 0;

        if(num >= first) {
            while (num <= last){

                if(isMegaprime(num)) {
                    lastVal = num;
                    reqList.add(lastVal);
                }
                next = next(next);
                num = Long.parseLong(next);
            }
        }

        int INF = (int) Math.sqrt(lastVal);

        int[] isPrime = new int[INF];
        for (int i = 2; i < INF; i++)
            isPrime[i] = 1;
        for (int i = 0; i < INF; i++) {
            if (isPrime[i] == 1)
                for (int j = 2; i * j < INF; j++)
                    isPrime[i * j] = 0;
        }

        long[] primes = new long[INF];
        int j = 0;

        int noOfPrimes = 0;
        for (int i = 0; i < INF; i++)
            if (isPrime[i] == 1) {
                primes[j++] = i;
                noOfPrimes++;
            }


        long sqX;
        int res = 0;
        boolean isXPrime;
        int listLen = reqList.size();

        for(int i = 0; i < listLen; i++) {
            long x = reqList.get(i);
            isXPrime = true;
            sqX = (long) Math.sqrt(x);
            for(int k = 0; k < noOfPrimes && primes[k] <= sqX; k++) {
                if(/*x != primes[k] && */x % primes[k] == 0) {
                    isXPrime = false;
                    break;
                }
            }
            if(isXPrime) {
                res++;
            }
        }

        System.out.println(res);
    }

    static String preprocess(String s) {
        boolean shouldPromote = false;
        int sLen = s.length();
        char ch;
        StringBuilder stringBuilder = new StringBuilder();

        for(int i = sLen-1; i >= 0; i--) {

            ch = s.charAt(i);
            if(ch == '8' || ch == '9') {
                int oldStringBuilderLength = stringBuilder.length();
                stringBuilder = new StringBuilder();
                for(int j = 0; j < oldStringBuilderLength; j++) {
                    stringBuilder.append('2');
                }
                stringBuilder.append('2');
                shouldPromote = true;
            } else if(ch == '0' || ch == '1' || ch == '4' || ch == '6') {
                int oldStringBuilderLength = stringBuilder.length();
                stringBuilder = new StringBuilder();
                for(int j = 0; j < oldStringBuilderLength; j++) {
                    stringBuilder.append('2');
                }
                stringBuilder.append(promote.get(ch));
                shouldPromote = false;
            } else if(ch == '2' || ch == '3' || ch == '5' || ch == '7'){
                if(shouldPromote) {
                    int oldStringBuilderLength = stringBuilder.length();
                    stringBuilder = new StringBuilder();
                    for(int j = 0; j < oldStringBuilderLength; j++) {
                        stringBuilder.append('2');
                    }
                    stringBuilder.append(promote.get(ch));
                } else {
                    stringBuilder.append(ch);
                }
                if(!(ch == '7' && shouldPromote)) {
                    shouldPromote = false;
                }
            }
        }

        if(shouldPromote) {
            stringBuilder.append('2');
        }

        return stringBuilder.reverse().toString();
    }

    static String next(String curr) {
        StringBuilder stringBuilder = new StringBuilder();
        char ch;
        int currLen = curr.length();

        if(currLen == 0) {
            return "2";
        }

        ch = curr.charAt(currLen-1);
        if(ch == '2' || ch == '3' || ch == '5') {
            stringBuilder.append(next.get(ch));
            stringBuilder.insert(0, curr.substring(0, currLen-1));
        } else if(ch == '7') {
            stringBuilder.append('2');
            stringBuilder.insert(0, next(curr.substring(0, currLen-1)));
        }

        return stringBuilder.toString();
    }

    static boolean isMegaprime(long n) {
        long t;
        while (n != 0) {
            t = n % 10;
            if (!primesSet.contains((int)t)) {
                return false;
            }
            n /= 10;
        }
        return true;
    }
}
