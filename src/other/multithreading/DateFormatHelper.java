package other.multithreading;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Muneer on 15-02-2017.
 */
public class DateFormatHelper {

    DateFormat dateFormat;

    public DateFormatHelper() {
        dateFormat = new SimpleDateFormat("MM/dd/yyyy");
    }

    public Date getDateObjectFromDateString(String dateString) {
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        Date date = null;
        try {
            date = dateFormat.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public String getDateStringFromDateObject(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        String dateString = dateFormat.format(date);
        return dateString;
    }
}
