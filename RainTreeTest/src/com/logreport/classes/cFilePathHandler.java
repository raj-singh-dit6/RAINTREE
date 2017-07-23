package com.logreport.classes;

import java.io.File;
import java.util.Locale;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

public class cFilePathHandler 
{
	private  String aFolderPath;
	private  File aFileObj;

	public cFilePathHandler() {
		PropertyResourceBundle lPropResource=(PropertyResourceBundle) ResourceBundle.getBundle("ApplicationConfig",Locale.ENGLISH);
		try{
			aFolderPath=lPropResource.getString("SourceFolderDir");
			//System.out.println(aFolderPath);
		}catch(Exception ex){
			System.out.println("Resources Bundle ex cFilePathHandler(): "+ex);
			ex.printStackTrace();
		}
	}
	
	
	public File mGetFileObject(String pRelativePath)
	{
		aFileObj=new File(aFolderPath+pRelativePath);
		return aFileObj;
	}

	public File[] mGetFiles(String pRelativePath)
	{	
		File[] lListOfFiles=null;
		aFileObj = new File(aFolderPath+pRelativePath);
		lListOfFiles= aFileObj.listFiles();
		return lListOfFiles;
	}
	
}
