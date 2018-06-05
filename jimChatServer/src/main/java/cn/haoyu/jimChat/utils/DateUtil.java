package cn.haoyu.jimChat.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by haoyu on 2016/8/13.
 */
public class DateUtil {
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final SimpleDateFormat dateSdf = new SimpleDateFormat("yyyy-MM-dd");
    private static final SimpleDateFormat chineseDateSdf = new SimpleDateFormat("yyyy年MM月dd日");

    public static  String formatDate(Date date)throws ParseException{
        return sdf.format(date);
    }

    public static String formatDate(Date date,boolean isDateOnly)throws ParseException{
        return isDateOnly?dateSdf.format(date):sdf.format(date);
    }

    public static Date parse(String strDate) throws ParseException{
        return sdf.parse(strDate);
    }

    public static Date parseDate(String strDate) throws ParseException{
        return dateSdf.parse(strDate);
    }

    /**
     * 转换为中文日期
     * @param strDate yyyy-MM-dd
     * @return  yyyy年MM月dd日
     * @throws ParseException
     */
    public static String chineseDate(String strDate) throws ParseException{
        return chineseDateSdf.format(parseDate(strDate));
    }

    public static Date getStartDayOfWeek() {
        int mondayPlus;
        Calendar cd = Calendar.getInstance();
        int dayOfWeek = cd.get(Calendar.DAY_OF_WEEK)-1;
        if (dayOfWeek == 0) {
            mondayPlus = -6;
        } else {
            mondayPlus = 1 - dayOfWeek;
        }
        GregorianCalendar currentDate = new GregorianCalendar();
        currentDate.add(GregorianCalendar.DATE, mondayPlus);
        Date monday = currentDate.getTime();
        return monday;
    }

    public static Date getDayOfStartTime(Date date){
        Calendar todayStart = Calendar.getInstance();
        todayStart.setTime(date);
        todayStart.set(Calendar.HOUR_OF_DAY, 0);
        todayStart.set(Calendar.MINUTE, 0);
        todayStart.set(Calendar.SECOND, 0);
        todayStart.set(Calendar.MILLISECOND, 0);
        return new Date(todayStart.getTime().getTime());
    }

    public static Date getDayOfEndTime(Date date){
        Calendar todayEnd = Calendar.getInstance();
        todayEnd.setTime(date);
        todayEnd.set(Calendar.HOUR_OF_DAY, 23);
        todayEnd.set(Calendar.MINUTE, 59);
        todayEnd.set(Calendar.SECOND, 59);
        todayEnd.set(Calendar.MILLISECOND, 999);
        return new Date(todayEnd.getTime().getTime());
    }

    public static String getDayOfStartTimeString(Date date){
        Date res = getDayOfStartTime(date);
        return sdf.format(res);
    }

    public static String getDayOfEndTimeString(Date date){
        Date res = getDayOfEndTime(date);
        return sdf.format(res);
    }

    private static String getChineseNumber(int i){
        if(i<0 || i>9) throw new RuntimeException("只能转换0-9的数字");
        switch (i){
            case 1:
                return "一";
            case 2:
                return "二";
            case 3:
                return "三";
            case 4:
                return "四";
            case 5:
                return "五";
            case 6:
                return "六";
            case 7:
                return "七";
            case 8:
                return "八";
            case 9:
                return "九";
            case 0:
                return "〇";
            default:
                return null;
        }
    }
    public static int countDownHour(Date date ){
        Date now=new Date();
        long diff=now.getTime()-date.getTime();
        long days = diff / (1000 * 60 * 60 * 24);
        long hours = (diff-days*(1000 * 60 * 60))/(1000* 60 * 60);
        return new Long(hours).intValue();
    }

    private static String formatChineseNumber(int i,boolean isDecimalBase){
        String decimalBaseStr[] = {"","十","百","千","万","十万","百万","千万","亿","十亿","百亿","千亿","万亿"};
        String numStr = i+"";
        StringBuilder sb = new StringBuilder();
        char[] numArr = numStr.toCharArray();
        for(int f=0;f<numArr.length;f++){
            int k = Integer.valueOf(numArr[f]+"");
            if(!isDecimalBase) {
                if (!StringUtils.isEmpty(getChineseNumber(k))) sb.append(getChineseNumber(k));
            }else{
                if (!StringUtils.isEmpty(getChineseNumber(k))) {
                    String decimal = decimalBaseStr[numArr.length-f-1];
                    sb.append(getChineseNumber(k)).append(decimal);
                }
            }
        }
        return sb.toString();
    }

    public static String chineseDateFormat(Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH)+1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        String chineseYear = formatChineseNumber(year,false);
        String chineseMonth = formatChineseNumber(month,true);
        if(month>10)chineseMonth = chineseMonth.substring(1);
        if(month==10)chineseMonth = chineseMonth.substring(1,2);
        String chineseDay = formatChineseNumber(day,true);
        if(day>10 && day<20)chineseDay = chineseDay.substring(1);
        if(day==10)chineseDay = chineseDay.substring(1,2);
        return chineseYear+"年"+chineseMonth+"月"+chineseDay+"日";
    }
    public  static Date addDay(Date startDay,int day){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDay);
        calendar.add(Calendar.DATE, day);
        return calendar.getTime();
    }

    public static void main(String[] args) throws ParseException {
        String date = "2017-12-5";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = sdf.parse(date);
        System.out.println(chineseDateFormat(date1));
    }

}
