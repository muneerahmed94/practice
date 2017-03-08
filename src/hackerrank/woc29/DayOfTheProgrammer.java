package hackerrank.woc29;

import java.util.Scanner;

/**
 * Created by Muneer on 20-02-2017.
 */
public class DayOfTheProgrammer {

    final static int[] LEAP_YEAR = new int[]{31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    final static int[] NON_LEAP_YEAR = new int[]{31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int yyyy = in.nextInt();
        int nthDay = 256;
        // your code goes here

        int dayOfMonth, monthOfYear;

        if (yyyy == 1918) {
            nthDay += 13;
        }

        int[] noOfDaysInMonth = isLeapYear(yyyy) ? LEAP_YEAR : NON_LEAP_YEAR;

        int sum = 0;
        monthOfYear = 0;

        while (sum + noOfDaysInMonth[monthOfYear] < nthDay) {
            sum += noOfDaysInMonth[monthOfYear++];
        }
        monthOfYear++;

        dayOfMonth = nthDay - sum;

        String res, dd, mm;

        dd = dayOfMonth < 10 ? "0" + dayOfMonth : "" + dayOfMonth;
        mm = monthOfYear < 10 ? "0" + monthOfYear : "" + monthOfYear;

        res = dd + "." + mm + "." + yyyy;
        System.out.println(res);
    }

    static boolean isLeapYear(int year) {
        if (isJulian(year)) {
            return year % 4 == 0;
        } else {
            return (year % 400 == 0) || (year % 4 == 0 && year % 100 != 0);
        }
    }

    private static boolean isJulian(int year) {
        return year <= 1917;
    }
}
