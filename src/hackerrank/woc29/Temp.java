package hackerrank.woc29;

import java.util.*;
import java.lang.*;

class Temp {
    static boolean mp(long number, HashSet<Integer> pMap) {
        int temp;
        while (number != 0) {
            temp = (int) number % 10;
            if (!pMap.contains(temp)) {
                return false;
            }
            number = number / 10;
        }
        return true;
    }
    static String getNextNumber(String curr, HashMap<Character, Character> nMap) {
        StringBuilder sb = new StringBuilder();
        char c;
        int cLen = curr.length();
        if(cLen == 0)
            return "2";
        c = curr.charAt(cLen-1);
        if(c == '2' || c == '5'|| c == '3' ) {
            sb.append(nMap.get(c));
            sb.insert(0, curr.substring(0, cLen-1));
        } else if(c == '7') {
            sb.append('2');
            sb.insert(0, getNextNumber(curr.substring(0, cLen-1), nMap));
        }
        String res = sb.toString();
        return res;
    }
    static String getFirstNumber(String s, HashMap<Character, Character> pMap) {
        boolean isPromotionRequired = false;
        int n = s.length();
        char c;
        StringBuilder sb = new StringBuilder();
        for(int x = n-1; x >= 0; x--) {
            c = s.charAt(x);
            if(c == '8' || c == '9') {
                sb = updateStringBuilder(sb);
                sb.append('2');
                isPromotionRequired = true;
            } else if(c == '0' || c == '6' || c == '1' || c == '4') {
                sb = updateStringBuilder(sb);
                sb.append(pMap.get(c));
                isPromotionRequired = false;
            } else if(c == '2' || c == '7' || c == '3' || c == '5'){
                if(isPromotionRequired) {
                    sb = updateStringBuilder(sb);
                    sb.append(pMap.get(c));
                } else {
                    sb.append(c);
                }
                if(!(isPromotionRequired && c == '7')) {
                    isPromotionRequired = false;
                }
            }
        }
        if(isPromotionRequired)
            sb.append('2');
        String res = sb.reverse().toString();
        return res;
    }
    private static StringBuilder updateStringBuilder(StringBuilder sb) {
        int sbLen = sb.length();
        sb = new StringBuilder();
        for(int i = 0; i < sbLen; i++) {
            sb.append('2');
        }
        return sb;
    }
    public static void main(String[] args) throws java.lang.Exception {
        Scanner sc = new Scanner(System.in);
        long first = sc.nextLong();
        long last = sc.nextLong();
        HashMap<Character, Character> nMap = new HashMap<>();
        nMap.put('2', '3');
        nMap.put('3', '5');
        nMap.put('5', '7');
        HashSet<Integer> sMap = new HashSet<>();
        sMap.add(2);
        sMap.add(3);
        sMap.add(5);
        sMap.add(7);
        HashMap<Character, Character> pMap = new HashMap<>();
        pMap.put('0', '2');
        pMap.put('1', '2');
        pMap.put('2', '3');
        pMap.put('3', '5');
        pMap.put('4', '5');
        pMap.put('5', '7');
        pMap.put('6', '7');
        pMap.put('7', '7');
        pMap.put('8', '2');
        pMap.put('9', '2');
        int upperLimit = 31622781;
        boolean[] isPrimeNumber = new boolean[upperLimit];
        for (int i = 2; i < upperLimit; i++)
            isPrimeNumber[i] = true;
        for (int i = 0; i < upperLimit; i++) {
            if (isPrimeNumber[i])
                for (int j = 2; i * j < upperLimit; j++)
                    isPrimeNumber[i * j] = false;
        }
        long[] primeNumbers = new long[upperLimit];
        int j = 0;
        for (int i = 0; i < upperLimit; i++) {
            if (isPrimeNumber[i])
                primeNumbers[j++] = i;
        }
        String numberString = getFirstNumber(String.valueOf(first), pMap);
        long number = Long.parseLong(numberString);
        List<Long> megaprimesList = new ArrayList<>();
        long lNum = 0;
        if(first <= number) {
            while (last >= number){
                if(mp(number, sMap)) {
                    lNum = number;
                    megaprimesList.add(lNum);
                }
                numberString = getNextNumber(numberString, nMap);
                number = Long.parseLong(numberString);
            }
        }
        Iterator<Long> it = megaprimesList.iterator();
        long sqrtOfX;

        long r = 0; boolean flag = true;
        for(long x: megaprimesList) {
            flag = true;
            sqrtOfX = (long) Math.sqrt(x);
            for(int i = 0; primeNumbers[i] <= sqrtOfX; i++) {
                if(x % primeNumbers[i] == 0) {
                    flag = false;
                    break;
                }
            }
            if(flag == true)
                r++;
        }
        System.out.println(r);
    }
}
