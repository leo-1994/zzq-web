package com.leo.zzq.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class DateUtil {

    /**
     * 生成当前时间距离格林威治时间的毫秒数
     *
     * @return
     * @author jianming.jiangjm
     * @date Jul 21, 2012
     */
    public static long getSencondFrom1970() {
        Calendar cal = Calendar.getInstance();
        return cal.getTimeInMillis();
    }

    /**
     * 将指日期转为固定格式的字符串日期，日期类型为String
     *
     * @param date
     * @param dateFormat 需要转为的日期格式
     * @return
     * @throws Exception
     */
    public static String formatDateToString(Date date, DateformatEnum dateFormat) {
        if (null == date) {
            return "";
        }
        String strDate = null;
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat.getFormat());
        strDate = sdf.format(date);
        return strDate;
    }

    /**
     * 将指日期转为固定格式的字符串日期，日期类型为String
     *
     * @param date
     * @param dateFormat 需要转为的日期格式
     * @return
     * @throws Exception
     */
    public static long formatDateTolong(Date date, DateformatEnum dateFormat) {
        if (null == date) {
            return 0;
        }
        String strDate = null;
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat.getFormat());
        strDate = sdf.format(date);
        return Long.parseLong(strDate);
    }

    /**
     * @param strDate
     * @param dateFormat
     * @return
     * @throws Exception
     * @author jianming.jiangjm
     * @date Jul 21, 2012
     */
    public static Date formatStringToDate(String strDate, DateformatEnum dateFormat) {
        Date d = null;
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat.getFormat());
        try {
            d = sdf.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return d;
    }

    public static Date formatStringToDate(String strDate, DateformatEnum dateFormat, Locale loc) {
        Date d = null;
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat.getFormat(), loc);
        try {
            d = sdf.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return d;
    }

    /**
     * @param dateFormat
     * @return
     * @author jianming.jiangjm
     * @date Jul 21, 2012
     */
    public static String getCurrentDateStr(DateformatEnum dateFormat) {
        if (null == dateFormat) {
            return "";
        }
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat(dateFormat.getFormat());
        String mDateTime = formatter.format(cal.getTime());

        return mDateTime;
    }

    /**
     * @param dateFormat
     * @return
     * @throws Exception
     * @author jianming.jiangjm
     * @date Jul 21, 2012
     */
    public static Date getCurrentDate(DateformatEnum dateFormat) {
        return formatStringToDate(getCurrentDateStr(dateFormat), dateFormat);
    }

    public static Date getCurrentDate() {
        return new Date();
    }

    /**
     * @param date
     * @param second
     * @return
     * @author jianming.jiangjm
     * @date Oct 4, 2012
     */
    public static Date dateBeforeSecond(Date date, long second) {
        long afterTime = (date.getTime() / 1000) - second;
        date.setTime(afterTime * 1000);
        return date;
    }

    /**
     * @param second
     * @return
     * @author jianming.jiangjm
     * @date Oct 4, 2012
     */
    public static Date currentDateBeforeSecond(long second) {
        Date date = getCurrentDate();
        long afterTime = (date.getTime() / 1000) - second;
        date.setTime(afterTime * 1000);
        return date;
    }

    public static Date dateAfterSecond(Date date, long second) {
        long afterTime = (date.getTime() / 1000) + second;
        date.setTime(afterTime * 1000);
        return date;
    }

    public static Date currentDateAfterSecond(long second) {
        Date date = getCurrentDate();
        long afterTime = (date.getTime() / 1000) + second;
        date.setTime(afterTime * 1000);
        return date;
    }

    /**
     * 根据日期取得对应周周一日期
     *
     * @param date
     * @return
     * @author jianming.jiangjm
     * @date Oct 17, 2012
     */
    public static Date getMondayOfWeek(Date date) {
        Calendar monday = Calendar.getInstance();
        monday.setTime(date);
        monday.setFirstDayOfWeek(Calendar.MONDAY);
        monday.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        return monday.getTime();
    }

    public static String getMondayOfWeek(Date date, DateformatEnum dateformat) {
        Date monday = getMondayOfWeek(date);
        SimpleDateFormat format = new SimpleDateFormat(dateformat.getFormat());
        return format.format(monday);
    }

    /**
     * 根据日期取得对应周周日日期
     *
     * @param date
     * @return
     * @author jianming.jiangjm
     * @date Oct 17, 2012
     */
    public static Date getSundayOfWeek(Date date) {
        Calendar sunday = Calendar.getInstance();
        sunday.setTime(date);
        sunday.setFirstDayOfWeek(Calendar.MONDAY);
        sunday.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        return sunday.getTime();
    }

    public static String getSundayOfWeek(Date date, DateformatEnum dateformat) {
        Date sunday = getSundayOfWeek(date);
        SimpleDateFormat format = new SimpleDateFormat(dateformat.getFormat());
        return format.format(sunday);
    }

    /****
     * 根据日期取得对应周周一日期,yyyy-mm-dd 00:00:00
     *
     * @auther JZR
     * @version 2014年12月27日
     */
    public static Date getMondayOfWeekFormat(Date date) {
        Calendar monday = Calendar.getInstance();
        monday.setTime(date);
        monday.setFirstDayOfWeek(Calendar.MONDAY);
        monday.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        monday.set(Calendar.HOUR_OF_DAY, 0);
        monday.set(Calendar.MINUTE, 0);
        monday.set(Calendar.SECOND, 0);
        monday.set(Calendar.MILLISECOND, 0);
        return monday.getTime();
    }

    /**
     * 根据日期取得对应周周日日期,yyyy-mm-dd 23:59:59
     *
     * @auther JZR
     * @version 2014年12月27日
     */
    public static Date getSundayOfWeekFormat(Date date) {
        Calendar sunday = Calendar.getInstance();
        sunday.setTime(date);
        sunday.setFirstDayOfWeek(Calendar.MONDAY);
        sunday.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        sunday.set(Calendar.HOUR_OF_DAY, 23);
        sunday.set(Calendar.MINUTE, 59);
        sunday.set(Calendar.SECOND, 59);
        sunday.set(Calendar.MILLISECOND, 0);
        return sunday.getTime();
    }

    /**
     * 转换为最后一个毫秒的date
     *
     * @param date
     * @return
     */
    public static Date toLastMillisecond(Date date) {
        Calendar sunday = Calendar.getInstance();
        sunday.setTime(date);
        sunday.set(Calendar.HOUR_OF_DAY, 23);
        sunday.set(Calendar.MINUTE, 59);
        sunday.set(Calendar.SECOND, 59);
        sunday.set(Calendar.MILLISECOND, 999);
        return sunday.getTime();
    }

    /**
     * 根据得到的日期，取得下周的一个日期
     *
     * @auther JZR
     * @version 2014年12月27日
     */
    public static Date getNextWeek(Date date) {
        if (date == null) return date;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_YEAR, 7);
        return calendar.getTime();
    }

    /**
     * 根据得到的日期，取得上周的一个日期
     *
     * @auther leo
     * @version 2014年12月27日
     */
    public static Date getLastWeek(Date date) {
        if (date == null) return date;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_YEAR, -7);
        return calendar.getTime();
    }

    /**
     * 根据得到的日期，取得下月的一个日期
     *
     * @auther JZR
     * @version 2014年12月27日
     */
    public static Date getNextMonth(Date date) {
        if (date == null) return date;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, 1);
        return calendar.getTime();
    }

    /**
     * 取得月第一天 yyyy-mm-dd 00:00:00
     *
     * @auther JZR
     * @version 2014年12月27日
     */
    public static Date getFirstDateOfMonthFormat(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.DAY_OF_MONTH, c.getActualMinimum(Calendar.DAY_OF_MONTH));
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return c.getTime();
    }

    /**
     * 取得月最后一天 yyyy-mm-dd 23:59:59
     *
     * @auther JZR
     * @version 2014年12月27日
     */
    public static Date getLastDateOfMonthFormat(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
        c.set(Calendar.HOUR_OF_DAY, 23);
        c.set(Calendar.MINUTE, 59);
        c.set(Calendar.SECOND, 59);
        c.set(Calendar.MILLISECOND, 0);
        return c.getTime();
    }

    /**
     * 取得月第一天
     *
     * @param date
     * @return
     * @author jianming.jiangjm
     * @date Oct 17, 2012
     */
    public static Date getFirstDateOfMonth(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.DAY_OF_MONTH, c.getActualMinimum(Calendar.DAY_OF_MONTH));
        return c.getTime();
    }

    public static String getFirstDateOfMonth(Date date, DateformatEnum dateformat) {
        Date c = getFirstDateOfMonth(date);
        SimpleDateFormat format = new SimpleDateFormat(dateformat.getFormat());
        return format.format(c);
    }

    /**
     * 取得月最后一天
     *
     * @param date
     * @return
     * @author jianming.jiangjm
     * @date Oct 17, 2012
     */
    public static Date getLastDateOfMonth(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
        return c.getTime();
    }

    public static String getLastDateOfMonth(Date date, DateformatEnum dateformat) {
        Date c = getLastDateOfMonth(date);
        SimpleDateFormat format = new SimpleDateFormat(dateformat.getFormat());
        return format.format(c);
    }

    /**
     * 取得季度第一天
     *
     * @param date
     * @return
     */
    public static Date getFirstDateOfSeason(Date date) {
        return getFirstDateOfMonth(getSeasonDate(date)[0]);
    }

    public static String getFirstDateOfSeason(Date date, DateformatEnum dateformat) {
        return getFirstDateOfMonth(getSeasonDate(date)[0], dateformat);
    }

    /**
     * 取得季度最后一天
     *
     * @param date
     * @return
     */
    public static Date getLastDateOfSeason(Date date) {
        return getLastDateOfMonth(getSeasonDate(date)[2]);
    }

    public static String getLastDateOfSeason(Date date, DateformatEnum dateformat) {
        return getLastDateOfMonth(getSeasonDate(date)[2], dateformat);
    }

    /**
     * 取得年第一天
     *
     * @param date
     * @return
     */
    public static Date getFirstDateOfYear(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.DAY_OF_YEAR, c.getActualMinimum(Calendar.DAY_OF_YEAR));
        return c.getTime();
    }

    public static String getFirstDateOfYear(Date date, DateformatEnum dateformat) {
        Date c = getFirstDateOfMonth(date);
        SimpleDateFormat format = new SimpleDateFormat(dateformat.getFormat());
        return format.format(c);
    }

    /**
     * 取得年最后一天
     *
     * @param date
     * @return
     */
    public static Date getLastDateOfYear(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.DAY_OF_YEAR, c.getActualMaximum(Calendar.DAY_OF_YEAR));
        return c.getTime();
    }

    public static String getLastDateOfYear(Date date, DateformatEnum dateformat) {
        Date c = getLastDateOfMonth(date);
        SimpleDateFormat format = new SimpleDateFormat(dateformat.getFormat());
        return format.format(c);
    }

    /**
     * 计算指定日期为当前第几周 说明：20121231为第1周，此方法返回值为201201，这个值是错误的
     *
     * @param date
     * @return YYYYSS
     */
    public static String getWeekOfYearStr(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.setFirstDayOfWeek(Calendar.MONDAY);
        c.setMinimalDaysInFirstWeek(1);
        int y = c.get(Calendar.YEAR);
        int w = c.get(Calendar.WEEK_OF_YEAR);
        String result = String.valueOf(y);
        result = result + ((w < 10) ? ("0" + w) : w);
        return result;
    }

    /**
     * 得到星期
     *
     * @param date
     * @return
     * @author jianming.jiangjm
     * @date Oct 18, 2012
     */
    public static String getWeek(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.setFirstDayOfWeek(Calendar.MONDAY);
        int week = c.get(Calendar.DAY_OF_WEEK);
        return getWeekDate(week);
    }

    private static String getWeekDate(int week) {
        if (week == Calendar.MONDAY) {
            return "MONDAY";
        } else if (week == Calendar.TUESDAY) {
            return "TUESDAY";
        } else if (week == Calendar.WEDNESDAY) {
            return "WEDNESDAY";
        } else if (week == Calendar.THURSDAY) {
            return "THURSDAY";
        } else if (week == Calendar.FRIDAY) {
            return "FRIDAY";
        } else if (week == Calendar.SATURDAY) {
            return "SATURDAY";
        } else if (week == Calendar.SUNDAY) {
            return "SUNDAY";
        }
        return null;
    }

    /**
     * @param date
     * @return YYYYSS
     */
    public static String getSeasonOfYearStr(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int y = c.get(Calendar.YEAR);
        int s = getSeason(date);
        return y + "" + s;
    }

    /**
     * 取得季度月
     *
     * @param date
     * @return
     */
    private static Date[] getSeasonDate(Date date) {
        Date[] season = new Date[3];

        Calendar c = Calendar.getInstance();
        c.setTime(date);

        int nSeason = getSeason(date);
        if (nSeason == 1) {// 第一季度
            c.set(Calendar.MONTH, Calendar.JANUARY);
            season[0] = c.getTime();
            c.set(Calendar.MONTH, Calendar.FEBRUARY);
            season[1] = c.getTime();
            c.set(Calendar.MONTH, Calendar.MARCH);
            season[2] = c.getTime();
        } else if (nSeason == 2) {// 第二季度
            c.set(Calendar.MONTH, Calendar.APRIL);
            season[0] = c.getTime();
            c.set(Calendar.MONTH, Calendar.MAY);
            season[1] = c.getTime();
            c.set(Calendar.MONTH, Calendar.JUNE);
            season[2] = c.getTime();
        } else if (nSeason == 3) {// 第三季度
            c.set(Calendar.MONTH, Calendar.JULY);
            season[0] = c.getTime();
            c.set(Calendar.MONTH, Calendar.AUGUST);
            season[1] = c.getTime();
            c.set(Calendar.MONTH, Calendar.SEPTEMBER);
            season[2] = c.getTime();
        } else if (nSeason == 4) {// 第四季度
            c.set(Calendar.MONTH, Calendar.OCTOBER);
            season[0] = c.getTime();
            c.set(Calendar.MONTH, Calendar.NOVEMBER);
            season[1] = c.getTime();
            c.set(Calendar.MONTH, Calendar.DECEMBER);
            season[2] = c.getTime();
        }
        return season;
    }

    /**
     * 1 第一季度 2 第二季度 3 第三季度 4 第四季度
     *
     * @param date
     * @return
     */
    public static int getSeason(Date date) {

        int season = 0;

        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int month = c.get(Calendar.MONTH);
        switch (month) {
            case Calendar.JANUARY:
            case Calendar.FEBRUARY:
            case Calendar.MARCH:
                season = 1;
                break;
            case Calendar.APRIL:
            case Calendar.MAY:
            case Calendar.JUNE:
                season = 2;
                break;
            case Calendar.JULY:
            case Calendar.AUGUST:
            case Calendar.SEPTEMBER:
                season = 3;
                break;
            case Calendar.OCTOBER:
            case Calendar.NOVEMBER:
            case Calendar.DECEMBER:
                season = 4;
                break;
            default:
                break;
        }
        return season;
    }

    /**
     * 年月天的整数加减
     *
     * @param date
     * @param field
     * @param amount
     * @return
     */
    public static Date getAddDay(Date date, DateField field, int amount) {
        Calendar rightNow = Calendar.getInstance();
        if (date != null) {
            rightNow.setTime(date);
        }
        int intField = 0;
        String tmpField = field.name().toUpperCase();
        intField = 5;
        if (tmpField.equals(DateField.YEAR.name())) {
            intField = 1;
        } else if (tmpField.equals(DateField.MONTH.name())) {
            intField = 2;
        } else if (tmpField.equals(DateField.DAY.name())) {
            intField = 5;
        } else if (tmpField.equals(DateField.WEEK.name())) {
            intField = 3;
        }
        rightNow.add(intField, amount);
        return rightNow.getTime();
    }

    public static String getAddDay(Date date, DateField field, int amount, DateformatEnum dateformatEnum) {
        Date rightNow = getAddDay(date, field, amount);
        SimpleDateFormat formatter = new SimpleDateFormat(dateformatEnum.getFormat());
        String day = formatter.format(rightNow);
        return day;
    }

    public static enum DateField {
        YEAR, MONTH, DAY, WEEK;
    }

    public static int compareDay(Date date1, Date date2) {
        return date1.compareTo(date2);
    }

    /**
     * 前一小时的日期
     *
     * @param date
     * @return
     */
    public static Date getBeforhourDate(Date date) {
        Date dBefore = new Date();
        Calendar calendar = Calendar.getInstance(); // 得到日历
        calendar.setTime(date);// 把当前时间赋给日历
        calendar.add(Calendar.HOUR, -1); // 设置为 前一个小时
        dBefore = calendar.getTime(); // 得到前一天的时间
        return dBefore;
    }


    /**
     * 前n小时的日期
     *
     * @param date     给定时间
     * @param hourcont n
     * @return 给定时间前n小时的时间
     * @author Sidney
     */
    public static Date getBeforhourDate(Date date, int hourcont) {
        Date dBefore = new Date();
        Calendar calendar = Calendar.getInstance(); // 得到日历
        calendar.setTime(date);// 把当前时间赋给日历
        calendar.add(Calendar.HOUR, -hourcont); // 设置为 前hourcont个小时
        dBefore = calendar.getTime(); // 得到前一天的时间
        return dBefore;
    }

    /**
     * 后一小时的日期
     *
     * @param date
     * @return
     */
    public static String getLesterhourDate(Date date) {
        Date dBefore = new Date();
        Calendar calendar = Calendar.getInstance(); // 得到日历
        calendar.setTime(date);// 把当前时间赋给日历
        calendar.add(Calendar.HOUR, +1); // 设置为后一个小时
        dBefore = calendar.getTime(); // 得到前一天的时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // 设置时间格式
        String defaultStartDate = sdf.format(dBefore); // 格式化前一天
        return defaultStartDate;
    }

    /**
     * 前一天的日期
     *
     * @return
     */
    public static Date getBeforDayFroMonthDate(Date date) {
        Date dBefore = new Date();
        Calendar calendar = Calendar.getInstance(); // 得到日历
        calendar.setTime(date);// 把当前时间赋给日历
        calendar.add(Calendar.DAY_OF_YEAR, -1); // 设置为后一个小时
        dBefore = calendar.getTime(); // 得到前一天的时间
        return dBefore;
    }

    /**
     * 根据日期偏移天数取得日期。offset > 0 ,往后延迟offset天， offset < 0 向前推进 offset天
     *
     * @param date   :基日期
     * @param offset :日期天数偏移量
     * @return
     */
    public static Date getDateOffSet(Date date, int offset) {
        if (date == null) return date;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_YEAR, offset);
        calendar.add(Calendar.HOUR, 0);
        calendar.add(Calendar.MINUTE, 0);
        calendar.add(Calendar.SECOND, 01);
        return calendar.getTime();
    }

    /**
     * 根据日期偏移天数取得日期。hour > 0 ,往后延迟hour小时， hour < 0 向前推进 hour 小时
     * 根据日期偏移天数取得日期。minute > 0 ,往后延迟minute小时， minute < 0 向前推进 minute 小时
     *
     * @param date   :基日期
     * @param hour   :日期小时数偏移量
     * @param minute :日期分钟数偏移量
     * @return
     */
    public static Date getDateOffSet(Date date, int hour, int minute) {
        if (date == null) return date;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        //calendar.add(Calendar.DAY_OF_YEAR, hour);
        calendar.add(Calendar.HOUR, hour);
        calendar.add(Calendar.MINUTE, minute);
        //calendar.add(Calendar.SECOND, 01);
        return calendar.getTime();
    }

    /**
     * 取后？个小时的时间
     *
     * @param date
     * @return
     */
    public static String getLesterhourDate(Date date, int hour) {
        Date dBefore = new Date();
        Calendar calendar = Calendar.getInstance(); // 得到日历
        calendar.setTime(date);// 把当前时间赋给日历
        calendar.add(Calendar.HOUR, +hour); // 设置为后一个小时
        dBefore = calendar.getTime(); // 得到前一天的时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // 设置时间格式
        String defaultStartDate = sdf.format(dBefore); // 格式化前一天
        return defaultStartDate;
    }

    /**
     * 时间数据格式为 yyyy-mm-dd HH:mm:ss.S 转换成 yyyyMMddHHmmss
     *
     * @param formatDateString
     * @return
     * @author ZJD
     * @time 2013-8-2下午07:45:25
     */
    public static String formatStringToFormatString(String formatDateString) {
        int index = formatDateString.indexOf('.');
        if (index != -1) {
            formatDateString = formatDateString.substring(0, index);
        }
        return formatDateString.replaceAll("-", "").replaceAll(":", "").replaceAll(" ", "");
    }

    /**
     * 时间数据格式为 yyyy-mm 转换成 yyyyMMdd
     *
     * @param formatDateString
     * @return
     * @author ZJD
     * @time 2013-8-2下午07:45:25
     */
    public static String formatStringToYYMM(String formatDateString) {
        int index = formatDateString.indexOf('.');
        if (index != -1) {
            formatDateString = formatDateString.substring(0, index);
        }
        return formatDateString.replaceAll("-", "").replaceAll(":", "").replaceAll(" ", "").substring(0, 6);
    }

    //两个日期的--相差天数
    public static int daysBetween(Date start, Date end) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            start = sdf.parse(sdf.format(start));
            end = sdf.parse(sdf.format(end));
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(start);
        long time1 = cal.getTimeInMillis();
        cal.setTime(end);
        long time2 = cal.getTimeInMillis();
        long between_days = (time2 - time1) / (1000 * 3600 * 24);

        return Integer.parseInt(String.valueOf(between_days));
    }

    // 时间显示时格式转换
    public static String showfromatimeSS(String time) {
        if (null != time && !"".equals(time)) {
            String yy = time.substring(0, 4);
            String mm = time.substring(4, 6);
            String dd = time.substring(6, 8);
            String hh = time.substring(8, 10);
            String min = time.substring(10, 12);
            String ss = time.substring(12, 14);
            time = yy + "-" + mm + "-" + dd + " " + hh + ":" + min + ":" + ss;
        }
        return time;
    }

    // 时间显示时格式转换
    public static String showfromatimemin(String time) {
        if (null != time && !"".equals(time)) {
            String yy = time.substring(0, 4);
            String mm = time.substring(4, 6);
            String dd = time.substring(6, 8);
            String hh = time.substring(8, 10);
            String min = time.substring(10, 12);
            time = yy + "-" + mm + "-" + dd + " " + hh + ":" + min;
        }
        return time;
    }

    // 时间显示时格式转换
    public static String showfromatimeHH(String time) {
        if (null != time && !"".equals(time)) {
            String yy = time.substring(0, 4);
            String mm = time.substring(4, 6);
            String dd = time.substring(6, 8);
            String hh = time.substring(8, 10);
            time = yy + "-" + mm + "-" + dd + " " + hh;
        }
        return time;
    }

    // 时间显示时格式转换
    public static String showfromatimedd(String time) {
        if (null != time && !"".equals(time)) {
            String yy = time.substring(0, 4);
            String mm = time.substring(4, 6);
            String dd = time.substring(6, 8);
            time = yy + "-" + mm + "-" + dd;
        }
        return time;
    }

    // 时间显示时格式转换
    public static String showfromatimemm(String time) {
        if (null != time && !"".equals(time)) {
            String yy = time.substring(0, 4);
            String mm = time.substring(4, 6);
            time = yy + "-" + mm;
        }
        return time;
    }

    // 时间显示时格式转换
    public static String showfromatimeyear(String time) {
        if (null != time && !"".equals(time)) {
            String yy = time.substring(0, 4);
            time = yy;
        }
        return time;
    }

    /**
     * 获取当前时间位于一年的第几周
     *
     * @return
     */
    public static int weeknum(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        return cal.get(Calendar.WEEK_OF_YEAR);

    }

    /**
     * 获取时间的整点数
     *
     * @auther JZR
     * @version 2014年12月29日
     */
    public static String timePoint(String timeto) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        Date date = null;
        try {
            date = sdf.parse(timeto);
        } catch (ParseException e) {
            System.out.println(".........." + timeto);
            e.printStackTrace();
        }
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return sdf.format(calendar.getTime());
    }


    /**
     * 获取时间的整点数
     *
     * @auther JZR
     * @version 2014年12月29日
     */
    public static Date datePoint(Date timeto) {
        Calendar calendar = Calendar.getInstance();

        calendar.setTime(timeto);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * 取得时间的，年月日，yyyymmdd
     *
     * @param timeto
     * @return
     */
    public static int getYearMonthDay(String timeto) {
        String str = timeto.substring(0, 10);
        str = str.replaceAll("-", "");
        int yyyymmdd = Integer.parseInt(str);
        return yyyymmdd;
    }

    /**
     * 取得时间的，年月日，yyyymmdd
     *
     * @param date
     * @return
     */
    public static int getYearMonthDay(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int year = c.get(Calendar.YEAR);
        int mm = c.get(Calendar.MONTH) + 1;
        int dd = c.get(Calendar.DAY_OF_MONTH);
        String mmStr = "";
        String ddStr = "";
        String str = "";
        if (mm < 10) {
            mmStr = "0" + mm;
        } else {
            mmStr = "" + mm;
        }
        if (dd < 10) {
            ddStr = "0" + dd;
        } else {
            ddStr = "" + dd;
        }
        str = year + "" + mmStr + "" + ddStr;
        int yyyymmdd = Integer.parseInt(str);
        return yyyymmdd;
    }

    /***
     * 取得时间的，年月,yyyymm
     *
     * @auther JZR
     * @version 2014年12月29日
     */
    public static int getYearMonth(String timeto) {
        String str = timeto.substring(0, 7);
        str = str.replaceAll("-", "");
        int yyyymm = Integer.parseInt(str);
        return yyyymm;
    }

    /***
     * 取得时间的，年周,yyyyweek
     *
     * @return int
     * @auther JZR
     * @version 2014年12月29日
     */
    public static int getYearWeek(String timeto) {
        int week = weeknum(DateUtil.formatStringToDate(timeto, DateformatEnum.yyyyMMddHHmmssSplit));
        String str = timeto.substring(0, 4);
        str = str.replaceAll("-", "");
        int yyyyweek = Integer.parseInt(str + week);
        return yyyyweek;
    }

    /***
     * 取得时间的，年周,yyyy_week
     *
     * @return String
     * @auther JZR
     * @version 2014年12月29日
     */
    public static String getYear_Week(String timeto) {
        int week = weeknum(DateUtil.formatStringToDate(timeto, DateformatEnum.yyyyMMddHHmmssSplit));
        String str = timeto.substring(0, 4);
        str = str.replaceAll("-", "");
        return str + "_" + week;
    }

    /***
     * 取得日期的年
     *
     * @param date
     * @return int
     * @auther JZR
     * @version 2014年12月31日
     */
    public static int getYear(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.YEAR);
    }

    /***
     * 取得日期的月，如：01,02.....12
     *
     * @param date
     * @return String
     * @auther JZR
     * @version 2014年12月31日
     */
    public static String getMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int month = cal.get(Calendar.MONTH) + 1;
        if (month < 10) {
            return "0" + month;
        }
        return month + "";
    }

    /***
     * 取得日期的月，如：01,02.....12
     *
     * @param date
     * @return int
     * @auther JZR
     * @version 2014年12月31日
     */
    public static int getMonthInt(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int month = cal.get(Calendar.MONTH) + 1;
        return month;
    }

    /**
     * 根据开始时间和结束时间返回时间段内的小时集合 精确到时分秒
     *
     * @param beginDate
     * @param endDate
     * @return List
     */
    public static List<Date> gethourDatesBetweenTwoDate(Date beginDate, Date endDate) {
        List<Date> lDate = new ArrayList<Date>();
        lDate.add(beginDate);// 把开始时间加入集合
        Calendar cal = Calendar.getInstance();
        // 使用给定的 Date 设置此 Calendar 的时间
        cal.setTime(beginDate);
        boolean bContinue = true;
        while (bContinue) {
            // 根据日历的规则，为给定的日历字段添加或减去指定的时间量
            cal.add(Calendar.HOUR_OF_DAY, 1);
            // 测试此日期是否在指定日期之后
            if (endDate.after(cal.getTime())) {
                lDate.add(cal.getTime());
            } else {
                break;
            }
        }
        lDate.add(endDate);// 把结束时间加入集合
        return lDate;
    }

    /**
     * 根据开始时间和结束时间返回时间段内的天集合 精确到时分秒
     *
     * @param beginDate
     * @param endDate
     * @return List
     */
    public static List<Date> getdayDatesBetweenTwoDate(Date beginDate, Date endDate) {
        List<Date> lDate = new ArrayList<Date>();
        lDate.add(beginDate);// 把开始时间加入集合
        Calendar cal = Calendar.getInstance();
        // 使用给定的 Date 设置此 Calendar 的时间
        cal.setTime(beginDate);
        boolean bContinue = true;
        while (bContinue) {
            // 根据日历的规则，为给定的日历字段添加或减去指定的时间量
            cal.add(Calendar.DAY_OF_MONTH, 1);
            // 测试此日期是否在指定日期之后
            if (endDate.after(cal.getTime())) {
                lDate.add(cal.getTime());
            } else {
                break;
            }
        }
        lDate.add(endDate);// 把结束时间加入集合
        return lDate;
    }

    /**
     * 根据开始时间和结束时间返回时间段内的月集合 精确到时分秒
     *
     * @param beginDate
     * @param endDate
     * @return List
     */
    public static List<Date> getmonthDatesBetweenTwoDate(Date beginDate, Date endDate) {
        List<Date> lDate = new ArrayList<Date>();
        lDate.add(beginDate);// 把开始时间加入集合
        Calendar cal = Calendar.getInstance();
        // 使用给定的 Date 设置此 Calendar 的时间
        cal.setTime(beginDate);
        boolean bContinue = true;
        while (bContinue) {
            // 根据日历的规则，为给定的日历字段添加或减去指定的时间量
            cal.add(Calendar.MONTH, 1);
            // 测试此日期是否在指定日期之后
            if (endDate.after(cal.getTime())) {
                lDate.add(cal.getTime());
            } else {
                break;
            }
        }
        lDate.add(endDate);// 把结束时间加入集合
        return lDate;
    }

    public static List<Date> getDateBetweenTwoDate(String type, Date beginDate, Date endDate) {
        List<Date> datelist = new ArrayList<Date>();
        if (type.equals("hour")) {
            datelist = gethourDatesBetweenTwoDate(beginDate, endDate);
        } else if (type.equals("day")) {
            datelist = getdayDatesBetweenTwoDate(beginDate, endDate);
        } else if (type.equals("week")) {
            datelist = getdayDatesBetweenTwoDate(beginDate, endDate);
        } else if (type.equals("month")) {
            datelist = getdayDatesBetweenTwoDate(beginDate, endDate);
        } else if (type.equals("year")) {
            datelist = getmonthDatesBetweenTwoDate(beginDate, endDate);
        }
        return datelist;
    }

    /**
     * 对比传入时间和开始时间是否相同
     *
     * @param type
     * @param date1
     * @return
     */
    public static boolean comparedatetonow(String type, Date date1) {
        if (type.equals("hour")) {
            return DateUtil.formatDateToString(date1, DateformatEnum.yyyyMMddHH).equals(DateUtil.formatDateToString(new Date(),
                    DateformatEnum.yyyyMMddHH));
        } else if (type.equals("day")) {
            return DateUtil.formatDateToString(date1, DateformatEnum.yyyyMMdd).equals(DateUtil.formatDateToString(new Date(),
                    DateformatEnum.yyyyMMdd));
        } else if (type.equals("month")) {
            return DateUtil.formatDateToString(date1, DateformatEnum.yyyyMM).equals(DateUtil.formatDateToString(new Date(),
                    DateformatEnum.yyyyMM));
        } else if (type.equals("year")) {
            return DateUtil.formatDateToString(date1, DateformatEnum.yyyy).equals(DateUtil.formatDateToString(new Date(),
                    DateformatEnum.yyyy));
        }
        return false;
    }

    public static Date gettimeBytype(String type, String time) {
        Date lastdate = null;
        if (type.equals("hour")) {
            lastdate = DateUtil.formatStringToDate(DateUtil.showfromatimeHH(time), DateformatEnum.yyyyMMddHHSplit);
        } else if (type.equals("day")) {
            lastdate = DateUtil.formatStringToDate(DateUtil.showfromatimedd(time), DateformatEnum.yyyyMMddSplit);
        } else if (type.equals("week")) {
            lastdate = DateUtil.formatStringToDate(DateUtil.showfromatimedd(time), DateformatEnum.yyyyMMddSplit);
        } else if (type.equals("month")) {
            lastdate = DateUtil.formatStringToDate(DateUtil.showfromatimemm(time), DateformatEnum.yyyyMMSplit);
        } else if (type.equals("year")) {
            lastdate = DateUtil.formatStringToDate(DateUtil.showfromatimeyear(time), DateformatEnum.yyyy);
        }
        return lastdate;

    }

    /**
     * 根据type将时间转化成字符窜
     *
     * @param type
     * @param date1
     * @return
     */
    public static long gettimestrbytype(String type, Date date1) {
        if (type.equals("hour")) {
            return Long.parseLong(DateUtil.formatDateToString(date1, DateformatEnum.yyyyMMddHH));
        } else if (type.equals("day")) {
            return Long.parseLong(DateUtil.formatDateToString(date1, DateformatEnum.yyyyMMdd));
        } else if (type.equals("month")) {
            return Long.parseLong(DateUtil.formatDateToString(date1, DateformatEnum.yyyyMM));
        } else if (type.equals("year")) {
            return Long.parseLong(DateUtil.formatDateToString(date1, DateformatEnum.yyyy));
        }
        return 0;
    }

    /****
     * 根据日期取得整点日期,yyyy-mm-dd 00:00:00
     *
     * @auther JZR
     * @version 2014年12月27日
     */
    public static Date getIntegerDate(Date date) {
        Calendar monday = Calendar.getInstance();
        monday.setTime(date);
        monday.set(Calendar.MINUTE, 0);
        monday.set(Calendar.SECOND, 0);
        monday.set(Calendar.MILLISECOND, 0);
        return monday.getTime();
    }

    public static long gettimestamp(String datetime) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date;
        long timeStemp = 0;
        try {
            date = simpleDateFormat.parse(datetime);
            timeStemp = (long) date.getTime();

        } catch (ParseException e) {
            timeStemp = 0;
        }

        return timeStemp;
    }

    public static Date getnowdaystarttime() {
        Date date = getBeforDayFroMonthDate(new Date());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = simpleDateFormat.format(date);
        try {
            return simpleDateFormat1.parse(time + " 00:00:00");
        } catch (ParseException e) {
            return date;
        }
    }

    public static Date getnowdayendtime() {
        Date date = getBeforDayFroMonthDate(new Date());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = simpleDateFormat.format(date);
        try {
            return simpleDateFormat1.parse(time + " 23:59:59");
        } catch (ParseException e) {
            return date;
        }
    }

    /**
     * @param mss 要转换的毫秒数
     * @return 该毫秒数转换为 * days * hours * minutes * seconds 后的格式
     * @author fy.zhang
     */
    public static String formatDuring(long mss) {
        long days = mss / (1000 * 60 * 60 * 24);
        long hours = (mss % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
        long minutes = (mss % (1000 * 60 * 60)) / (1000 * 60);
        long seconds = (mss % (1000 * 60)) / 1000;
        return days + " days " + hours + " hours " + minutes + " minutes "
                + seconds + " seconds ";
    }

    public static String stampToDate(long time) {
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(time);
        res = simpleDateFormat.format(date);
        return res;

    }

    /***
     *  得到两个时间之间差的秒数
     * @param start
     * @param end
     * @return
     */
    public static long getDiffSconde(Date start, Date end) {
        long a = end.getTime();
        long b = start.getTime();
        int c = (int) ((a - b) / 1000);
        return c;
    }

    /**
     * 得到2个时间直接的小时差值
     *
     * @param start
     * @param end
     * @return
     */
    public static long getDiffHour(Date start, Date end) {
        long abs = Math.abs(getDiffSconde(start, end));
        long l = abs / 60 / 60;
        return l;
    }

    public static void main(String[] arg) throws ParseException {
    }
}
