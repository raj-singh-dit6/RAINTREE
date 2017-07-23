package com.logreport.classes;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author Bhavya 23-Nov-2016 To change date format to dd-MMM-yyyy
 * @chg Bhavya 23-Nov-2016 Handled the empty date format
 */
public class cDate
{
	
	/**
	 * Current Date in yyyy-MM-dd format
	 * @return String
	 */
	public String mCurrentDateDBformat() {
		SimpleDateFormat lFormat = new SimpleDateFormat("yyyy-MM-dd");
		return lFormat.format(new Date());
	}
	
	/**
	 * Current Date in dd-MMM-yyyy format
	 * @return String
	 */
	public String mCurrentDateUIformat() {
		SimpleDateFormat lFormat = new SimpleDateFormat("dd-MMM-yyyy");
		return lFormat.format(new Date());
	}
	
	/**
	 * @author Bhavya 23-Nov-2016 method to change date format from dd-MM-yyyy to dd-MMM-yyyy
	 * @param lDate
	 * @return lNewDate
	 * @exception ParseException
	 */
	public String mDateFormat(String lDate) throws ParseException
	{
		SimpleDateFormat lFormat1 = new SimpleDateFormat("dd-MM-yyyy");
		SimpleDateFormat lFormat2 = new SimpleDateFormat("dd-MMM-yyyy");
		Date lDate1;
		String lNewDate ="";
		if(lDate!="") {
			lDate1     =  (Date) lFormat1.parse(lDate);
			lNewDate   =  lFormat2.format(lDate1);
		}
		else
			lNewDate = "";
		return lNewDate;
	}
	
	/**
	 * @author 91779 - 20170131 - method to change date format from dd-MMM-yyyy to yyyy-MM-dd
	 * @param pDate
	 * @return lNewDate
	 * @exception ParseException
	 */
	public String mDateFormatyyyyMMdd(String pDate)
	{
		SimpleDateFormat lFormat1 = new SimpleDateFormat("dd-MMM-yyyy");
		SimpleDateFormat lFormat2 = new SimpleDateFormat("yyyy-MM-dd");
		Date lDate1;
		String lNewDate ="";
		try {
			if(pDate!="") {
				lDate1     =  (Date) lFormat1.parse(pDate);
				lNewDate   =  lFormat2.format(lDate1);
			}
			else
				lNewDate = "";
		}
		catch (ParseException pEx) {
			pEx.printStackTrace();
			System.out.println("mDateFormatyyyyMMdd error: "+pEx.getMessage());
		}
		return lNewDate;		
	}
	
	/**
	 * @author Bhavya 23-Nov-2016 method to change date format from yyyy-MM-dd to dd-MMM-yyyy
	 * @param lDate
	 * @return lNewDate
	 * @exception ParseException
	 */
	public String mDateFormat1(String lDate) throws ParseException
	{
		SimpleDateFormat lFormat1 = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat lFormat2 = new SimpleDateFormat("dd-MMM-yyyy");
		Date lDate1;
		String lNewDate ="";
		if(lDate!="") {
			lDate1     =  (Date) lFormat1.parse(lDate);
			lNewDate   =  lFormat2.format(lDate1);
		}
		else
			lNewDate = "";
		return lNewDate;		
	}
	/**
	 * @author Bhavya 23-Nov-2016 method to change date format from yy-MM-dd to dd-MM-yyyy
	 * @param lDate
	 * @return lNewDate
	 * @exception ParseException
	 */
	public String mDateFormatddmmyyyy(String lDate) throws ParseException
	{
		SimpleDateFormat lFormat1 = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat lFormat2 = new SimpleDateFormat("dd-MM-yyyy");
		Date lDate1;
		String lNewDate ="";
		if(lDate!="") {
			lDate1     =  (Date) lFormat1.parse(lDate);
			lNewDate   =  lFormat2.format(lDate1);
		}
		else
			lNewDate = "";
		return lNewDate;		
	}
	
	/**
	 * To compare the sent date with current date 
	 * @author 91779
	 * @param pDate in yyyy-MM-dd format
	 * @return 0 if same dates, 1 if pDate in future, -1 if its past
	 */
	public int mCurrentDateCompare(String pDate) {
		int lCompare = 0;
	    DateFormat lDateFormat=new SimpleDateFormat("yyyy-MM-dd");
    	String lCurrentDateString=lDateFormat.format(new java.util.Date());
    	Date lCurrentDate, lDateToCompare;
    	
		try {
			lCurrentDate = lDateFormat.parse(lCurrentDateString);
			lDateToCompare = lDateFormat.parse(pDate);
			lCompare=lDateToCompare.compareTo(lCurrentDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lCompare;
	}
	
	/**
	 * To compare the sent date with current date and return the date difference
	 * @author 91779
	 * @param pDate in yyyy-MM-dd format
	 * @return 0 if same dates, date difference in positive number if pDate in future, negative number if its past
	 */
	public int mCurrentDateDifference(String pDate) {
		int lCompare = 0;
	    DateFormat lDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    	Date lCurrentDate, lDateToCompare;
    	
		try {
			String lCurrentDateString=lDateFormat.format(new java.util.Date());
			lCurrentDate = lDateFormat.parse(lCurrentDateString);
			lDateToCompare = lDateFormat.parse(pDate);
			
			long lTimeDifferenceMilliseconds = lDateToCompare.getTime() - lCurrentDate.getTime();
			long diffDays = lTimeDifferenceMilliseconds / (60 * 60 * 1000 * 24);
			lCompare=(int) diffDays;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return lCompare;
	}
	
	public static void main(String[] args) {
		cDate lObj = new cDate();
		lObj.mCurrentDateDifference("2017-01-15");
	}
	
	/**
	 * @author Nikshitha - 08-Feb-2017 - method to change date format from dd-MMM-yyyy to dd-MM-yyyy
	 * @param pDate
	 * @return lNewDate
	 * @exception ParseException
	 */
	public String mDateFormatddMMyyyy(String pDate)
	{
		SimpleDateFormat lFormat1 = new SimpleDateFormat("dd-MMM-yyyy");
	    SimpleDateFormat lFormat2 = new SimpleDateFormat("dd-MM-yyyy");
	    Date lDate1;
	    String lNewDate ="";
	    try {
		    if(pDate!="") {
		    	lDate1     =  (Date) lFormat1.parse(pDate);
		    	lNewDate   =  lFormat2.format(lDate1);
		    }
		    else
		    	lNewDate = "";
	    } catch (ParseException pEx) {
			pEx.printStackTrace();
			System.out.println("mDateFormatddMMyyyy error: "+pEx.getMessage());
		}
		return lNewDate;		
	}
	
	/**
	 * @author Nikshitha - 08-Feb-2017 - method to change date format from dd-MM-yyyy to yyyy-MM-dd
	 * @param pDate
	 * @return lNewDate
	 * @exception ParseException
	 */
	public String mDateFormatyyyymmdd(String pDate)
	{
		SimpleDateFormat lFormat1 = new SimpleDateFormat("dd-MM-yyyy");
	    SimpleDateFormat lFormat2 = new SimpleDateFormat("yyyy-MM-dd");
	    Date lDate1;
	    String lNewDate ="";
	    try {
		    if(pDate!="") {
		    	lDate1     =  (Date) lFormat1.parse(pDate);
		    	lNewDate   =  lFormat2.format(lDate1);
		    }
		    else
		    	lNewDate = "";
	    } catch (ParseException pEx) {
			pEx.printStackTrace();
			System.out.println("mDateFormatddMMyyyy error: "+pEx.getMessage());
		}
		return lNewDate;		
	}
	
	/**
	 * @author 91779 - 20170316 - To get the start date of current financial year
	 * @return date in dd-MMM-yyyy format
	 */
	public String mGetFinancialYearStartDate()
	{
		SimpleDateFormat lFormat = new SimpleDateFormat("dd-MMM-yyyy");
		SimpleDateFormat lFormat2 = new SimpleDateFormat("dd-MM-yyyy");
		Calendar lCalendar = Calendar.getInstance();
		int lMonth = lCalendar.get(Calendar.MONTH);
		String lDate = "";
		if(lMonth < 3)
			lDate = "01-04-"+(lCalendar.get(Calendar.YEAR) -1);
		else
			lDate = "01-04-"+lCalendar.get(Calendar.YEAR);
		
		Date lDateObj = new Date();
        try {
			lDateObj = lFormat2.parse(lDate);
			lDate = lFormat.format(lDateObj);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lDate;
	}
	
	/**
	 * @author 91779 - 20170307 - To get the start date of current quarter
	 * @return date in dd-MMM-yyyy format
	 */
	public String mGetQuarterStartDate()
	{	
		SimpleDateFormat lFormat = new SimpleDateFormat("dd-MMM-yyyy");
		SimpleDateFormat lFormat2 = new SimpleDateFormat("dd-MM-yyyy");
		Calendar lCalendar = Calendar.getInstance();
		
		int lMonth = lCalendar.get(Calendar.MONTH);
		String lStartMonth = "";
		switch(lMonth) {
		case 0:
		case 1:
		case 2: lStartMonth = "01";
			break;
		case 3:
		case 4:
		case 5: lStartMonth = "04";
			break;
		case 6:
		case 7:
		case 8: lStartMonth = "07";
			break;
		case 9:
		case 10:
		case 11: lStartMonth = "10";
			break;
		}
		
        String lDate = "01"+"-"+lStartMonth+"-"+lCalendar.get(Calendar.YEAR);
        Date lDateObj = new Date();
        try {
			lDateObj = lFormat2.parse(lDate);
			lDate = lFormat.format(lDateObj);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
	    return lDate;			
	}
	
	/**
	 * @author 91779 - 20170307 - To get the end date of current quarter
	 * @return date in dd-MMM-yyyy format
	 */
	public String mGetQuarterEndDate()
	{	
		SimpleDateFormat lFormat = new SimpleDateFormat("dd-MMM-yyyy");
		SimpleDateFormat lFormat2 = new SimpleDateFormat("dd-MM-yyyy");
		Calendar lCalendar = Calendar.getInstance();
		
		int lMonth = lCalendar.get(Calendar.MONTH);
		String lEndMonth = "", lEndDate = "";
		switch(lMonth) {
		case 0:
		case 1:
		case 2: lEndMonth = "03";lEndDate = "31";
			break;
		case 3:
		case 4:
		case 5: lEndMonth = "06";lEndDate = "30";
			break;
		case 6:
		case 7:
		case 8: lEndMonth = "09";lEndDate = "30";
			break;
		case 9:
		case 10:
		case 11: lEndMonth = "12";lEndDate = "31";
			break;
		}
		
        String lDate = lEndDate+"-"+lEndMonth+"-"+lCalendar.get(Calendar.YEAR);
        Date lDateObj = new Date();
        try {
			lDateObj = lFormat2.parse(lDate);
			lDate = lFormat.format(lDateObj);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
	    return lDate;			
	}
	
}