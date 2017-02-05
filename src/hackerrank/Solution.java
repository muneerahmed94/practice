package hackerrank;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.HashMap;

/**
 * Created by Muneer on 22-12-2016.
 */
public class Solution {

    public static void main(String[] args) {

        int[] ar = new int[] {1,1,1};
        boolean[] isPassed = new boolean[ar.length];

        State stateObj = getState(ar, isPassed);
        HashMap<State, Boolean> stateMap = new HashMap<>();

        char state = 'W';
        boolean isWinningPos = isWinningNormal(ar);
        if(!isWinningPos)
            state = 'L';

//        char stateZero = 'W';
//        boolean isWinningPosZero = isWinningZero(ar, isPassed, 1);
//        if(!isWinningPosZero)
//            stateZero = 'L';



        char stateZeroDP = 'W';
        boolean isWinningPosDP = isWinningZeroDP(stateObj, stateMap);
        if(!isWinningPosDP)
            stateZeroDP = 'L';

        System.out.println();

        System.out.print("[");
        for(int i = 0; i < ar.length-1; i++) {
            System.out.print(ar[i] + ",");
        }
        System.out.print(ar[ar.length-1] + "] ");


       // print(a, N);
        int xor = findXor(ar);
        System.out.println(state + " " + /*stateZero +*/ " " + stateZeroDP + ", xor: " + xor);
    }

    private static void print(int[] a, int N) {
        int add = 0;
        for(int i = 0; i < N; i++) {
            a[i]++;
            for(int j = i+1; j < N; j++) {
                a[j]++;
                for(int k = j+1; k < N; k++) {
                    a[k]++;
                    for(int it = 0; it < N; it++) {
                        System.out.print(a[i] + " ");
                    }
                    System.out.println();
                }
            }
        }
    }

    private static boolean isWinningZeroDP(State stateObj, HashMap<State, Boolean> stateMap) {
        Boolean isWinningState = stateMap.get(stateObj);
        if(isWinningState != null)
            return isWinningState;

        State nextState;

        boolean allZeros = true;
        for(int x: stateObj.ar) {
            if(x != 0)
                allZeros = false;
        }
        if(allZeros) {
            //System.out.println("Exiting from fN: " + fN + " with " + false);
            //printStatus(ar, isPassed);
            stateMap.put(stateObj, false);
            return false;
        }

        for(int i = 0; i < stateObj.ar.length; i++) {

            if(!stateObj.isPassed[i]) {
                stateObj.isPassed[i] = true;
                nextState = getState(stateObj.ar, stateObj.isPassed);
                if(!isWinningZeroDP(nextState, stateMap)) {
                    stateObj.isPassed[i] = false;
                    //System.out.println("Exiting from fN: " + fN + " with " + true);
                    //printStatus(ar, isPassed);
                    stateMap.put(stateObj, true);
                    return true;
                }
                stateObj.isPassed[i] = false;
            }
        }

        int t;
        for(int i = 0; i < stateObj.ar.length; i++) {
            t = stateObj.ar[i];
            for(int j = 1; j <= t; j++) {
                stateObj.ar[i] -= j;
                nextState = getState(stateObj.ar, stateObj.isPassed);
                if(!isWinningZeroDP(nextState, stateMap)) {
                    stateObj.ar[i] += j;
                    //System.out.println("Exiting from fN: " + fN + " with " + true);
                    //printStatus(ar, isPassed);
                    stateMap.put(stateObj, true);
                    return true;
                }
                stateObj.ar[i] += j;
            }
        }

        stateMap.put(stateObj, false);
        return false;
    }





    private static boolean isWinningNormal(int[] ar) {
        int xor = findXor(ar);
        if(xor == 0)
            return false;
        else
            return true;
    }

    private static boolean isWinningZero(int[] ar, boolean[] isPassed, int fN) {

        //System.out.println("Entered fN: " + fN);
        //printStatus(ar, isPassed);
        
        boolean allZeros = true;
        for(int x: ar) {
            if(x != 0)
                allZeros = false;
        }
        if(allZeros) {
            //System.out.println("Exiting from fN: " + fN + " with " + false);
            //printStatus(ar, isPassed);
            return false;
        }


        for(int i = 0; i < ar.length; i++) {

            if(!isPassed[i]) {
                isPassed[i] = true;
                if(!isWinningZero(ar, isPassed, fN+1)) {
                    isPassed[i] = false;
                    //System.out.println("Exiting from fN: " + fN + " with " + true);
                    //printStatus(ar, isPassed);
                    return true;
                }
                isPassed[i] = false;
            }
        }

        int t;
        for(int i = 0; i < ar.length; i++) {
            t = ar[i];
            for(int j = 1; j <= t; j++) {
                ar[i] -= j;
                if(!isWinningZero(ar, isPassed, fN+1)) {
                    ar[i] += j;
                    //System.out.println("Exiting from fN: " + fN + " with " + true);
                    //printStatus(ar, isPassed);
                    return true;
                }
                ar[i] += j;
            }
        }

        //System.out.println("Exiting from fN: " + fN + " with " + false);
        //printStatus(ar, isPassed);
        return false;
    }

    static void printStatus(int[] ar, boolean[] isPassed) {
        //System.out.println("fN: " + fN);
        for(int x: ar)
            System.out.print(x + " ");
        System.out.println();
        for(boolean x: isPassed)
            if(x)
                System.out.print("t ");
            else
                System.out.print("f ");
        System.out.println();
    }

    private static int findXor(int[] ar) {
        int xor = 0;
        for(int x: ar)
            xor ^= x;
        return xor;
    }

    private static char getChar(boolean isWinningPos) {
        if(isWinningPos)
            return 'W';
        else
            return 'L';
    }

    private static State getState(int[] ar, boolean[] isPassed) {
        int[] a = new int[ar.length];
        boolean[] isPass = new boolean[isPassed.length];

        for(int i = 0; i < ar.length; i++) {
            a[i] = ar[i];
            isPass[i] = isPassed[i];
        }

        State state = new State(a, isPass);
        return state;
    }

}
