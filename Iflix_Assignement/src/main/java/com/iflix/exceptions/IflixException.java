package main.java.com.iflix.exceptions;

public class IflixException extends Exception{

	public String geoID;
	
	public String exceptionCode;
	
	public String customMsg ;
	
	public IflixException(String msg)
	{
		customMsg = msg;
	}

	public String getGeoID() {
		return geoID;
	}

	public void setGeoID(String geoID) {
		this.geoID = geoID;
	}

	public String getExceptionCode() {
		return exceptionCode;
	}

	public void setExceptionCode(String exceptionCode) {
		this.exceptionCode = exceptionCode;
	}

	public String getCustomMsg() {
		return customMsg;
	}

	public void setCustomMsg(String customMsg) {
		this.customMsg = customMsg;
	}
	
}
