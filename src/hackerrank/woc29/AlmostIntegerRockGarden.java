package hackerrank.woc29;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Muneer on 26-02-2017.
 */
public class AlmostIntegerRockGarden {
    final static double EPSILON = 0.000000000001;
    static long count = 0;
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int x = sc.nextInt();
        int y = sc.nextInt();
        Point first = new Point(x, y);

        List<Point> possiblePointsList = new ArrayList<>();
        Point p;

        double distance, difference, zero = 0.0;
        Point origin = new Point(0, 0);
        for (int i = 0; i <= 12; i++) {
            for (int j = 0; j <= 12; j++) {
                p = new Point(i, j);
                distance = getDistanceBetween(p, origin);
                difference = distance - ((int) distance);
                if (difference != zero) {
                    possiblePointsList.add(p);
                }
            }
        }

        int[] arr = new int[possiblePointsList.size()];
        for (int i = 0; i < possiblePointsList.size(); i++) {
            arr[i] = i;
        }
        printCombination(arr, arr.length, 11, possiblePointsList, first);

        System.out.println(possiblePointsList.size());
        System.out.println(possiblePointsList);
    }

    private static double getDistanceBetween(Point p1, Point p2) {
        double dist = (p1.x - p2.x) * (p1.x - p2.x) + (p1.y - p2.y) * (p1.y - p2.y);
        return Math.sqrt(dist);
    }

    static void printCombination(int arr[], int n, int r, List<Point> possiblePointsList, Point first) {
        int[] data = new int[r];

        combinationUtil(arr, data, 0, n - 1, 0, r, possiblePointsList, first);
    }

    static boolean combinationUtil(int arr[], int data[], int start, int end,
                                   int index, int r, List<Point> possiblePointsList, Point first) {
        if (index == r) {
            List<Integer> indexList = new ArrayList<>();
            for (int j = 0; j < r; j++)
                indexList.add(data[j]);
            return process(indexList, possiblePointsList, first);
        }

        for (int i = start; i <= end && end - i + 1 >= r - index; i++) {
            data[index] = arr[i];
            if(combinationUtil(arr, data, i + 1, end, index + 1, r, possiblePointsList, first))
                return true;
        }
        return false;
    }

    private static boolean process(List<Integer> indexList, List<Point> pointsList, Point first) {

        Point p;
        Point origin = new Point(0, 0);
        double sumOfDistance = getDistanceBetween(origin, first);
        for(int index: indexList) {
            p = pointsList.get(index);
            sumOfDistance += getDistanceBetween(origin, p);
        }
        double difference = sumOfDistance - ((int) sumOfDistance);
        if(difference <= EPSILON) {
            for(int index: indexList) {
                p = pointsList.get(index);
                int x = (int) p.x;
                int y = (int) p.y;
                System.out.println(x + " " + y);
            }
            System.out.println(count++ + " " + sumOfDistance);
            return true;
        }
        System.out.println(count++ + " " + sumOfDistance);
        return false;
    }
}
