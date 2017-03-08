package hackerrank.woc29;

import java.util.Scanner;

/**
 * Created by Muneer on 22-02-2017.
 */
public class ACircleAndASquare {
    private static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) {
        double epsilon = 0.1;
        Scanner in = new Scanner(System.in);
        int w = in.nextInt();
        int h = in.nextInt();
        int circleX = in.nextInt();
        int circleY = in.nextInt();
        int r = in.nextInt();
        int x1 = in.nextInt();
        int y1 = in.nextInt();
        int x3 = in.nextInt();
        int y3 = in.nextInt();
        // your code goes here
        Point centreOfCircle = new Point(circleX, circleY);

        double dist;
        Point p;

        Point p1 = new Point(x1, y1);
        Point p3 = new Point(x3, y3);
        Point p2 = new Point((p1.x + p3.x + p3.y - p1.y) / 2.0, (p1.y + p3.y + p1.x - p3.x) / 2.0);
        Point p4 = new Point((p1.x + p3.x + p1.y - p3.y) / 2.0, (p1.y + p3.y + p3.x - p1.x) / 2.0);
        Point centreOfSquare = new Point((p1.x + p3.x) / 2.0, (p1.y + p3.y) / 2.0);

        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                p = new Point(x, y);
                dist = getDistanceBetween(centreOfCircle, p);
                if (dist <= r) {
                    System.out.print("#");
                } else {
                    if (areOnSameSide(p1, p2, centreOfSquare, p) && areOnSameSide(p2, p3, centreOfSquare, p) && areOnSameSide(p3, p4, centreOfSquare, p) && areOnSameSide(p4, p1, centreOfSquare, p)) {
                        System.out.print("#");
                    } else {
                        System.out.print(".");
                    }
                }
            }
            System.out.println();
        }
    }

    private static boolean areOnSameSide(Point P1, Point P2, Point x, Point y) {
        return isLeft(P1, P2, x) == isLeft(P1, P2, y);
    }

    private static double getSquareOfDistanceBetween(Point p1, Point p2) {
        double dist = (p1.x - p2.x) * (p1.x - p2.x) + (p1.y - p2.y) * (p1.y - p2.y);
        return dist;
    }

    private static double getDistanceBetween(Point p1, Point p2) {
        double dist = (p1.x - p2.x) * (p1.x - p2.x) + (p1.y - p2.y) * (p1.y - p2.y);
        return Math.sqrt(dist);
    }

    public static boolean isLeft(Point a, Point b, Point c){
        return ((b.x - a.x)*(c.y - a.y) - (b.y - a.y)*(c.x - a.x)) >= 0;
    }
}

class Point {
    double x;
    double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
