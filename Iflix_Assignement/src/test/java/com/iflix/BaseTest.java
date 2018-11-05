package test.java.com.iflix;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.OutputStreamWriter;
import java.io.Writer;

import org.junit.After;
import org.junit.Before;

public abstract class BaseTest {
	
	private String TestDir = "";
	
	public BaseTest () {
		TestDir =  System.getProperty("user.dir")+"\\test\\fixture\\";
	}

	public boolean createFile(String name ,String contents) {
		
		try{
			Writer writer = new BufferedWriter(new OutputStreamWriter(
		              new FileOutputStream(TestDir + name), "utf-8")) ;
		   writer.write(contents);
		   writer.close();		
		}
		catch(Exception ex){
			return false;
		}		
		return true;
	}
	
	public boolean deleteFile(String name){
		File newFile =  null;
		try{
			newFile = new File(TestDir + name);
			newFile.delete();
		}
		catch(Exception ex){
			return false;
		}
		return true;
	}
	
	public String GetFileWithPath(String name){
		return TestDir + name;
	}
	
	@Before
	public void setUp() {
		
	}
	
	@After
	public void tearDown () {
		File directoryPath  = new File(TestDir);		
		for (File file: directoryPath.listFiles())
		    if (!file.isDirectory())
		        file.delete();
	}
}
