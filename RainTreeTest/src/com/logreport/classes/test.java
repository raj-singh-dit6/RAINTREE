package com.logreport.classes;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class test {

	public static void main(String[] a) throws ParseException, IOException {

		String lFromDate = "05-03-2016";
		String lToDate = "03-12-2016";
		cFileProcessor lFileProcessor = new cFileProcessor();
		HashMap<String, Integer> lRecords = lFileProcessor.mGetDetailsByDateRange(lFromDate, lToDate);
		
		Object[] objArray = lRecords.entrySet().toArray();
		Arrays.sort(objArray, new Comparator() {
		    public int compare(Object o1, Object o2) {
		        return ((Map.Entry<String, Integer>) o1).getValue().compareTo(
		               ((Map.Entry<String, Integer>) o2).getValue());
		    }
		});

		cFilePathHandler lFilePathHandler = new cFilePathHandler();
		File aLogFile = lFilePathHandler.mGetFileObject("Report["+lFromDate+" to "+lToDate+"].txt");
		File aParentDirectory = aLogFile.getParentFile();

		System.out.println(aLogFile);
		System.out.println(aParentDirectory);
		// if parent directory does not exists, then create it
		if (aParentDirectory != null) {
			aParentDirectory.mkdirs();
		}
		// if log file does not exists, then create it
		if (aLogFile != null) {
			aLogFile.createNewFile();
		}
		// true = append file
		FileWriter lWriter = new FileWriter(aLogFile, true);
		BufferedWriter lBufferedWriter = new BufferedWriter(lWriter);
		lBufferedWriter.write("Date	:  " + lFromDate + "  to  " + lToDate);
		lBufferedWriter.write("\r\n");
		lBufferedWriter.write("ComputerName Number of Disconnects");
		lBufferedWriter.write("\r\n");

		for (Object e : objArray) {

			lBufferedWriter.write(((Map.Entry<String, Integer>) e).getKey() + "   " + ((Map.Entry<String, Integer>) e).getValue());
			lBufferedWriter.write("\r\n");
		} 
		

		lBufferedWriter.close();
	}
}
