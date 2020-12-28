package coursework;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class TesterNew {

    public static void main(String[] args) {
        String message = "ssss";
        Date date = new Date();
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        String dateTimeString = "("+formatter.format(date)+") ";
        System.out.println(dateTimeString+message);

    }

}
