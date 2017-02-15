package other.multithreading;

/**
 * Created by Muneer on 15-02-2017.
 */
public class Test {
    public static void main(String[] args) {

        DateFormatHelper dateFormatHelper = new DateFormatHelper();

        for(int i = 0; i < 100; i++) {
            MyThread myThread = new MyThread(dateFormatHelper);
            Thread thread = new Thread(myThread);
            thread.start();
        }
    }
}
