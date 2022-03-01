package au.edu.unimelb.ds.fatjar;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.TimeZone;

public class Application {
	public static void main(String[] args) {
	  Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Australia/Melbourne"));
	  DateFormat dateFormat = DateFormat.getDateTimeInstance();
	  System.out.println(String.format("The current local time is: %s", dateFormat.format(cal.getTime())));
  }
}