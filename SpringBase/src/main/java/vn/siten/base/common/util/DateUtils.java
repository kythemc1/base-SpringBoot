package vn.siten.base.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtils {

	private DateUtils() {}

    private static final Logger logger = LoggerFactory.getLogger(DateUtils.class);

	/**
	 * Now : truncated hour
	 *
	 * @return Timestamp
	 */
	public static Timestamp before(long days) {
		return Timestamp.valueOf(LocalDateTime.now().minusDays(days)
				.truncatedTo(ChronoUnit.HOURS));
	}

	public static Date beginOfDay(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);

		return cal.getTime();
	}

	public static Date endOfDay(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MILLISECOND, 999);
		return cal.getTime();
	}
	
	

	public static int subtractDays(Date date1, Date date2) {
		GregorianCalendar gc1 = new GregorianCalendar();
		gc1.setTime(date1);
		GregorianCalendar gc2 = new GregorianCalendar();
		gc2.setTime(date2);

		int days1 = 0;
		int days2 = 0;
		int maxYear = Math.max(gc1.get(Calendar.YEAR), gc2.get(Calendar.YEAR));

		GregorianCalendar gctmp = (GregorianCalendar) gc1.clone();
		for (int f = gctmp.get(Calendar.YEAR); f < maxYear; f++) {
			days1 += gctmp.getActualMaximum(Calendar.DAY_OF_YEAR);
			gctmp.add(Calendar.YEAR, 1);
		}

		gctmp = (GregorianCalendar) gc2.clone();
		for (int f = gctmp.get(Calendar.YEAR); f < maxYear; f++) {
			days2 += gctmp.getActualMaximum(Calendar.DAY_OF_YEAR);
			gctmp.add(Calendar.YEAR, 1);
		}

		days1 += gc1.get(Calendar.DAY_OF_YEAR) - 1;
		days2 += gc2.get(Calendar.DAY_OF_YEAR) - 1;

		return (days1 - days2);
	}
	
	public static Date getDayBefore(Date ngay, int soNgay) {
		GregorianCalendar gcSau = new GregorianCalendar();
		gcSau.setTime(ngay);
		gcSau.add(Calendar.DAY_OF_YEAR, -soNgay);
		return gcSau.getTime();
	}
	
	public static Date getDayAfter(Date ngay, int soNgay) {
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(ngay);
		gc.add(Calendar.DAY_OF_YEAR, soNgay);
		return gc.getTime();
	}
	
	public static Date getMonthAfter(Date ngay, int soThang) {
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(ngay);
		gc.add(Calendar.MONTH, soThang);
		return gc.getTime();
	}

    public static Date addDay(Date ngay, int number) {
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(ngay);
        gc.add(Calendar.DATE, number);
        return  gc.getTime();
    }

    public static SimpleDateFormat getDateFormat() {
        return new SimpleDateFormat("dd/MM/yyyy hh:mm:ss a");
    }

    public static Date getDateFromString(String date) {
        try {
            return DateUtils.getDateFormat().parse(date);
        } catch (Exception e) {
            logger.error("", e);
        }

        return null;
    }


	
	
}
