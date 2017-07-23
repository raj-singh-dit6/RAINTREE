package com.logreport.classes;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.ListIterator;

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
		Date startDate = formatter.parse(pFROMDateDDMMYYY);
		Date endDate = formatter.parse(pTODateDDMMYYY);

		Calendar start = Calendar.getInstance();
		start.setTime(startDate);
		Calendar end = Calendar.getInstance();
		end.setTime(endDate);
		lGetDetails=mGetDetailsByDate(start,end,new HashMap<String,Integer>());
		return	lGetDetails;
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
	
	
}
