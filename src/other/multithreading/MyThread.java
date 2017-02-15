package other.multithreading;

import java.util.Date;

/**
 * Created by Muneer on 15-02-2017.
 */
public class MyThread implements Runnable {

    DateFormatHelper dateFormatHelper;

    public MyThread(DateFormatHelper dateFormatHelper) {
        this.dateFormatHelper = dateFormatHelper;
    }

    @Override
    public void run() {
        Date date = dateFormatHelper.getDateObjectFromDateString("02/15/2017");
        System.out.println(date);

        String dateString = dateFormatHelper.getDateStringFromDateObject(date);
        System.out.println(dateString);
    }
}
