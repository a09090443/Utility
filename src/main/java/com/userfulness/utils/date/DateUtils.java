package com.userfulness.utils.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtils {

	public static enum Type {
		Year, Month, Week, Day, Hour, Minutes, Seconds;
	}

	/**
	 * <b>獲取當前時間</b><br>
	 * y 年 M 月 d 日 H 24小時制 h 12小時制 m 分 s 秒
	 *
	 * @param format
	 *            日期格式
	 * @return String
	 */
	public static String getCurrentDate(String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(new Date());
	}

	/**
	 * 獲取制定日期的格式化字符串
	 *
	 * @param date
	 *            Date 日期
	 * @param format
	 *            String 格式
	 * @return String
	 */
	public static String getFormatedDate(Date date, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}

	/**
	 * 判斷哪個日期在前 如果日期一在日期二之前，返回true,否則返回false
	 *
	 * @param date1
	 *            日期一
	 * @param date2
	 *            日期二
	 * @return boolean
	 */
	public static boolean isBefore(Date date1, Date date2) {
		Calendar c1 = Calendar.getInstance();
		c1.setTime(date1);

		Calendar c2 = Calendar.getInstance();
		c2.setTime(date2);

		if (c1.before(c2))
			return true;

		return false;
	}

	/**
	 * 將字符串轉換成日期
	 *
	 * @param date
	 *            String 日期字符串
	 * @return Date
	 * @throws ParseException
	 */
	public static Date parseDateFromString(String date) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.parse(date);
	}

	/**
	 * 獲取指定日期當月的最後一天
	 *
	 * @param date
	 * @return
	 */
	public static Date lastDayOfMonth(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, 1);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.add(Calendar.DAY_OF_MONTH, -1);
		return cal.getTime();
	}

	/**
	 * 獲取指定日期當月的第一天
	 *
	 * @param date
	 * @return
	 */
	public static Date firstDayOfMonth(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		return cal.getTime();
	}

	/**
	 * 是否是閏年
	 *
	 * @param year
	 *            年份
	 * @return boolean
	 */
	public static boolean isLeapYear(int year) {
		GregorianCalendar calendar = new GregorianCalendar();
		return calendar.isLeapYear(year);
	}

	/**
	 * 獲取指定日期之前或者之後多少天的日期
	 *
	 * @param day
	 *            指定的時間
	 * @param offset
	 *            日期偏移量，正數表示延後，負數表示天前
	 * @return Date
	 */
	public static Date getDateByOffset(Date day, int offset) {
		Calendar c = Calendar.getInstance();
		c.setTime(day);
		c.add(Calendar.DAY_OF_MONTH, offset);
		return c.getTime();
	}

	/**
	 * 獲取一天開始時間 如 2014-12-12 00:00:00
	 *
	 * @return
	 */
	public static Date getDayStart() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		return calendar.getTime();
	}

	/**
	 * 獲取一天結束時間 如 2014-12-12 23:59:59
	 *
	 * @return
	 */
	public static Date getDayEnd() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		return calendar.getTime();
	}

	/**
	 * 時間分段 比如：2014-12-12 10:00:00 ～ 2014-12-12 14:00:00 分成兩段就是 2014-12-12
	 * 10：00：00 ～ 2014-12-12 12：00：00 和2014-12-12 12：00：00 ～ 2014-12-12 14：00：00
	 *
	 * @param start
	 *            起始日期
	 * @param end
	 *            結束日期
	 * @param pieces
	 *            分成幾段
	 */
	public static Date[] getDatePieces(Date start, Date end, int pieces) {

		Long sl = start.getTime();
		Long el = end.getTime();

		Long diff = el - sl;

		Long segment = diff / pieces;

		Date[] dateArray = new Date[pieces + 1];

		for (int i = 1; i <= pieces + 1; i++) {
			dateArray[i - 1] = new Date(sl + (i - 1) * segment);
		}

		// 校正最後結束日期的誤差，可能會出現偏差，比如14:00:00 ,會變成13:59:59之類的
		dateArray[pieces] = end;

		return dateArray;
	}

	/**
	 * 獲取某個日期的當月第一天
	 *
	 * @return
	 */
	public static Date getFirstDayOfMonth(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		return cal.getTime();
	}

	/**
	 * 獲取某個日期的當月最後一天
	 *
	 * @return
	 */
	public static Date getLastDayOfMonth(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, 1);
		cal.set(Calendar.DAY_OF_MONTH, 0);
		return cal.getTime();
	}

	/**
	 * 獲取某個日期的當月第一天
	 *
	 * @return
	 */
	public static Date getFirstDayOfMonth(int year, int month) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month - 1);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		return cal.getTime();
	}

	/**
	 * 獲取某個日期的當月最後一天
	 *
	 * @return
	 */
	public static Date getLastDayOfMonth(int year, int month) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month);
		cal.set(Calendar.DAY_OF_MONTH, 0);
		return cal.getTime();
	}

	/**
	 * 獲取兩個日期的時間差，可以指定年，月，週，日，時，分，秒
	 *
	 * @param date1
	 *            第一個日期
	 * @param date2
	 *            第二個日期<font color="red">此日期必須在date1之後</font>
	 * @param type
	 *            DateUtils.Type.X的枚舉類型
	 * @return long值
	 * @throws Exception
	 */
	public static long getDiff(Date date1, Date date2, Type type) throws Exception {

		if (!isBefore(date1, date2))
			throw new Exception("第二個日期必須在第一個日期之後");

		long d = Math.abs(date1.getTime() - date2.getTime());
		switch (type) {
		case Year: {
			Calendar cal1 = Calendar.getInstance();
			Calendar cal2 = Calendar.getInstance();

			cal1.setTime(date1);
			int year1 = cal1.get(Calendar.YEAR);
			int month1 = cal1.get(Calendar.MONTH);
			int day1 = cal1.get(Calendar.DAY_OF_MONTH);
			int hour1 = cal1.get(Calendar.HOUR_OF_DAY);
			int minute1 = cal1.get(Calendar.MINUTE);
			int second1 = cal1.get(Calendar.SECOND);

			cal2.setTime(date2);
			int year2 = cal2.get(Calendar.YEAR);
			int month2 = cal2.get(Calendar.MONTH);
			int day2 = cal2.get(Calendar.DAY_OF_MONTH);
			int hour2 = cal2.get(Calendar.HOUR_OF_DAY);
			int minute2 = cal2.get(Calendar.MINUTE);
			int second2 = cal2.get(Calendar.SECOND);

			int yd = year2 - year1;

			if (month1 > month2) {
				yd -= 1;
			} else {
				if (day1 > day2) {
					yd -= 1;
				} else {
					if (hour1 > hour2) {
						yd -= 1;
					} else {
						if (minute1 > minute2) {
							yd -= 1;
						} else {
							if (second1 > second2) {
								yd -= 1;
							}
						}
					}
				}
			}
			return (long) yd;
		}
		case Month: {
			// 獲取年份差
			long year = getDiff(date1, date2, Type.Year);

			Calendar cal1 = Calendar.getInstance();
			Calendar cal2 = Calendar.getInstance();

			cal1.setTime(date1);
			int month1 = cal1.get(Calendar.MONTH);
			int day1 = cal1.get(Calendar.DAY_OF_MONTH);
			int hour1 = cal1.get(Calendar.HOUR_OF_DAY);
			int minute1 = cal1.get(Calendar.MINUTE);
			int second1 = cal1.get(Calendar.SECOND);

			cal2.setTime(date2);
			int month2 = cal2.get(Calendar.MONTH);
			int day2 = cal2.get(Calendar.DAY_OF_MONTH);
			int hour2 = cal2.get(Calendar.HOUR_OF_DAY);
			int minute2 = cal2.get(Calendar.MINUTE);
			int second2 = cal2.get(Calendar.SECOND);

			int md = (month2 + 12) - month1;

			if (day1 > day2) {
				md -= 1;
			} else {
				if (hour1 > hour2) {
					md -= 1;
				} else {
					if (minute1 > minute2) {
						md -= 1;
					} else {
						if (second1 > second2) {
							md -= 1;
						}
					}
				}
			}
			return (long) md + year * 12;
		}
		case Week: {
			return getDiff(date1, date2, Type.Day) / 7;
		}
		case Day: {
			long d1 = date1.getTime();
			long d2 = date2.getTime();
			return (int) ((d2 - d1) / (24 * 60 * 60 * 1000));
		}
		case Hour: {
			long d1 = date1.getTime();
			long d2 = date2.getTime();
			return (int) ((d2 - d1) / (60 * 60 * 1000));
		}
		case Minutes: {
			long d1 = date1.getTime();
			long d2 = date2.getTime();
			return (int) ((d2 - d1) / (60 * 1000));
		}
		case Seconds: {
			long d1 = date1.getTime();
			long d2 = date2.getTime();
			return (int) ((d2 - d1) / 1000);
		}
		default:
			throw new Exception("請指定要獲取的時間差的類型：年，月，天，週，時，分，秒");
		}
	}

	public static void main(String[] args) throws Exception {
		Date d = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		cal.add(Calendar.MONTH, 1);
		System.out.println(new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime()));
		System.out.println(getDiff(d, cal.getTime(), DateUtils.Type.Week));
	}
}