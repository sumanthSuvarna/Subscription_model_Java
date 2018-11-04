package main.java.com.iflix.model;

import java.util.List;

public class Subcriptions {
	

    private String name;
    
    private long number;
    
    private List <Subcription> partnerList ;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getNumber() {
		return number;
	}

	public void setNumber(long number) {
		this.number = number;
	}

	public List<Subcription> getPartnerList() {
		return partnerList;
	}

	public void setPartnerList(List<Subcription> partnerList) {
		this.partnerList = partnerList;
	}

    
    
}
