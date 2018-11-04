package main.java.com.iflix.model;

import java.util.Date;

public class Subcription {
	
	private Date startDate ;
	
	private Date endDate ;
	
	private String partnerName;
	
	private int numberOfDays;

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getPartnerName() {
		return partnerName;
	}

	public void setPartnerName(String partnerName) {
		this.partnerName = partnerName;
	}

	public int getNumberOfDays() {
		return numberOfDays;
	}

	public void setNumberOfDays(int numberOfDays) {
		this.numberOfDays = numberOfDays;
	}
	
	



    @Override
    public String toString() {
        return "Subscription{" +
                "startDate='" + startDate + '\'' +
                ", endDate=" + endDate +
                ", parterName=" + partnerName +
                ", NumberOfDays=" + numberOfDays +
                '}';
    }
    
}
