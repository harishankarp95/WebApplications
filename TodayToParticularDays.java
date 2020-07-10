import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

// This program for if u want to exicute some thing today date to after 7th or any month, year,day
public class TodayToParticularDays {
	
		    private static final DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

		    public static void main(String[] args) {

		        Date currentDate = new Date();
		        System.out.println(dateFormat.format(currentDate));

		        // convert date to calendar
		        Calendar c = Calendar.getInstance();
		        c.setTime(currentDate);

		        // manipulate date
		       
		     // manipulate date
		        c.add(Calendar.YEAR, 1);
		        c.add(Calendar.MONTH, 1);
		        c.add(Calendar.DATE, 1); //same with c.add(Calendar.DAY_OF_MONTH, 1);
		        c.add(Calendar.HOUR, 1);
		        c.add(Calendar.MINUTE, 1);
		        c.add(Calendar.SECOND, 1);

		        // convert calendar to date
		        Date currentDatePlusOne = c.getTime();

		        System.out.println(dateFormat.format(currentDatePlusOne));

		    }
}
