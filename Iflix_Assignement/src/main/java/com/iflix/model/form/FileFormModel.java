package main.java.com.iflix.model.form;

public class FileFormModel implements IFormModel {
	
	private String sourceFileName;
private String destFileName;
public String getSourceFileName() {
	return sourceFileName;
}
public void setSourceFileName(String sourceFileName) {
	this.sourceFileName = sourceFileName;
}
public String getDestFileName() {
	return destFileName;
}
public void setDestFileName(String destFileName) {
	this.destFileName = destFileName;
}

}
