package br.com.test.rf.agendaTransf.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @copy of
 *       {@link http://www.java2s.com/Code/Java/Development-Class/CalendarUtil.htm}
 *
 */
public class CalendarUtil {

	public static final String DD_MM_YYYY = "dd/MM/yyyy";
	
	private static Calendar date;

	public static Calendar getCurrentTime() {
		if (date != null) {
			Calendar result = deepCopy(date);
			date = null;
			return result;
		} else {
			return Calendar.getInstance();
		}
	}

	public static Calendar getCurrentTruncDate() {
		return dateTrunc(getCurrentTime());
	}

	public static Integer getYear(Calendar calendar) {
		Integer year = calendar.get(Calendar.YEAR);
		return year;
	}

	public static Integer getMonth(Calendar calendar) {
		Integer month = calendar.get(Calendar.MONTH) + 1;
		return month;
	}

	public static Integer getDay(Calendar calendar) {
		Integer day = calendar.get(Calendar.DATE);
		return day;
	}

	public static Integer getDayOfWeekNumber(Calendar cal) {
		return cal.get(Calendar.DAY_OF_WEEK);
	}

	public static Integer get24Hour(Calendar calendar) {
		Integer hour = calendar.get(Calendar.HOUR_OF_DAY);
		return hour;
	}

	public static Integer getMinute(Calendar calendar) {
		Integer minute = calendar.get(Calendar.MINUTE);
		return minute;
	}

	public static Integer getSecond(Calendar calendar) {
		Integer second = calendar.get(Calendar.SECOND);
		return second;
	}

	public static Integer getMillisecond(Calendar calendar) {
		Integer millisecond = calendar.get(Calendar.MILLISECOND);
		return millisecond;
	}

	public static Calendar getCalendar(String yyyy, String mm, String dd) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, Integer.valueOf(yyyy));
		cal.set(Calendar.MONTH, Integer.valueOf(mm) - 1);
		cal.set(Calendar.DATE, Integer.valueOf(dd));
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal;
	}

	public static Calendar getCalendar(int yyyy, int mm, int dd) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, yyyy);
		cal.set(Calendar.MONTH, mm - 1);
		cal.set(Calendar.DATE, dd);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal;
	}

	public static Calendar getCalendar(String yyyy, String mm, String dd, String hh, String mi, String ss) {
		Calendar cal = getCalendar(yyyy, mm, dd);
		cal.set(Calendar.HOUR_OF_DAY, Integer.valueOf(hh));
		cal.set(Calendar.MINUTE, Integer.valueOf(mi));
		cal.set(Calendar.SECOND, Integer.valueOf(ss));
		return cal;
	}

	public static Calendar getCalendar(int yyyy, int mm, int dd, int hh, int mi, int ss) {
		Calendar cal = getCalendar(yyyy, mm, dd);
		cal.set(Calendar.HOUR_OF_DAY, hh);
		cal.set(Calendar.MINUTE, mi);
		cal.set(Calendar.SECOND, ss);
		return cal;
	}

	public static Calendar dateTrunc(Calendar calendar) {
		if (calendar == null) {
			return null;
		}
		Calendar date = (Calendar) calendar.clone();
		date.set(Calendar.HOUR_OF_DAY, 0);
		date.set(Calendar.MINUTE, 0);
		date.set(Calendar.SECOND, 0);
		date.set(Calendar.MILLISECOND, 0);
		return date;
	}

	public static boolean isFirstAfterSecond(Calendar first, Calendar second) {
		long firstValue = first.getTimeInMillis();
		long secondValue = second.getTimeInMillis();
		return (firstValue > secondValue) ? true : false;
	}

	public static Calendar deepCopy(Calendar src) {
		Calendar dest = Calendar.getInstance();
		dest.setTimeInMillis(src.getTimeInMillis());
		return dest;
	}

	public static Calendar addYears(Calendar src, int years) {
		Calendar dest = deepCopy(src);
		dest.add(Calendar.YEAR, years);
		return dest;
	}

	public static Calendar addMonths(Calendar src, int months) {
		Calendar dest = deepCopy(src);
		dest.add(Calendar.MONTH, months);
		return dest;
	}

	public static Calendar addDays(Calendar src, int days) {
		Calendar dest = deepCopy(src);
		dest.add(Calendar.DATE, days);
		return dest;
	}

	public static Calendar addHours(Calendar src, int hours) {
		Calendar dest = deepCopy(src);
		dest.add(Calendar.HOUR_OF_DAY, hours);
		return dest;
	}

	public static Calendar addMinutes(Calendar src, int minutes) {
		Calendar dest = deepCopy(src);
		dest.add(Calendar.MINUTE, minutes);
		return dest;
	}

	public static Calendar addSeconds(Calendar src, int seconds) {
		Calendar dest = deepCopy(src);
		dest.add(Calendar.SECOND, seconds);
		return dest;
	}

	public static Calendar getCalendar(long timeInMillis) {
		Calendar dest = Calendar.getInstance();
		dest.setTimeInMillis(timeInMillis);
		return dest;
	}
	
	public static Calendar getCalendar(String format, String date) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Date dt = sdf.parse(date);
		return getCalendar(dt.getTime());
	}
	
	public static Calendar getCalendarByDefaultFormat(String date) throws ParseException {
		return getCalendar(DD_MM_YYYY, date);
	}
	
	public static String toYYYYMMDDHHMISS(Calendar calendar) {
		return String.format("%04d", getYear(calendar)) + String.format("%02d", getMonth(calendar))
				+ String.format("%02d", getDay(calendar)) + String.format("%02d", get24Hour(calendar))
				+ String.format("%02d", getMinute(calendar)) + String.format("%02d", getSecond(calendar));
	}
	
	public static String toDDMMYYYY(Calendar calendar) {
		SimpleDateFormat sdf = new SimpleDateFormat(DD_MM_YYYY);
		return sdf.format(calendar.getTime());
	}

	public static int getDaysBetween(Calendar start, Calendar finish) {
		long aTime = start.getTime().getTime();
		long bTime = finish.getTime().getTime();

		long adjust = 60 * 60 * 1000;
		adjust = (bTime > aTime) ? adjust : -adjust;

		return (int) ((bTime - aTime + adjust) / (24 * 60 * 60 * 1000));
	}
}
