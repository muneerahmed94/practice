package spoj;

import java.util.*;

/**
 * Created by Muneer on 08-01-2017.
 */
public class HASHIT {

    final static long MOD = 101;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int t = sc.nextInt();
        for(int it = 0; it < t; it++) {
            TreeMap<Long, String> treeMap = new TreeMap<>();
            int n = sc.nextInt();
            for(int in = 0; in < n; in++) {

                String query = sc.next();
                String[] words = query.split(":");
                if(words.length == 1) {
                    words = new String[] {words[0], ""};
                }

                long hKey = h(words[1]) % 101;
                switch (words[0]) {
                    case "ADD":
                        boolean isAlreadyPresent = false;
                        if(treeMap.get(hKey) != null && treeMap.get(hKey).equals(words[1])) {
                            isAlreadyPresent = true;
                        }
                        if(!isAlreadyPresent) {
                            for(int j = 1; j <= 19; j++) {
                                long pos = (hKey + j*j + 23*j) % 101;
                                if(treeMap.get(pos) != null && treeMap.get(pos).equals(words[1])) {
                                    isAlreadyPresent = true;
                                    break;
                                }
                            }
                        }

                        if(isAlreadyPresent)
                            break;

                        if(treeMap.get(hKey) == null) {
                            treeMap.put(hKey, words[1]);
                        }
                        else if(!treeMap.get(hKey).equals(words[1])){
                           for(int j = 1; j <= 19; j++) {
                               long pos = (hKey + j*j + 23*j) % 101;
                               if(treeMap.get(pos) == null) {
                                   treeMap.put(pos, words[1]);
                                   break;
                               } else if(treeMap.get(pos).equals(words[1])) {
                                   break;
                               }
                           }
                       }
                        break;
                    case "DEL":
                        if(treeMap.get(hKey) != null && treeMap.get(hKey).equals(words[1])) {
                            treeMap.remove(hKey);
                        }
                        else {
                            for(int j = 1; j <= 19; j++) {
                                long pos = (hKey + j*j + 23*j) % 101;
                                if(treeMap.get(pos) != null && treeMap.get(pos).equals(words[1])) {
                                    treeMap.remove(pos);
                                    break;
                                }
                            }
                        }
                        break;
                }
            }
            System.out.println(treeMap.size());
            for(Map.Entry<Long, String> entry: treeMap.entrySet()) {
                System.out.println(entry.getKey() + ":" + entry.getValue());
            }
        }
    }

    private static long h(String key) {
        long res = 19;

        long tempRes = 0;
        for(int i = 0; i < key.length(); i++) {
            tempRes += ((int) key.charAt(i)) * (i+1);
        }

        res = res * tempRes;

        return res;
    }
}
