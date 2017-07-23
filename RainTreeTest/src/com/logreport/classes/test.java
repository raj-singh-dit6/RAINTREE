package com.logreport.classes;

import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;

public class test {

	public static void main(String[] a) throws ParseException, IOException {
		cFileProcessor lFileProcessor = new cFileProcessor();
		Scanner sc= new Scanner(System.in);
		String lFromDate="";
		String lToDate="";
		int pressedKey;
		while(1==1)
		{
			System.out.println("To generate report you need to enter a date range in (dd-MM-yyyy) format" );
			System.out.println("Please enter the starting date(dd-MM-yyyy)" );
			lFromDate=sc.nextLine();
			if(lFromDate.equals(""))
			{		
				System.out.println("Wrong Details entered)" );
				continue;
			}
			System.out.println("Please enter the ending date(dd-MM-yyyy)" );
			lToDate=sc.nextLine();
			if(lToDate.equals(""))
			{		
				System.out.println("Wrong Details entered)" );
				continue;
			}
			
			System.out.println("Please wait for a while....");
			String[] lStatus=lFileProcessor.mGenerateReport(lFromDate, lToDate);
			if(lStatus[0].equals("1"))
				System.out.println("Please check and open  the generated report from the following directory : "+lStatus[1]);
			
			System.out.println("Press 1 to generate more reports");
			System.out.println("Press 0 to exit");
			pressedKey=sc.nextInt();
			if(pressedKey==0)
				break;
			else
				continue;
		}	
	}
}
