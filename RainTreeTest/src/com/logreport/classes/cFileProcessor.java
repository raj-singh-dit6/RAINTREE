package com.logreport.classes;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

public class cFileProcessor {

	cFilePathHandler aFilePathHandler=new cFilePathHandler();
	FileInputStream afin;  
	public HashMap<String,Integer> mGetDetailsByDate(Calendar pStart,Calendar pEnd,HashMap<String,Integer> lDataContent) throws ParseException
	{		LinkedHashSet<String> lDateRange=new LinkedHashSet<String>();
			for (Date date = pStart.getTime(); pStart.before(pEnd); pStart.add(Calendar.DATE, 1), date = pStart.getTime()) 
			{
				String lDateMMdd=new SimpleDateFormat("MM/dd").format(date.getTime());
				lDateRange.add(lDateMMdd);
			}
	
			//Import Files from "QoS_logs" folder
			File[] lAllFiles=aFilePathHandler.mGetFiles("QoS_logs");
			try{
				if(lAllFiles!=null)
				{
					for(int i=0;i<lAllFiles.length;i++)
					{
						File lCurrentFile=lAllFiles[i];
						if(lCurrentFile.canRead() && lCurrentFile.isFile())
						{ 
							Path filePath =lCurrentFile.toPath();
							Charset charset = Charset.defaultCharset();        
							List<String> stringList = Files.readAllLines(filePath, charset);
							ListIterator<String> stringListItr=stringList.listIterator();
							while(stringListItr.hasNext())
							{
								String x=stringListItr.next();
								if(!x.equals(""))
								{ 
									String lComputerName=x.substring(x.indexOf("ComputerName:")+13, x.indexOf("UserID")-1).trim();
									String lDate=x.substring(x.indexOf("| (")+3, x.indexOf("| (")+8).trim();
									if(lDataContent.containsKey(lComputerName)&& lDateRange.contains(lDate) && x.indexOf("disconnected")!=-1)//HashMap already have record for that particular computer 
									{
											Integer lCount=lDataContent.get(lComputerName)+1;
											lDataContent.replace(lComputerName, lCount);
									}
									else if(lDateRange.contains(lDate) && x.indexOf("disconnected")!=-1) // HashMap does not have record  for that particular computer
									{
											Integer lCount=1;
											lDataContent.put(lComputerName, lCount);
									}
								}	
							}
						}
					}
					
				}
			}catch(Exception ex){
				System.out.println("Resources Bundle ex cPathHandler(): "+ex);
				ex.printStackTrace();
			}
		return lDataContent;
	}
	
	public HashMap<String,Integer> mGetDetailsByDateRange(String pFROMDateDDMMYYY,String pTODateDDMMYYY) throws ParseException
	{
		HashMap<String,Integer> lGetDetails=null;
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		try{
		Date startDate = formatter.parse(pFROMDateDDMMYYY);
		Date endDate = formatter.parse(pTODateDDMMYYY);

		Calendar start = Calendar.getInstance();
		start.setTime(startDate);
		Calendar end = Calendar.getInstance();
		end.setTime(endDate);
		lGetDetails=mGetDetailsByDate(start,end,new HashMap<String,Integer>());
		}
		catch(ParseException e){
			throw new ParseException("Please enter date in (dd-MM-yyyy) format only",0);			
		}
		return lGetDetails;
	}
	public  String mGetFileContent(File file)throws IOException 
	{
	      int len;
	      char[] chr = new char[(4096)^4];
	      final StringBuffer buffer = new StringBuffer();
	      final FileReader reader = new FileReader(file);
	      try {
	          while ((len = reader.read(chr)) > 0) {
	              buffer.append(chr, 0, len);
	          }
	      } finally {
	          reader.close();
	      }
	return buffer.toString();
  }
	
	
	public String[] mGenerateReport(String pFromDate,String pToDate) throws ParseException, IOException
	{	String[] lReturn=new String[2];
		HashMap<String, Integer> lRecords = mGetDetailsByDateRange(pFromDate, pToDate);
		Object[] objArray = lRecords.entrySet().toArray();
		Arrays.sort(objArray, new Comparator() {
		    public int compare(Object o1, Object o2) {
		        return ((Map.Entry<String, Integer>) o1).getValue().compareTo(
		               ((Map.Entry<String, Integer>) o2).getValue());
		    }
		});

		cFilePathHandler lFilePathHandler = new cFilePathHandler();
		File aLogFile = lFilePathHandler.mGetFileObject("Reports/Report["+pFromDate+" to "+pToDate+"].txt");
		File aParentDirectory = aLogFile.getParentFile();

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
		lBufferedWriter.write("Date	:  " + pFromDate + "  to  " + pToDate);
		lBufferedWriter.write("\r\n");
		lBufferedWriter.write("ComputerName Number of Disconnects");
		lBufferedWriter.write("\r\n");

		for (Object e : objArray) {

			lBufferedWriter.write(((Map.Entry<String, Integer>) e).getKey() + "   " + ((Map.Entry<String, Integer>) e).getValue());
			lBufferedWriter.write("\r\n");
		} 
		lBufferedWriter.close();
		lReturn[0]="1";
		lReturn[1]=aParentDirectory.getPath()+"\\Report["+pFromDate+" to "+pToDate+"].txt";
		return lReturn;
	}
	
	
}
